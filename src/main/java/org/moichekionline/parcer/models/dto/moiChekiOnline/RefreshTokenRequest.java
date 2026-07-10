package org.moichekionline.parcer.models.dto.moiChekiOnline;

public record RefreshTokenRequest(
        String refreshToken,
        DeviceInfo deviceInfo)
{}
