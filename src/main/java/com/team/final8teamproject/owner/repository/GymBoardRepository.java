package com.team.final8teamproject.owner.repository;

import com.team.final8teamproject.owner.entity.GymBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GymBoardRepository extends JpaRepository<GymBoard, Long> {
    List<GymBoard> findAllByUserName(String userName);
    Optional<GymBoard> findByIdAndUserName(Long id, String userName);
    Page<GymBoard> findAll(Pageable pageable);
    Page<GymBoard> findByUserName(Pageable pageable, String userName);
    void deleteById(Long id);

}
