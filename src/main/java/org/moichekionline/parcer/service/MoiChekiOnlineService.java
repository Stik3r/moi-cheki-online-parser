package org.moichekionline.parcer.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.moichekionline.parcer.client.MoiChekiOnlineClient;
import org.moichekionline.parcer.models.dto.moiChekiOnline.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Setter
public class MoiChekiOnlineService {

    private final MoiChekiOnlineClient moiChekiOnlineClient;

    @Value("${refreshToken}")
    private String refreshToken;
    @Value("${sourceDeviceId}")
    private String sourceDeviceId;
    private String token;

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
    }

    public ReceiptSearchResponse getReceipts(ReceiptSearchRequest receiptSearchRequest) {

        var receipts = moiChekiOnlineClient.getReceipts(
                "Bearer " + token,
                receiptSearchRequest
        );

        System.out.println(receipts);
        return receipts;
    }

    public List<FiscalDataResponse> getFiscalData(List<String> keys) {
        List<FiscalDataResponse> result = new ArrayList<>();
        for (String key : keys) {
            result.add(moiChekiOnlineClient.getFiscalData(
                    "Bearer " + token,
                    new FiscalDataRequest(key)
            ));
        }
        return result;
    }

    @SneakyThrows
    public byte[] saveToJson(List<FiscalDataResponse> fiscalDataResponses) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsBytes(fiscalDataResponses);
    }
}
