package com.team.final8teamproject.owner.repository;

import com.team.final8teamproject.owner.entity.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerGymRepository extends JpaRepository<Gym, Long> {
    Optional<Gym> findByUsername(String username);

}
