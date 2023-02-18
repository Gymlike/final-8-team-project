package com.team.final8teamproject.board.like.service;

import com.team.final8teamproject.board.like.entity.T_exerciseLike;
import com.team.final8teamproject.board.like.repository.T_exerciseLikeRepository;
import com.team.final8teamproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;

@Service
@RequiredArgsConstructor
public class T_exerciseLikeServiceImple implements T_exerciseLikeService {

    private final T_exerciseLikeRepository tExerciseLikeRepository;
    @Override
    @Transactional
    public ResponseEntity<String> likeBoard(User user, Long boardId) {
        String username = user.getUsername();
        if(!tExerciseLikeRepository.existsByUsernameAndBoardId(username,boardId)){
            T_exerciseLike tExerciseLike = new T_exerciseLike(username,boardId);
            tExerciseLikeRepository.save(tExerciseLike);

            //303코드 보내서 중복요청 막고싶은데...~.~ 그러면 좋아요 취소기능을 하나더 만들어야하나?
            return new ResponseEntity<>("좋아요 증가", HttpStatus.OK);//중복요청
        }else{
            tExerciseLikeRepository.deleteByUsernameAndBoardId(username,boardId);

            return new ResponseEntity<>("좋아요 취소",HttpStatus.OK);
        }
    }

    @Override
    public Long countLike(Long boardID) {

     return  tExerciseLikeRepository.countByBoardId(boardID);
    }
}
