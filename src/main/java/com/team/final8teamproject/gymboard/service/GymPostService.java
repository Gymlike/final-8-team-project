package com.team.final8teamproject.gymboard.service;

import com.team.final8teamproject.base.repository.BaseRepository;
import com.team.final8teamproject.gymboard.dto.*;
import com.team.final8teamproject.gymboard.entity.GymBoard;
import com.team.final8teamproject.gymboard.repository.GymBoardRepository;
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
    private final GymBoardReviewService gymBoardReviewService;
    private final BaseRepository baseRepository;
    //1.헬스장 게시글 작성
    @Transactional
    public String createGymPost(CreatePostGymRequestDto requestDto, String username) {
        baseRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
        GymBoard gymBoard = GymBoard.GymBoard()
                .title(requestDto.getTitle())
                .content(requestDto.getContents())
                .username(username)
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
    public List<GymPostResponseDto> getGymPost() {
        List<GymBoard> gymBoards = gymBoardRepository.findAll();

        List<GymPostResponseDto> gymPostResponseDetailDtosList = new ArrayList<>();

        for (GymBoard gymBoard : gymBoards) {
            GymPostResponseDto gymPostResponseDetailDto = new GymPostResponseDto(gymBoard);
            gymPostResponseDetailDtosList.add(gymPostResponseDetailDto);
        }
        return gymPostResponseDetailDtosList;
    }

    //3. 작성한 운동시설 선택시 세부사항
    @Transactional(readOnly = true)
    public GymPostResponseDetailDto getGymPostDetail(Long id) {
        GymBoard gymBoards = gymBoardRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("게시글이 삭제되어 찾을수 없습니다.")
        );
        List<GymBoardReviewResponseDto> review = gymBoardReviewService.getReview(gymBoards.getId());
        return new GymPostResponseDetailDto(gymBoards, review);
    }
    //4. 자기가 작성한 운동시설 전부 조회
    @Transactional(readOnly = true)
    public List<GymBoardviewResponseDto> getAllGymPost(int pageChoice, String username){
        Page<GymBoard> gymBoards = gymBoardRepository.findByUsername(pageableGymPostSetting(pageChoice),username);
        return gymBoards.stream().map(GymBoardviewResponseDto::new).collect(Collectors.toList());
    }
    //4-1. 페이징 처리 메소드
    private Pageable pageableGymPostSetting(int pageChoice){
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction,"id");
        return PageRequest.of(pageChoice -1,9,sort);
    }


    //5. 헬스장 게시글 수정
    @Transactional
    public GymPostPutResponseDto updateGymPost(CreatePostGymRequestDto requestDto, String username, Long id) {

        baseRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );

        GymBoard gymBoard = gymBoardRepository.findByIdAndUsername(id, username).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );

        gymBoard.update(requestDto, username);

        return new GymPostPutResponseDto(gymBoard);
    }


    //6. 헬스장 게시글 삭제
    @Transactional
    public String deleteGymPost(Long id, String username) {

        baseRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );

        GymBoard gymBoard = gymBoardRepository.findByIdAndUsername(id, username).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        gymBoardRepository.deleteById(gymBoard.getId());

        return "삭제완료";
    }
}
