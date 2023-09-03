package org.delivery.api.common.api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.ErrorCodeIfs;

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
    public static Result ERROR(ErrorCodeIfs errorCodeIfs){
        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode())
                .resultMessage(errorCodeIfs.getDescription())
                .resultDescription("에러발생")
                .build();
    }

    /**
     * 예외가 일어날 경우
     * */
    public static Result ERROR(ErrorCodeIfs errorCodeIfs, Throwable tx){
        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode())
                .resultMessage(errorCodeIfs.getDescription())
                .resultDescription(tx.getLocalizedMessage())
                .build();
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs, String resultDescription){
        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode())
                .resultMessage(errorCodeIfs.getDescription())
                .resultDescription(resultDescription)
                .build();
    }
}
