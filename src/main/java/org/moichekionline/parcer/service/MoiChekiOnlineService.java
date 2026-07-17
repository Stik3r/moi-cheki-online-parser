package org.moichekionline.parcer.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.moichekionline.parcer.client.MoiChekiOnlineClient;
import org.moichekionline.parcer.models.dto.moiChekiOnline.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Setter
public class MoiChekiOnlineService {

    private final MoiChekiOnlineClient moiChekiOnlineClient;

    @Value("${refreshToken}")
    private String refreshToken;
    @Value("${sourceDeviceId}")
    private String sourceDeviceId;
    private volatile String token;

    public void refreshToken() {
        RefreshTokenRequest tokenRequest = new RefreshTokenRequest(
                refreshToken,
                new DeviceInfo(
                        sourceDeviceId,
                        "WEB",
                        "1.0.0",
                        new MetaDetails(
                                "Mozilla/5.0"
                        )
                )
        );
        TokenResponse response = moiChekiOnlineClient.refreshToken(tokenRequest);
        token = response.token();

        if (response.refreshToken() != null && !response.refreshToken().isBlank()) {
            refreshToken = response.refreshToken();
        }
    }

    public ReceiptSearchResponse getReceipts(ReceiptSearchRequest receiptSearchRequest) {

        var receipts = executeWithToken(authorization -> moiChekiOnlineClient.getReceipts(
                "Bearer " + token,
                receiptSearchRequest
        ));

        return receipts;
    }

    public List<FiscalDataResponse> getFiscalData(List<String> keys) {
        List<FiscalDataResponse> result = new ArrayList<>();
        for (String key : keys) {
            result.add(executeWithToken(authorization ->
                    moiChekiOnlineClient.getFiscalData(
                            authorization,
                            new FiscalDataRequest(key)
                    )));
        }
        return result;
    }

    @SneakyThrows
    public byte[] saveToJson(List<String> keys) {
        List<FiscalDataResponse> res = getFiscalData(keys);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsBytes(res);
    }

    private void ensureTokenExists() {
        if (token == null) {
            synchronized (this) {
                if (token == null) {
                    refreshToken();
                }
            }
        }
    }

    private <T> T executeWithToken(Function<String, T> request) {
        ensureTokenExists();

        try {
            return request.apply("Bearer " + token);
        } catch (HttpClientErrorException.Unauthorized exception) {
            refreshToken();
            return request.apply("Bearer " + token);
        }
    }
}
