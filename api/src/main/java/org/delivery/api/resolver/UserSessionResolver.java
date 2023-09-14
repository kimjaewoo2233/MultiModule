package org.delivery.api.resolver;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.UserSession;
import org.delivery.api.domain.user.business.UserBusiness;
import org.delivery.api.domain.user.model.User;
import org.delivery.api.domain.user.service.UserService;
import org.delivery.db.user.UserEntity;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@Component
@RequiredArgsConstructor
public class UserSessionResolver implements HandlerMethodArgumentResolver { //Request  들어오면 실행

    private final UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 지원하는 파라미터 체크, 애노테이션 체크

        // 1. 애노테이션이 있는지 체크 @UserSession이 달려있는지
        boolean annotation = parameter.hasParameterAnnotation(UserSession.class);
        // 2. 파라미터 타입 체크    매개변수로 받는 파라미터가 User 타입이 맞는지 체크
        boolean parameterType = parameter.getParameterType().equals(User.class);

        return (annotation && parameterType); // 이게 만약 true일 경우 아래 resolveArgument 메소드가 실행된다.
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // support parameter 에서 true 반환시 여기 실행

        //request context holder에서 찾아오기
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        Object userId = requestAttributes.getAttribute("userId",RequestAttributes.SCOPE_REQUEST);

        // 사용자 정보 세팅
        UserEntity userEntity = userService.getUserWithThrow(Long.parseLong(userId.toString()));

        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .status(userEntity.getStatus())
                .password(userEntity.getPassword())
                .address(userEntity.getAddress())
                .registeredAt(userEntity.getRegisteredAt())
                .unregisteredAt(userEntity.getUnregisteredAt())
                .lastLoginAt(userEntity.getLastLoginAt())
                .build();
    }
}
