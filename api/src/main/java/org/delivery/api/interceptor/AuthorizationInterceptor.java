package org.delivery.api.interceptor;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.TokenErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.business.TokenBusiness;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private final TokenBusiness tokenBusiness;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(" Authorization Interceptor url : {} ",request.getRequestURL());

        // WEB 크롬의 경우 GET , POST 요청하기 전 OPTION으로 요청해서 해당 API를 지원하는지 체크한다. 이걸 통과시키는 코드가 필요하다.
        if(HttpMethod.OPTIONS.matches(request.getMethod())){
            return true;    //API가 살아있는지 요청날리는 것이기에 검증이 필요없음
        }

        //리소스를 요청하는 경우 ( js, html, png 등등) 검증이 필요없음
        if(handler instanceof ResourceHttpRequestHandler){
            return true;
        }

        var accessToken = request.getHeader("authorization-token");
        if(accessToken == null){
            throw new ApiException(TokenErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND);
        }

        Long userId = tokenBusiness.validationAccessToken(accessToken);

        if(userId != null){
            RequestAttributes requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
            // Local Thread에 저장
            requestContext.setAttribute("userId",userId,RequestAttributes.SCOPE_REQUEST);   //request 스코프로 저장하겠다.
            return true;
        }

        throw new ApiException(ErrorCode.BAD_REQUEST);
    }
}
