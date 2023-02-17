package com.team.final8teamproject.manager.repository;

import com.team.final8teamproject.manager.entity.GeneralManager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeneralManagerRepository extends JpaRepository<GeneralManager, Long> {

    Optional<GeneralManager> findByUsername(String GeneralName);
}
