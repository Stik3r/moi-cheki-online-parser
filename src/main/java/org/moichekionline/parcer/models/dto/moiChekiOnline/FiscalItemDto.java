package org.moichekionline.parcer.models.dto.moiChekiOnline;

import java.math.BigDecimal;

public record FiscalItemDto(
        String name,
        BigDecimal price,
        BigDecimal quantity,
        BigDecimal sum,
        Integer nds,
        Integer paymentType,
        Integer productType,
        String providerInn
) {
}
