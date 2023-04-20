package com.team.final8teamproject.scheduler;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team.final8teamproject.gymboard.entity.GymBoard;
import com.team.final8teamproject.gymboard.entity.QGymBoard;
import com.team.final8teamproject.gymboard.repository.GymBoardRepository;
import com.team.final8teamproject.gymboardreview.entity.GymReview;
import com.team.final8teamproject.gymboardreview.entity.QGymReview;
import com.team.final8teamproject.gymboardreview.repository.GymReviewRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DslService {

    private final GymBoardRepository gymBoardRepository;
    private final GymReviewRepository gymReviewRepository;
    private final EntityManager entityManager;
    //1. Transactional을 넣어준이유
    //중간에 하다가 하나라도 잘못됬다면 되돌려야 하기 때문이다.
    @Transactional
    public void dslRatingReview() {
        QGymBoard qGymBoard = QGymBoard.gymBoard;
        QGymReview qGymReview = QGymReview.gymReview;

        List<Tuple> result = new JPAQueryFactory(entityManager)
                .select(qGymBoard.id, qGymReview.rating.count(), qGymReview.rating.sum())
                .from(qGymBoard)
                .leftJoin(qGymReview).on(qGymBoard.id.eq(qGymReview.gymId))
                .groupBy(qGymBoard.id)
                .fetch();

        List<GymBoard> gymBoardsToUpdate = new ArrayList<>();

        result.forEach(tuple -> {
            Long gymId = tuple.get(qGymBoard.id);
            Long ratingCount = tuple.get(qGymReview.rating.count());
            Long ratingSum = tuple.get(qGymReview.rating.sum());

            if (gymId != null && ratingCount != null && ratingSum != null && ratingCount != 0) {
                gymBoardRepository.findById(gymId).ifPresent(gymBoard -> {
                    long ratingAvg = (long) (ratingSum / (double) ratingCount);
                    gymBoard.updateRating(ratingAvg);
                    gymBoardsToUpdate.add(gymBoard);
                });
            }
        });
        gymBoardRepository.saveAll(gymBoardsToUpdate);
    }

    public void ratingReview() {
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
            gymBoard.updateRating(total/div);
        }
    }
}
