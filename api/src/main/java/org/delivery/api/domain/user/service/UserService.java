package org.delivery.api.domain.user.service;


import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.user.UserEntity;
import org.delivery.db.user.UserRepository;
import org.delivery.db.user.enums.UserStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.beans.Transient;
import java.time.LocalDateTime;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public UserEntity register(UserEntity entity){
        return Optional.ofNullable(entity)
                .map(it -> {

                    entity.setStatus(UserStatus.REGISTERED);
                    entity.setRegisteredAt(LocalDateTime.now());
                    return userRepository.save(entity);
                }).orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT,"User Entity Null"));
    }
}
