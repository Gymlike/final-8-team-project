package com.team.final8teamproject.gymboard.service;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.base.repository.BaseRepository;
import com.team.final8teamproject.gymboard.dto.GymBoardReviewRequestDto;
import com.team.final8teamproject.gymboard.dto.GymBoardReviewResponseDto;
import com.team.final8teamproject.gymboard.dto.GymReviewUpdateRequestDto;
import com.team.final8teamproject.gymboard.entity.GymReview;
import com.team.final8teamproject.gymboard.repository.GymReviewRepository;
import com.team.final8teamproject.owner.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class GymBoardReviewService {

    private final OwnerRepository ownerRepository;

    private final BaseRepository baseRepository;
    private final GymReviewRepository gymReviewRepository;

    //1. 리뷰 작성
    @Transactional
    public void writeReview(Long id,GymBoardReviewRequestDto requestDto, String username){

        BaseEntity base = baseRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")        );
        try {
            GymReview gymReview = GymReview.builder()
                    .gymId(id)
                    .comment(requestDto.getComment())
                    .username(username)
                    .build();

            gymReviewRepository.save(gymReview);
        }catch (Exception e){
            throw new NoSuchElementException("서버에 문제가 발생하였습니다.");
        }
    }

    //2. 작성된 리뷰 조회
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

    @Transactional
    public String putReview(GymReviewUpdateRequestDto requestDto, String username, Long id){


        BaseEntity base = baseRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
        GymReview gymReview = gymReviewRepository.findByIdAndUsername(id, username).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        gymReview.update(requestDto.getComment());
        return "수정 성공";
    }

    //4. 작성된 리뷰 삭제
    @Transactional
    public String deleteReview(String username, Long id){


        BaseEntity base = baseRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
        );
        GymReview gymReview = gymReviewRepository.findByIdAndUsername(id, username).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        gymReviewRepository.deleteById(gymReview.getId());

        return "삭제 성공";
    }

}
