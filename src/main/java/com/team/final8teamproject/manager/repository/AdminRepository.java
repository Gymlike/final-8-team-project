package com.team.final8teamproject.manager.repository;

import com.team.final8teamproject.manager.entity.Manager;
import com.team.final8teamproject.manager.entity.ManagerRoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Manager, Long> {

    Optional<Manager> findByManager(String manager);
    void deleteByManager(String manager);
    Page<Manager> findByRole(Pageable pageable, ManagerRoleEnum role);
}
