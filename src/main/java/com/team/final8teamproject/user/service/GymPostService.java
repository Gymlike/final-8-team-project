package com.team.final8teamproject.user.service;

import com.team.final8teamproject.owner.entity.GymBoard;
import com.team.final8teamproject.owner.entity.Owner;
import com.team.final8teamproject.user.dto.CreatePostGymRequestDto;
import com.team.final8teamproject.user.dto.GymPostResponseDto;
import com.team.final8teamproject.user.repository.GymBoardRepository;
import com.team.final8teamproject.user.repository.OwnerRepository;
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
    private final CreatePostGymRequestDto createPostGymRequestDto;

    //상품 페이징
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

    @Transactional(readOnly = true)
    public List<GymPostResponseDto> getAllGymPost(int pageChoice, String username){
        Page<GymBoard> gymBoards = gymBoardRepository.findByUsername(pageableGymPostSetting(pageChoice),username);
        return gymBoards.stream().map(GymPostResponseDto::new).collect(Collectors.toList());
    }

    private Pageable pageableGymPostSetting(int pageChoice){
        Sort.Direction direction = Sort.Direction.DESC;
        Sort sort = Sort.by(direction,"id");
        return PageRequest.of(pageChoice -1,10,sort);
    }

    //헬스장 게시글 작성
    @Transactional
    public String createGymPost(CreatePostGymRequestDto requestDto, String userName) {

        Owner owner = ownerRepository.findByOwnername(userName).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );

        GymBoard gymBoard = GymBoard.builder()
                .title(requestDto.getTitle())
                .writer(requestDto.getWriter())
                .content(requestDto.getContents())
                .image(requestDto.getImage())
                .trainer(requestDto.getTrainer())
                .region(requestDto.getRegion())
                .build();

        gymBoardRepository.save(gymBoard);

        return "등록 완료";
    }

    //헬스장 게시글 수정
    @Transactional
    public GymPostResponseDto updateGymPost(CreatePostGymRequestDto requestDto, String userName, Long id) {
        Owner owner = ownerRepository.findByOwnername(userName).orElseThrow(
                () ->new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
        GymBoard gymBoard = gymBoardRepository.findByIdAndUsername(id, owner.getOwnerName()).orElseThrow(
                () -> new IllegalArgumentException("글이 존재하지 않습니다.")
        );

        gymBoard.update(requestDto);

        return new GymPostResponseDto(gymBoard);
    }


    //글 삭제
    @Transactional
    public String deleteGymPost(Long id, String userName) {
        Owner owner = ownerRepository.findByOwnername(userName).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );

        GymBoard gymBoard = gymBoardRepository.findByIdAndUsername(id, owner.getOwnerName()).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        gymBoardRepository.deleteById(gymBoard.getId());

        return "삭제완료";
    }
}
