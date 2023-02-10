package com.team.final8teamproject.board.like.service;

import com.team.final8teamproject.board.like.entity.T_exerciseLike;
import com.team.final8teamproject.board.like.entity.TodayMealLike;
import com.team.final8teamproject.board.like.repository.TodayMealLikeRepository;
import com.team.final8teamproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TodayMealLikeServiceImple implements TodayMealLikeService {

    private final TodayMealLikeRepository todayMealRepository;
    @Override
    @Transactional
    public ResponseEntity<String> likeBoard(User user, Long boardId) {
        String username = user.getUsername();
        if(!todayMealRepository.existsByUsernameAndBoardId(username,boardId)){
            TodayMealLike todayMealLike = new TodayMealLike(username,boardId);
            todayMealRepository.save(todayMealLike);

            //303코드 보내서 중복요청 막고싶은데...~.~ 그러면 좋아요 취소기능을 하나더 만들어야하나?
            return new ResponseEntity<>("좋아요 증가", HttpStatus.OK);//중복요청
        }else{
            todayMealRepository.deleteByUsernameAndBoardId(username,boardId);

            return new ResponseEntity<>("좋아요 취소",HttpStatus.OK);
        }
    }

    @Override
    public Long countLike(Long boardID) {
     return  todayMealRepository.countByBoardId(boardID);
    }
}
