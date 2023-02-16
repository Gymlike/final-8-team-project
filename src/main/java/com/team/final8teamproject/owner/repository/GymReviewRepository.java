package com.team.final8teamproject.owner.repository;

import com.team.final8teamproject.owner.entity.GymBoard;
import com.team.final8teamproject.owner.entity.GymReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GymReviewRepository extends JpaRepository<GymReview, Long> {
    List<GymReview> findByGymId(Long gymId);
    Optional<GymReview> findByIdAndUsername(Long id, String username);
    void deleteById(Long id);

}
