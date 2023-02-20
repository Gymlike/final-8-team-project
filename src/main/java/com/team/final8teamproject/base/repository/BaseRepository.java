package com.team.final8teamproject.base.repository;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository extends JpaRepository<BaseEntity, Long> {
    Optional<BaseEntity> findByUsername(String username);

    Optional<BaseEntity> findByEmail(String email);

    List<BaseEntity> findByRole(UserRoleEnum userRoleEnum);

}
