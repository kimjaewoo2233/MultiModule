package org.delivery.api.account;


import lombok.RequiredArgsConstructor;
import org.delivery.api.account.model.AccountMeResponse;
import org.delivery.api.common.api.Api;
import org.delivery.db.account.AccountEntity;
import org.delivery.db.account.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountAPIController {

    private final AccountRepository accountRepository;

    @GetMapping("/me")
    public Api<AccountMeResponse> save(){
        AccountMeResponse responseData = AccountMeResponse.builder()
                .name("홍길동")
                .email("A@gmail.com")
                .registeredAt(LocalDateTime.now())
                .build();

        //  RuntimeException 일부로 일으킴
        var str = "안녕";
        var age = Integer.parseInt(str);

        Api<AccountMeResponse> apiResponseData =  Api.OK(responseData);
        return apiResponseData;
    }
}
