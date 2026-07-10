package org.moichekionline.parcer.models.dto.moiChekiOnline;

import java.math.BigDecimal;

public record ReceiptSummary(
        String key,
        String createdDate,
        BigDecimal totalSum,
        String kktOwner,
        String kktOwnerInn,
        String brandId,
        String brand,
        String brandImage
) {
}
