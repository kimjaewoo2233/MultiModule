package org.delivery.api.common.api;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.api.common.error.ErrorCodeInter;

import javax.validation.Valid;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api<T> {

    private Result result;

    @Valid
    private T body;

    public static <T> Api<T> OK(T data){
        Api api = new Api();
        api.result = Result.OK();
        api.body = data;
        return api;
    }

    /**
     * 만약 에러날 경우 바디에 채울 값이 없기 때문에 제네릭이 아닌 Object로 만들었다.
     * */
    public static Api<Object> ERROR(Result result){
        Api api = new Api();
        api.result = result;
        return api;
    }

    public static Api<Object> ERROR(ErrorCodeInter errorCodeInter){
        Api api = new Api<Object>();
        api.result = Result.ERROR(errorCodeInter);
        return  api;
    }
    public static Api<Object> ERROR(ErrorCodeInter errorCodeInter, String description){
        Api api = new Api<Object>();
        api.result = Result.ERROR(errorCodeInter, description);
        return api;
    }

}
