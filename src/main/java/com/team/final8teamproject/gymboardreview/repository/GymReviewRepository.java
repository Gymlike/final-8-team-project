package com.team.final8teamproject.gymboardreview.repository;

import com.team.final8teamproject.gymboardreview.entity.GymReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GymReviewRepository extends JpaRepository<GymReview, Long> {
    List<GymReview> findByGymId(Long gymId);
    Optional<GymReview> findById(Long id);
    Optional<GymReview> findByIdAndUsername(Long id, String username);
    void deleteById(Long id);
    // 삭제되는 gymId값과 연동된것도 삭제
    void deleteByGymId(Long gymId);

}
