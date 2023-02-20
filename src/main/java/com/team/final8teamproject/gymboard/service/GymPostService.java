package com.team.final8teamproject.gymboard.service;

import com.team.final8teamproject.gymboard.dto.CreatePostGymRequestDto;
import com.team.final8teamproject.gymboard.dto.GymPostPutResponseDto;
import com.team.final8teamproject.gymboard.dto.GymPostResponseDetailDto;
import com.team.final8teamproject.gymboard.dto.GymPostResponseDto;
import com.team.final8teamproject.gymboard.entity.GymBoard;
import com.team.final8teamproject.gymboardreview.dto.GymBoardReviewResponseDto;
import com.team.final8teamproject.gymboardreview.dto.GymBoardviewResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public interface GymPostService {

    //1.헬스장 게시글 작성
    public String createGymPost(CreatePostGymRequestDto requestDto,
                                MultipartFile file,
                                String username) throws NullPointerException, IOException;
    //2. 작성된 운동시설 보여주기
    public List<GymPostResponseDto> getGymPost();

    //3. 작성한 운동시설 선택시 세부사항
    public GymPostResponseDetailDto getGymPostDetail(Long id);
    //4. 자기가 작성한 운동시설 전부 조회
    public List<GymBoardviewResponseDto> getAllGymPost(int pageChoice, String username);

    //5. 헬스장 게시글 수정
    public GymPostPutResponseDto updateGymPost(CreatePostGymRequestDto requestDto, String username, Long id);



    //6. 헬스장 게시글 삭제
    public String deleteGymPost(Long id, String username);
}
