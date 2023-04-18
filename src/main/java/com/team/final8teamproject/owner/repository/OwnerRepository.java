package com.team.final8teamproject.owner.repository;

import com.team.final8teamproject.owner.entity.Owner;
import com.team.final8teamproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByUsername(String username);
    Optional<Owner> findByNickName(String nickName);

    Optional<Owner> findByEmail(String email);

    Optional<Owner> findByPhoneNumber(String phoneNumber);
}
