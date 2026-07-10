package org.moichekionline.parcer.models.dto.moiChekiOnline;

public record DeviceInfo(
        String sourceDeviceId,
        String sourceType,
        String appVersion,
        MetaDetails metaDetails
) {}
