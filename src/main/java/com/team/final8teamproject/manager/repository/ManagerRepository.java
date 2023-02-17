package com.team.final8teamproject.manager.repository;

import com.team.final8teamproject.manager.entity.Manager;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findByUsername(String manager);
    void deleteByUsername(String manager);
    Page<Manager> findByRole(Pageable pageable, UserRoleEnum role);
}
