package com.team.final8teamproject.base.repository;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.security.cache.CacheNames;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface BaseRepository extends JpaRepository<BaseEntity, Long> {

//    @Cacheable(cacheNames = "BaseEntity", key="#username", unless="#result==null")
//    @Query("select p from BaseEntity as p where p.username = :username")
//    Optional<BaseEntity> findByUsername(@Param("username") String username);

    @Cacheable(value = "FindUser")
    Optional<BaseEntity> findByUsername(String username);
    void deleteAllByUsername(@Param("username") String username);
    Optional<BaseEntity> findByNickName(@Param("nickName") String nickName);
    Optional<BaseEntity> findByEmail(@Param("email") String email);
    List<BaseEntity> findByRole(UserRoleEnum userRoleEnum);
    boolean existsByUsername(String username);
}
