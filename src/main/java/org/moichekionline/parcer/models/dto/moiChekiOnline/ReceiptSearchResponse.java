package org.moichekionline.parcer.models.dto.moiChekiOnline;

import java.util.List;

public record ReceiptSearchResponse(
        List<ReceiptSummary> receipts,
        boolean hasMore,
        List<BrandDto> brands
) {
}
