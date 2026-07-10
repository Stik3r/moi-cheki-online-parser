package org.moichekionline.parcer.models.dto.moiChekiOnline;

import java.time.LocalDate;

public record ReceiptSearchRequest(
        int limit,
        int offset,
        LocalDate dateFrom,
        LocalDate dateTo,
        String orderBy,
        String inn,
        String kktOwner
) {
}
