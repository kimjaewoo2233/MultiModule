package org.delivery.api.domain.user.business;


import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.user.controller.model.UserRegisterRequest;
import org.delivery.api.domain.user.controller.model.UserResponse;
import org.delivery.api.domain.user.converter.UserConverter;
import org.delivery.api.domain.user.service.UserService;
import org.delivery.db.user.UserEntity;

import java.util.Optional;

@RequiredArgsConstructor
@Business
public class UserBusiness {
    private final UserService userService;

    private final UserConverter userConverter;


    /**
     * 사용자에 대한 가입처리 로직
     * @param
     * */
    public UserResponse register(UserRegisterRequest body) {

        /*
        * var entitiy = userConver.toEntity(body);
        * var newEntity = userService.register(entity);
        * var response = userConverter.toResponse(newEntity);
        * */

        return Optional.ofNullable(body)
                .map(userConverter::toEntity)
                .map(userService::register)
                .map(userConverter::toResponse)
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT,"request Null"));
    }
}
