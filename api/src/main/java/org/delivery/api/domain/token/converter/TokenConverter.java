package org.delivery.api.domain.token.converter;


import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.token.model.TokenDTO;

import java.util.Objects;

@Converter
public class TokenConverter {

    public TokenResponse toResponse(
            TokenDTO accessToken,
            TokenDTO refreshToken
    ){
        Objects.requireNonNull(accessToken, () -> { throw new ApiException(ErrorCode.NULL_POINT); });
        Objects.requireNonNull(refreshToken, () -> { throw new ApiException(ErrorCode.NULL_POINT); });

        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }
}
