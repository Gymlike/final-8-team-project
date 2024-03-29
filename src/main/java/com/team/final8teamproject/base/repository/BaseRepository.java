package com.team.final8teamproject.base.repository;

import com.team.final8teamproject.base.dto.BaseEntityProjectionDto;
import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.redis.cache.CacheNames;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface BaseRepository extends JpaRepository<BaseEntity, Long> {

    Optional<BaseEntityProjectionDto> findByUsernameAndInLiveIsTrue(String username);

    @Cacheable(cacheNames = CacheNames.USERBYUSERNAME, key="'login'+#p0", unless="#result==null")
    Optional<BaseEntity> findByUsername(String username);


    Optional<BaseEntity> findByNickName(@Param("nickName") String nickName);
    Optional<BaseEntity> findByEmail(@Param("email") String email);
    List<BaseEntity> findByRole(UserRoleEnum userRoleEnum);
    boolean existsByUsername(String username);
}
