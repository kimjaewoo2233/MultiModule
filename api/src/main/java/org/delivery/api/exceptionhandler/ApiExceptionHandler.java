package org.delivery.api.exceptionhandler;


import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.error.ErrorCodeIfs;
import org.delivery.api.common.exception.ApiException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MIN_VALUE) //최우선 처리 여기서 안 잡히면 Global로 패스
public class ApiExceptionHandler {


    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Api<Object>> apiException(ApiException apiException){
        log.error("",apiException);

        ErrorCodeIfs errorCodeIfs = apiException.getErrorCodeIfs();
        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(
                        Api.ERROR(errorCodeIfs, errorCodeIfs.getDescription())
                );
    }
}
