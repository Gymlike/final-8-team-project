package com.team.final8teamproject.base.repository;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.owner.entity.Owner;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BaseRepository extends JpaRepository<BaseEntity, Long> {

//    @Modifying(clearAutomatically = true)
    @Query("select p from BaseEntity as p where p.username = :username")
    Optional<BaseEntity> findByUsername(@Param("username") String username);

//    void deleteAllByUsername(@Param("username") String username);

    Optional<BaseEntity> findByNickName(@Param("nickName") String nickName);

    Optional<BaseEntity> findByEmail(@Param("email") String email);

    List<BaseEntity> findByRole(UserRoleEnum userRoleEnum);

    boolean existsByUsername(String username);
}
