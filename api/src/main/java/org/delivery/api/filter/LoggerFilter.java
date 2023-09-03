package org.delivery.api.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


@Slf4j
@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);
        /**
         * response,request는 한번 읽거나 한번더 응답할경우 에러나거나 못 읽는데 그래서 위처럼 ContentCaching객체를 이용해야한다.
            wrapping된 클래스들이 다음으로 넘어간다.
         * */

        chain.doFilter(requestWrapper,responseWrapper);

        //request 정보
        Enumeration<String> headerNames = requestWrapper.getHeaderNames();
        StringBuilder headerValues = new StringBuilder();

        headerNames.asIterator().forEachRemaining(headerKey -> {
            String headerValue = requestWrapper.getHeader(headerKey);

            headerValues
                    .append("[")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append("]");
        });

        String requestBody = new String(requestWrapper.getContentAsByteArray());
        String uri = requestWrapper.getRequestURI();
        String method = requestWrapper.getMethod();

        log.info(">>>>> method {} , uri  : {} , header : {} , body {} ",method,uri,headerValues,requestBody);

        //response 정보
        StringBuilder responseHeaderValues = new StringBuilder();
        responseWrapper.getHeaderNames().forEach(headerKey -> {
            String headerValue = responseWrapper.getHeader(headerKey);

            responseHeaderValues
                    .append("[")
                    .append(headerKey)
                    .append(" : ")
                    .append(headerValue)
                    .append("]");
        });

        String responseBody = new String(responseWrapper.getContentAsByteArray());

        log.info("<<<<<< header : {} , body : {} ",responseHeaderValues,responseBody);
        responseWrapper.copyBodyToResponse(); //바디를 한번 읽었기 때문에 해당 메소드를 써야만 바디가 제대로 간다.
    }
}
