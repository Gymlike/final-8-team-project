package com.team.final8teamproject.gymboard.repository;

import com.team.final8teamproject.gymboard.entity.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GymAmenitiesRepository extends JpaRepository<Amenities, Long> {
}
