package com.team.final8teamproject.owner.repository;

import com.team.final8teamproject.owner.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByOwnername(String ownername);
}
