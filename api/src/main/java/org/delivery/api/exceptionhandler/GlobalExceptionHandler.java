package org.delivery.api.exceptionhandler;


import lombok.extern.slf4j.Slf4j;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.error.ErrorCode;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.criteria.CriteriaBuilder;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE)              //예외핸들러 순서 Global은 맨 마지막에 넣을 예정 값이 높을 수록 마지막
public class GlobalExceptionHandler {

    /**
     * 예상하지 못한 예외잡기 ( 모든 예외는 여기서 한번 잡히지만 Order 설정을 인해 가장 마지막순간 잡힘)
     * */
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<Object>> exception(Exception e){
        log.error(" {} ",e);

        return ResponseEntity
                .status(500)
                .body(
                        Api.ERROR(ErrorCode.SERVER_ERROR)
                );
    }
}
