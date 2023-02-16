package com.team.final8teamproject.owner.repository;

import com.team.final8teamproject.owner.entity.GymReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GymReviewRepository extends JpaRepository<GymReview, Long> {
    Optional<GymReview> findByUsername(String username);

}
