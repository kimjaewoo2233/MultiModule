package org.delivery.api.domain.token;


import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.api.domain.token.ifs.TokenHelperIfs;
import org.delivery.api.domain.token.model.TokenDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * token에 대한 도메인 로직이 들어감
 * */
@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenHelperIfs tokenHelperIfs;

    public TokenDTO issueAccessToken(Long userID){
        HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("userId",userID);

        return tokenHelperIfs.issueAccessToken(data);
    }

    public TokenDTO issueRefreshToken(Long userID){
        HashMap<String,Object> data = new HashMap<String,Object>();
        data.put("userId",userID);

        return tokenHelperIfs.issueRefreshToken(data);
    }

    public Long validationToken(String token){
        Map<String, Object> map = tokenHelperIfs.validationTokenWithThrow(token);
        Object userId = map.get("userId");
        Objects.requireNonNull(userId, () -> {  //userID는 null이 아니어야하고 null이면 exception을 일으킨다.
            throw new ApiException(ErrorCode.NULL_POINT);
        });

        return Long.parseLong(userId.toString());
    }

}
