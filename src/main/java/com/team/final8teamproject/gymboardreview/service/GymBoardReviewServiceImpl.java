package com.team.final8teamproject.gymboardreview.service;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.base.repository.BaseRepository;
import com.team.final8teamproject.gymboardreview.dto.GymBoardReviewRequestDto;
import com.team.final8teamproject.gymboardreview.dto.GymBoardReviewResponseDto;
import com.team.final8teamproject.gymboardreview.dto.GymReviewUpdateRequestDto;
import com.team.final8teamproject.gymboardreview.entity.GymDeleteReview;
import com.team.final8teamproject.gymboardreview.entity.GymReview;
import com.team.final8teamproject.gymboardreview.repository.GymDeleteReviewRepository;
import com.team.final8teamproject.gymboardreview.repository.GymReviewRepository;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class GymBoardReviewServiceImpl implements GymBoardReviewService{
//    private final OwnerRepository ownerRepository;
    private final BaseRepository baseRepository;
    private final GymReviewRepository gymReviewRepository;
    private final GymDeleteReviewRepository gymDeleteReviewRepository;

    //1. 리뷰 작성
    @Override
    @Transactional
    public void writeReview(Long id,GymBoardReviewRequestDto requestDto, String username){

        BaseEntity base = baseRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        try {
            GymReview gymReview = GymReview.builder()
                    .gymId(id)
                    .comment(requestDto.getComment())
                    .username(username)
                    .rating(requestDto.getRating())
                    .build();

            gymReviewRepository.save(gymReview);
        }catch (Exception e){
            throw new NoSuchElementException("서버에 문제가 발생하였습니다.");
        }
    }
    //2. 작성된 리뷰 조회
    @Override
    @Transactional(readOnly = true)
    public List<GymBoardReviewResponseDto> getReview(Long gymId){
        List<GymReview> reviews = gymReviewRepository.findByGymId(gymId);
        List<GymBoardReviewResponseDto> gymBoards = new ArrayList<>();
        for (GymReview gymReview : reviews) {
            GymBoardReviewResponseDto gymBoardReviewResponseDto = new GymBoardReviewResponseDto(gymReview);
            gymBoards.add(gymBoardReviewResponseDto);
        }
        return gymBoards;
    }

    //3. 작성된 리뷰 수정

    @Override
    @Transactional
    public String putReview(GymReviewUpdateRequestDto requestDto, String username, Long id){
        BaseEntity base = baseRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
        GymReview gymReview = gymReviewRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        if(base.getRole().equals(UserRoleEnum.MANAGER) || !gymReview.getUsername().equals(username)){
            throw new IllegalArgumentException("작성한 사용자만 삭제할수있습니다.");
        }
        gymReview.updateReview(requestDto.getComment());
        return "수정 성공";
    }
    //4. 작성된 리뷰 삭제
    @Override
    @Transactional
    public String deleteReview(String username, Long id){
        BaseEntity base = baseRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
        GymReview gymReview = gymReviewRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        if(base.getRole().equals(UserRoleEnum.MANAGER) || !gymReview.getUsername().equals(username)){
            throw new IllegalArgumentException("작성한 사용자만 삭제할수있습니다.");
        }
        GymDeleteReview gymDeleteReview = GymDeleteReview.builder()
                        .username(gymReview.getUsername())
                                .comment(gymReview.getComment())
                                        .gymId(gymReview.getGymId())
                .reviewId(gymReview.getId())
                .build();

        gymDeleteReviewRepository.save(gymDeleteReview);
        gymReviewRepository.deleteById(gymReview.getId());
        return "삭제 성공";
    }
}
