package com.team.final8teamproject.gymboard.repository;

import com.team.final8teamproject.gymboard.entity.GymBoard;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface GymBoardRepository extends JpaRepository<GymBoard, Long> {
    List<GymBoard> findAllByUsername(String username);

    List<GymBoard> findAll();
    Page<GymBoard> findByTitleContainingIgnoreCaseAndInLiveTrue(
            String title,
            Pageable pageable
    );
    @Modifying
    Optional<GymBoard> findById(@NotNull Long id);
    Optional<GymBoard> findByIdAndUsername(Long id, String username);
    Page<GymBoard> findByUsername(Pageable pageable, String username);

    Optional<GymBoard> findByUsername(String username);
    void deleteById(Long id);

}
