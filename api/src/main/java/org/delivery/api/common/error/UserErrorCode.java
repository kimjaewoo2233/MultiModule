package org.delivery.api.common.error;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCodeInter{

    USER_NOT_FOUND(400, 1404, "사용자를 찾으 수 없음")
    ;

    private final Integer httpStatusCode;

    private Integer errorCode;  //Internal servererror 중 에러마다 다르게 설정하기 위한 코드임 아닐때는 그냥 HTTP Code를 따른다.

    private String description;
}

