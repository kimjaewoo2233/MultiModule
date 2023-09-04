package org.delivery.api.domain.user.converter;


import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.db.user.UserEntity;

import java.util.Optional;

@Converter
@RequiredArgsConstructor
public class UserConverter {

    public UserEntity toEntity(UserRegisterRequest registerRequest){


        return Optional.ofNullable(registerRequest)
                .map(it -> {    //데이터가 null이 아닌 경우
                    //to Entity
                    return UserEntity.builder()
                            .name(registerRequest.getName())
                            .email(registerRequest.getEmail())
                            .password(registerRequest.getPassword())
                            .address(registerRequest.getAddress())
                            .build();

                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT,"UserRequest Null"));
    }

    public UserResponse toResponse(UserEntity userEntity) {
        return Optional.ofNullable(userEntity)
                .map(it -> {
                    return UserResponse.builder()
                            .id(userEntity.getId())
                            .name(userEntity.getName())
                            .status(userEntity.getStatus())
                            .email(userEntity.getEmail())
                            .address(userEntity.getAddress())
                            .registeredAt(userEntity.getRegisteredAt())
                            .unregisteredAt(userEntity.getUnregisteredAt())
                            .lastLoginAt(userEntity.getLastLoginAt())
                            .build();
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT,"User Entity Null"));
    }
}
