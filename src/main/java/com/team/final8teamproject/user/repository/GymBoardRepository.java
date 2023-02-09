package com.team.final8teamproject.user.repository;

import com.team.final8teamproject.owner.entity.GymBoard;
import com.team.final8teamproject.owner.entity.Owner;
import com.team.final8teamproject.user.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;

public interface GymBoardRepository extends JpaRepository<GymBoard, Long> {
    List<GymBoard> findAllByUsername(String username);
    Optional<GymBoard> findByIdAndUsername(Long id, String username);
    Page<GymBoard> findAll(Pageable pageable);
    Page<GymBoard> findByUsername(Pageable pageable, String username);
    void deleteById(Long id);

}
