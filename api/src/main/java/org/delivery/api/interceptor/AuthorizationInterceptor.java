package org.delivery.api.interceptor;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

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

        // TODO: hedaer 검증

        return true;
    }
}
