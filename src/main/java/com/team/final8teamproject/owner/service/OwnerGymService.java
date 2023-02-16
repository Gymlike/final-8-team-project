package com.team.final8teamproject.owner.service;

import com.team.final8teamproject.owner.dto.GymRequestDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OwnerGymService {

    @Transactional
    public void crateGym(String username, GymRequestDto requestDto){

    }
}
