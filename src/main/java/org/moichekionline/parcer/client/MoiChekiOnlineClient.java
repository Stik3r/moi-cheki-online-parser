package org.moichekionline.parcer.client;

import org.moichekionline.parcer.models.dto.moiChekiOnline.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/api")
public interface MoiChekiOnlineClient {

    @PostExchange("/v1/auth/token")
    TokenResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest);

    @PostExchange("/v1/receipt")
    ReceiptSearchResponse getReceipts(
            @RequestHeader("Authorization") String authorization,
            @RequestBody ReceiptSearchRequest receiptSearchRequest);

    @PostExchange("/v1/receipt/fiscal_data")
    FiscalDataResponse getFiscalData(
            @RequestHeader("Authorization") String authorization,
            @RequestBody FiscalDataRequest request
    );
}
