package com.team.final8teamproject.scheduler.service;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.final8teamproject.base.repository.BaseRepository;
import com.team.final8teamproject.gymboard.entity.GymBoard;
import com.team.final8teamproject.gymboard.entity.QGymBoard;
import com.team.final8teamproject.gymboard.repository.GymBoardRepository;
import com.team.final8teamproject.gymboardreview.entity.QGymReview;
import com.team.final8teamproject.gymboardreview.repository.GymReviewRepository;
import com.team.final8teamproject.gymboardreview.service.GymBoardReviewServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingReviewUpdateSchedeled {
    private final GymBoardRepository gymBoardRepository;
    private final GymBoardReviewServiceImpl gymBoardReviewServiceImpl;
    private final BaseRepository baseRepository;
    private final GymReviewRepository gymReviewRepository;

    private final EntityManager entityManager;
    //1. 평점 업데이트
    //JPQL과 성능 비교를 하기위해서 만듬
    @Transactional
    @PostConstruct
    //1분 주기로 실행된다.
    @Scheduled(fixedDelay = 6000000)
    public void ratingReviewUpdate() {
        QGymBoard qGymBoard = QGymBoard.gymBoard;
        QGymReview qGymReview = QGymReview.gymReview;

        List<Tuple> result = new JPAQueryFactory(entityManager)
                .from(qGymBoard)
                .leftJoin(qGymReview)
                .on(qGymBoard.id.eq(qGymReview.gymId))
                .groupBy(qGymBoard.id)
                .select(qGymBoard.id, qGymReview.rating.count(), qGymReview.rating.sum())
                .fetch();

        for (Tuple tuple : result) {
            Long gymId = tuple.get(qGymBoard.id);
            Long ratingCount = tuple.get(qGymReview.rating.count());
            Long ratingSum = tuple.get(qGymReview.rating.sum());

            if (ratingCount == null || ratingSum == null || ratingCount == 0) {
                continue;
            }

            long ratingAvg = (long) (ratingSum / (double) ratingCount);
            assert gymId != null;
            Optional<GymBoard> optionalGymBoard = gymBoardRepository.findById(gymId);
            optionalGymBoard.ifPresent(gymBoard -> gymBoard.updateRating(ratingAvg));
        }
    }




//    @Transactional
//    @PostConstruct
//    //1분 주기로 실행된다.
//    @Scheduled(fixedDelay = 600000)
//    public void ratingReviewUpdate() {
//        List<GymBoard> gymBoards = gymBoardRepository.findAll();
//        if(gymBoards.isEmpty()){
//            return;
//        }
//        List<GymReview> gymReview = gymReviewRepository.findAll();
//        if(gymReview.isEmpty()){
//            return;
//        }
//        HashMap<Long, Long> rating = new HashMap<>();
//        HashMap<Long, Long> rating_count = new HashMap<>();
//        //맵 2개 쓸바에. 다른게 좋으려나?
//        for(GymReview gymReview1 : gymReview){
//            Long key = gymReview1.getGymId();
//            Long value = gymReview1.getRating();
//            rating.put(key ,rating.getOrDefault(key,0L)+value);
//            rating_count.put(key ,rating_count.getOrDefault(key,0L)+1);
//        }
//        for (GymBoard gymBoard : gymBoards) {
//            if(rating.get(gymBoard.getId()) == null){
//                continue;
//            }
//            Long total = rating.get(gymBoard.getId());
//            Long div = rating_count.get(gymBoard.getId());
//            gymBoard.ratingUpdate(total/div);
//        }
//    }
}


 /* 참조형 해보기 default를 활용해서 더하기가 안됨
        List<GymBoard> gymBoards = gymBoardRepository.findAll();
        if(gymBoards.isEmpty()){
            throw new IllegalArgumentException("작성된 운동시설이 없습니다.");
        }
        List<GymReview> gymReview = gymReviewRepository.findAll();
        if(gymReview.isEmpty()){
            throw new IllegalArgumentException("리뷰가 없습니다.");
        }
        Map<Long, RatingDto> rating = new HashMap<>();
        for(GymReview gymReview1 : gymReview){
            Long key = gymReview1.getGymId();
            Long value = gymReview1.getRating();
            RatingDto ratingDto = new RatingDto(value, 1L);
            rating.put(key , ratingDto);
        }
        for (GymBoard gymBoard : gymBoards) {
            if(rating.get(gymBoard.getId()) == null){
                continue;
            }
            RatingDto ratingDto = rating.get(gymBoard.getId());
            gymBoard.ratingUpdate(ratingDto.getTotal()/ratingDto.getCount());
        }
 */
