package com.team.final8teamproject.gymboard.service;

import com.team.final8teamproject.gymboard.dto.*;
import com.team.final8teamproject.gymboardreview.dto.GymBoardviewResponseDto;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GymPostService {

    //1.헬스장 게시글 작성

    public String createGymPost(CreatePostGymRequestDto requestDto,
                                String imgUrl,
                                String username);

    //2. 메인 페이지에서 조회
    public List<GymPostResponseDto> getGymPostAll();
    //2. 작성된 운동시설 보여주기
    public GymPostServiceImpl.Result<List<GymPostResponseDto>> getGymPost(Pageable pageRequest, String search, Integer size, Integer page);

    //3. 작성한 운동시설 선택시 세부사항
    public GymPostResponseDetailDto getGymPostDetail(Long id);
    //4. 자기가 작성한 운동시설 전부 조회
    public List<GymBoardviewResponseDto> getAllGymPost(int pageChoice, String username);

    //5. 헬스장 게시글 수정
    public void updateGymPost(GymUpdateRequestDto requestDto, String imageUrl,String username, Long id);

    //6. 헬스장 게시글 삭제
    public String deleteGymPost(Long id, String username);

    //7. 가격 할인 업데이트
    public String discountUpdate(Long id, String discount,String username);
    //8. 가격 할인 삭제
    public String deleteUpdate(Long id, String discount, String username);
    //9. 이미지 변경
    public String imageUpdate(Long id, String discount,String username);
}
