package org.delivery.db.user;

import org.delivery.db.user.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // select * from user where id = ? and status =? order by id desc limit 1
    @Query("select u from UserEntity u  where u.id = :id and u.status = :status order by u.id desc")
    Optional<UserEntity> findFirstById(@Param("id") Long id,
                                       @Param("status")UserStatus userStatus);

    // select * from user where email = ? and password = ? and status = ? order by id desc limit 1
    @Query("select u from UserEntity u where u.email = :email and u.password = :password and u.status = :status order by u.id desc")
    Optional<UserEntity> findFirstByEmailAndPasswordAndStatus(@Param("email") String email,
                                                              @Param("password") String password,
                                                              @Param("status") UserStatus status);

}
