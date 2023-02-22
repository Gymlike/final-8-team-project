package com.team.final8teamproject.gymboardreview.repository;

import com.team.final8teamproject.gymboardreview.entity.GymDeleteReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GymDeleteReviewRepository extends JpaRepository<GymDeleteReview, Long> {
    List<GymDeleteReview> findByGymId(Long gymId);
    List<GymDeleteReview> findByGymIdAndUsername(Long gymId, String username);
    Optional<GymDeleteReview> findByIdAndUsername(Long id, String username);
    void deleteById(Long id);
}
