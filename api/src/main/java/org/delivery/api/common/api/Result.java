package org.delivery.api.common.api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.ErrorCodeInter;

import javax.validation.Valid;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {

    private Integer resultCode;

    private String resultMessage;

    private String resultDescription;

    public static Result OK(){
        Result result = Result.builder()
                .resultCode(ErrorCode.OK.getErrorCode())
                .resultMessage(ErrorCode.OK.getDescription())
                .resultDescription("성공")
                .build();
        return result;
    }


    /**
     * ErroCodeInter를 구현체들은 모두 ErrorCde 이넘 타입이다.
     * */
    public static Result ERROR(ErrorCodeInter errorCodeInter){
        return Result.builder()
                .resultCode(errorCodeInter.getErrorCode())
                .resultMessage(errorCodeInter.getDescription())
                .resultDescription("성공")
                .build();
    }

    /**
     * 예외가 일어날 경우
     * */
    public static Result ERROR(ErrorCodeInter errorCodeInter, Throwable tx){
        return Result.builder()
                .resultCode(errorCodeInter.getErrorCode())
                .resultMessage(errorCodeInter.getDescription())
                .resultDescription(tx.getLocalizedMessage())
                .build();
    }

    public static Result ERROR(ErrorCodeInter errorCodeInter, String resultDescription){
        return Result.builder()
                .resultCode(errorCodeInter.getErrorCode())
                .resultMessage(errorCodeInter.getDescription())
                .resultDescription(resultDescription)
                .build();
    }
}
