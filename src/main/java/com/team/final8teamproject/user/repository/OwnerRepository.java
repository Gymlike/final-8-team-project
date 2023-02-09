package com.team.final8teamproject.user.repository;

import com.team.final8teamproject.owner.entity.Owner;
import com.team.final8teamproject.user.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Optional<Owner> findByOwnername(String ownerName);
}
