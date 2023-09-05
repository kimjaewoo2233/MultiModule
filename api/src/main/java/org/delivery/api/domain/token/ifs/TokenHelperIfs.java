package org.delivery.api.domain.token.ifs;

import org.delivery.api.domain.token.model.TokenDTO;

import java.util.Map;

public interface TokenHelperIfs {

    TokenDTO issueAccessToken(Map<String, Object> data);

    TokenDTO issueRefreshToken(Map<String, Object> data);

    Map<String, Object> validationTokenWithThrow(String token);

}
