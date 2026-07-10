package org.moichekionline.parcer.models.dto.moiChekiOnline;

public record TokenResponse(
        String token,
        String tokenExpireIn,
        String refreshToken,
        String refreshTokenExpiresIn
)
{}
