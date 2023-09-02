package org.delivery.api.account.model;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) // 실제 응답값은 바로 스네이크케이스로 바꿔준다.
public class AccountMeResponse {

    private String email;

    private String name;

    private LocalDateTime registeredAt;
}
