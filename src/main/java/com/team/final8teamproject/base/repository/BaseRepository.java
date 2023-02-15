package com.team.final8teamproject.base.repository;

import com.team.final8teamproject.base.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BaseRepository extends JpaRepository<BaseEntity, Long> {

    Optional<BaseEntity> findByUsername(String username);
    Optional<BaseEntity> findByEmail(String email);
}
