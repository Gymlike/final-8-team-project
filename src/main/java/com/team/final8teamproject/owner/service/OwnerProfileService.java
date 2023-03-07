package com.team.final8teamproject.owner.service;

import com.team.final8teamproject.owner.dto.OwnerProfileModifyRequestDto;
import com.team.final8teamproject.owner.dto.OwnerProfileResponseDto;

public interface OwnerProfileService {

    void modifyMyOwnerProfile(OwnerProfileModifyRequestDto ownerProfileModifyRequestDto, Long id,String imageURL);

    OwnerProfileResponseDto getMyOwnerProfile(Long id);
}
