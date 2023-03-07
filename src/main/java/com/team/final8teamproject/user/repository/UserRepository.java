package com.team.final8teamproject.user.repository;

import com.team.final8teamproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByNickName(String nickName);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);

}
