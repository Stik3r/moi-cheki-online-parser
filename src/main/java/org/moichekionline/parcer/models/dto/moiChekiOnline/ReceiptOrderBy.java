package org.moichekionline.parcer.models.dto.moiChekiOnline;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ReceiptOrderBy {
    CREATED_DATE_ASC("CREATED_DATE:ASC"),
    CREATED_DATE_DESC("CREATED_DATE:DESC"),
    TOTAL_SUMM_ASC("TOTAL_SUMM:ASC"),
    TOTAL_SUMM_DESC("TOTAL_SUMM:DESC");

    public final String value;
}
