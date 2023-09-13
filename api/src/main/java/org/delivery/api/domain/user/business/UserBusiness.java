package org.delivery.api.domain.user.business;


import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Business;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.TokenService;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.delivery.api.domain.token.controller.model.TokenResponse;
import org.delivery.api.domain.user.controller.model.UserLoginRequest;
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

    private final TokenBusiness tokenBusiness;

    /**
     * email , password를 가지고 사용자 체크
     * user entity 로그인 확인
     * token 생성
     * token을 response로 내려줘야한다.
     * */
    public TokenResponse login(UserLoginRequest request) {
        var userEntity = userService.login(request.getEmail(),request.getPassword());
        // 사용자 없으면 throw
        TokenResponse tokenResponse = tokenBusiness.issueToken(userEntity);

        return tokenResponse;
    }


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

    public UserResponse me(Long userID) {
        UserEntity user = userService.getUserWithThrow(userID);
        UserResponse response = userConverter.toResponse(user);
        return response;
    }
}
