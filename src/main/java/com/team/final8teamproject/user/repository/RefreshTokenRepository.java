package com.team.final8teamproject.user.repository;

import com.team.final8teamproject.user.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    void deleteRefreshTokenByEmail(String email);

}
