package com.team.final8teamproject.manager.service;

import com.team.final8teamproject.manager.dto.ManagerResponseDto;
import com.team.final8teamproject.user.entity.UserRoleEnum;

import java.util.List;

public interface ManagerService {

    List<ManagerResponseDto> getStandByList(UserRoleEnum userRoleEnum);

    void allowStandBy(Long id);

    void refuseStandBy(Long id);

}
