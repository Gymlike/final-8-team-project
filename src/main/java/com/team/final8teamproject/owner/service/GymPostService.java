package com.team.final8teamproject.owner.service;

import com.team.final8teamproject.owner.entity.GymBoard;
import com.team.final8teamproject.owner.entity.Owner;
import com.team.final8teamproject.owner.dto.CreatePostGymRequestDto;
import com.team.final8teamproject.owner.dto.GymPostResponseDto;
import com.team.final8teamproject.owner.repository.GymBoardRepository;
import com.team.final8teamproject.owner.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GymPostService {
    private final OwnerRepository ownerRepository;
    private final GymBoardRepository gymBoardRepository;

    //1.헬스장 게시글 작성
    @Transactional
    public String createGymPost(CreatePostGymRequestDto requestDto, String username) {

        Owner owner = ownerRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );

        GymBoard gymBoard = GymBoard.builder()
                .title(requestDto.getTitle())
                .username(requestDto.getUsername())
                .content(requestDto.getContents())
                .image(requestDto.getImage())
                .gymName(requestDto.getGymName())
                .ownerNumber(requestDto.getOwnerNumber())
                .region(requestDto.getRegion())
                .build();

        gymBoardRepository.save(gymBoard);

        return "등록 완료";
    }
    //2.작성된 운동시설 보여주기
    @Transactional(readOnly = true)
    public List<GymPostResponseDto> getGymPost(String userName) {
        List<GymBoard> gymBoards = gymBoardRepository.findAllByUsername(userName);

        List<GymPostResponseDto> gymPostResponseDtosList = new ArrayList<>();

        for (GymBoard gymBoard : gymBoards) {
            GymPostResponseDto gymPostResponseDto = new GymPostResponseDto(gymBoard);
            gymPostResponseDtosList.add(gymPostResponseDto);
        }
        return gymPostResponseDtosList;
    }

    //3. 자기가 작성한 운동시설 전부 조회
    @Transactional(readOnly = true)
    public List<GymPostResponseDto> getAllGymPost(int pageChoice, String username){
        Page<GymBoard> gymBoards = gymBoardRepository.findByUsername(pageableGymPostSetting(pageChoice),username);
        return gymBoards.stream().map(GymPostResponseDto::new).collect(Collectors.toList());
    }
    //3-1. 페이징 처리 메소드
    private Pageable pageableGymPostSetting(int pageChoice){
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction,"id");
        return PageRequest.of(pageChoice -1,10,sort);
    }


    //4. 헬스장 게시글 수정
    @Transactional
    public GymPostResponseDto updateGymPost(CreatePostGymRequestDto requestDto, String userName, Long id) {
        Owner owner = ownerRepository.findByUsername(userName).orElseThrow(
                () ->new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
        GymBoard gymBoard = gymBoardRepository.findByIdAndUsername(id, owner.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        gymBoard.update(requestDto);

        return new GymPostResponseDto(gymBoard);
    }


    //5. 헬스장 게시글 삭제
    @Transactional
    public String deleteGymPost(Long id, String userName) {
        Owner owner = ownerRepository.findByUsername(userName).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );

        GymBoard gymBoard = gymBoardRepository.findByIdAndUsername(id, owner.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        gymBoardRepository.deleteById(gymBoard.getId());

        return "삭제완료";
    }
}
