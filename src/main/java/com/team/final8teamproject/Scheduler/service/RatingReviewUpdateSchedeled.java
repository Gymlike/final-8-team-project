package com.team.final8teamproject.Scheduler.service;

import com.team.final8teamproject.base.repository.BaseRepository;
import com.team.final8teamproject.gymboard.entity.GymBoard;
import com.team.final8teamproject.gymboard.repository.GymBoardRepository;
import com.team.final8teamproject.gymboardreview.entity.GymReview;
import com.team.final8teamproject.gymboardreview.repository.GymReviewRepository;
import com.team.final8teamproject.gymboardreview.service.GymBoardReviewServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingReviewUpdateSchedeled {
    private final GymBoardRepository gymBoardRepository;
    private final GymBoardReviewServiceImpl gymBoardReviewServiceImpl;
    private final BaseRepository baseRepository;
    private final GymReviewRepository gymReviewRepository;


    //7. 평점 업데이트
    @Transactional
    @PostConstruct
    //1분 주기로 실행된다.
    @Scheduled(fixedDelay = 60000)
    public void ratingReviewUpdate() {
        List<GymBoard> gymBoards = gymBoardRepository.findAll();
        if(gymBoards.isEmpty()){
            return;
        }
        List<GymReview> gymReview = gymReviewRepository.findAll();
        if(gymReview.isEmpty()){
            return;
        }
        HashMap<Long, Long> rating = new HashMap<>();
        HashMap<Long, Long> rating_count = new HashMap<>();
        //맵 2개 쓸바에. 다른게 좋으려나?
        for(GymReview gymReview1 : gymReview){
            Long key = gymReview1.getGymId();
            Long value = gymReview1.getRating();
            rating.put(key ,rating.getOrDefault(key,0L)+value);
            rating_count.put(key ,rating_count.getOrDefault(key,0L)+1);
        }
        for (GymBoard gymBoard : gymBoards) {
            if(rating.get(gymBoard.getId()) == null){
                continue;
            }
            Long total = rating.get(gymBoard.getId());
            Long div = rating_count.get(gymBoard.getId());
            gymBoard.ratingUpdate(total/div);
        }
    }
}
