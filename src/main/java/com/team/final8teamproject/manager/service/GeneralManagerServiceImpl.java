package com.team.final8teamproject.manager.service;

import com.team.final8teamproject.manager.dto.ManagerMessageDto;
import com.team.final8teamproject.manager.dto.WaitMangerResponseDto;
import com.team.final8teamproject.manager.entity.GeneralManager;
import com.team.final8teamproject.manager.entity.Manager;
import com.team.final8teamproject.manager.entity.ManagerRoleEnum;
import com.team.final8teamproject.manager.repository.GeneralManagerRepository;
import com.team.final8teamproject.manager.repository.AdminRepository;
import com.team.final8teamproject.security.jwt.JwtUtil;
import com.team.final8teamproject.security.redis.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeneralManagerServiceImpl implements GeneralManagerService {
    private final GeneralManagerRepository generalManagerRepository;
    private final AdminRepository adminRepository;
    private final RedisUtil redisUtil;
    private final JwtUtil jwtUtil;
    //실행시 테이블에 저장
    @Transactional
    @Override
    public void save(GeneralManager generalManager){

        Optional<GeneralManager> found = generalManagerRepository.findByGeneralName(generalManager.getGeneralName());
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }
        generalManagerRepository.save(generalManager);
    }
    //총 관리자 로그인
    @Transactional
    @Override
    public ManagerMessageDto login(String manager){

        return new ManagerMessageDto("로그인 성공했습니다");
    }
    //관리자 조회
    public List<WaitMangerResponseDto> waitManagerList(Pageable pageable) {
        Pageable page = pageableSetting(pageable.getPageNumber(), pageable.getPageSize());
        Page<Manager> approval = adminRepository.findByRole(page, ManagerRoleEnum.WAIT);
        return approval.stream().map(WaitMangerResponseDto::new).collect(Collectors.toList());
    }
    public Pageable pageableSetting(int page, int size){
        Sort.Direction direction = Sort.Direction.ASC;
        Sort sort = Sort.by(direction, "id");
        return PageRequest.of(page-1, size, sort);
    }
    //관리자 승급 승인
    @Transactional
    @Override
    public ManagerMessageDto approval(String manager) {
        Manager manager1 = adminRepository.findByManager(manager).orElseThrow(
                ()-> new IllegalArgumentException("해당 신청자를 찾을수 없습니다.")
        );
        manager1.approvalManager(ManagerRoleEnum.MANAGER);
        return new ManagerMessageDto(manager+"의 매니저 활동을 승인하셨습니다.");
    }


    //총 관리자 로그아웃
    @Transactional
    @Override
    public void logout(String accessToken, String general) {
        if(redisUtil.getRefreshToken(accessToken) != null){
            redisUtil.deleteRefreshToken(general);
        }
        // 레디스에 accessToken 사용못하도록 등록
        Long expiration = jwtUtil.getExpiration(accessToken);
        redisUtil.setBlackList(accessToken, "logout", expiration);
    }


}
