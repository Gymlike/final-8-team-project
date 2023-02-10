package com.team.final8teamproject.manager.service;

import com.team.final8teamproject.manager.dto.ApprovalRequestDto;
import com.team.final8teamproject.manager.dto.ManagerMessageDto;
import com.team.final8teamproject.manager.dto.WaitMangerResponseDto;
import com.team.final8teamproject.manager.entity.GeneralManager;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GeneralManagerService {
    void save(GeneralManager generalManager);
    ManagerMessageDto login(String manager);
    ManagerMessageDto approval(String manager);
    public List<WaitMangerResponseDto> waitManagerList(Pageable pageable);
    void logout(String accessToken, String username);
}
