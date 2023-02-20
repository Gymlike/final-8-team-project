package com.team.final8teamproject.user.repository;

import com.team.final8teamproject.user.entity.KaKao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KakaoRepository extends JpaRepository<KaKao, Long> {

    Optional<KaKao> findByKakaoId(Long KakaoID);

    Optional<KaKao> findByEmail(String email);

}
