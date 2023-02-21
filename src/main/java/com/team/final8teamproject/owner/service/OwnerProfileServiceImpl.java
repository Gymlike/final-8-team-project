package com.team.final8teamproject.owner.service;

import com.team.final8teamproject.owner.dto.OwnerProfileModifyRequestDto;
import com.team.final8teamproject.owner.dto.OwnerProfileResponseDto;
import com.team.final8teamproject.owner.entity.Owner;
import com.team.final8teamproject.owner.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerProfileServiceImpl implements OwnerProfileService {
    private final OwnerRepository ownerRepository;
    private final PasswordEncoder passwordEncoder;

    //프로필 수정
    @Override
    @Transactional
    public void modifyMyOwnerProfile(OwnerProfileModifyRequestDto dto, Long id) {
        Owner owner = getOwnerById(id);
        String password = passwordEncoder.encode(dto.getPassword());
        owner.modifyOwnerProfile(password, dto.getNickname(), dto.getPhoneNumber(), dto.getEmail(), dto.getStoreName(), dto.getOwnerNumber(), dto.getImage());
    }

    //프로필 조회
    @Override
    @Transactional
    public OwnerProfileResponseDto getMyOwnerProfile(Long id) {
        Owner owner = getOwnerById(id);
        return OwnerProfileResponseDto.of(owner);

    }

    @Transactional
    public Owner getOwnerById(Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isEmpty()) {
            throw new NoSuchElementException("오너프로필을 찾을 수 없습니다");
        }
        return owner.get();
    }
}
