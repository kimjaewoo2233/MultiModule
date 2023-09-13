package org.delivery.api.domain.user.service;


import lombok.RequiredArgsConstructor;
import org.delivery.api.common.annotation.Converter;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.UserErrorCode;
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

    public UserEntity login(
            String email,
            String password
    ){
        var entity = getUserWithThrow(email,password);
        return entity;
    }

    public UserEntity getUserWithThrow(
            String email,
            String password
    ){
        return userRepository.
                findFirstByEmailAndPasswordAndStatus(email,password,UserStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }

    public UserEntity getUserWithThrow(
            Long userId
    ){
        return userRepository.
                findFirstById(
                        userId,
                        UserStatus.REGISTERED
                ).orElseThrow(() -> new ApiException(UserErrorCode.USER_NOT_FOUND));
    }
}
