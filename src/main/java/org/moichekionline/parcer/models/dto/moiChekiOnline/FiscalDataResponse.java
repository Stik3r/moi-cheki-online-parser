package org.moichekionline.parcer.models.dto.moiChekiOnline;

import java.math.BigDecimal;
import java.util.List;

public record FiscalDataResponse(
        String dateTime,
        Integer operationType,
        String user,
        String userInn,
        String retailPlace,
        String retailPlaceAddress,
        String kktRegId,
        String fiscalDriveNumber,
        String fiscalDocumentNumber,
        String fiscalDocumentFormatVer,
        String fiscalSign,
        String operator,
        Integer shiftNumber,
        BigDecimal totalSum,
        BigDecimal cashTotalSum,
        BigDecimal eCashTotalSum,
        BigDecimal creditSum,
        BigDecimal prepaidSum,
        BigDecimal provisionSum,
        List<FiscalItemDto> items,
        Integer taxationType,
        Double nds10,
        String fnsSite,
        AmountsReceiptNds amountsReceiptNds,
        String buyerAddress
) {
}
