package com.team.final8teamproject.manager.service;

import com.team.final8teamproject.manager.dto.*;
import com.team.final8teamproject.manager.entity.GeneralManager;
import com.team.final8teamproject.user.dto.LoginResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GeneralManagerService {
    void save(GeneralManager generalManager);
    LoginResponseDto login(ManagerLoginRequestDto requestDto);
    ManagerMessageDto approval(String manager);
    public List<WaitMangerResponseDto> waitManagerList(Pageable pageable);
    void logout(String accessToken, String username);
}
