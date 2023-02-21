package com.team.final8teamproject.owner.repository;

import com.team.final8teamproject.owner.entity.Trainer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByIdAndTrainername(Long id, String trainername);

    Page<Trainer> findAll(Pageable pageable);


    Optional<Trainer> findByIdAndOwnerId(Long id, Long id1);

    Page<Trainer> findAllByStoreName(String storeName, Pageable pageable);
}
