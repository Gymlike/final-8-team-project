package com.team.final8teamproject.owner.repository;

import com.team.final8teamproject.owner.entity.Owner;
import com.team.final8teamproject.owner.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Optional<Owner> findByTrainername(String ownername);
}
