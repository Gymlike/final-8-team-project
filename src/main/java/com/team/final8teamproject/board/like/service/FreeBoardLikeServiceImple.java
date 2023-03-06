package com.team.final8teamproject.board.like.service;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.board.like.entity.FreeBoardLike;
import com.team.final8teamproject.board.like.repository.FreeBoardLikeRepository;
import com.team.final8teamproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FreeBoardLikeServiceImple implements FreeBoardLikeService {

    private final FreeBoardLikeRepository freeBoardLikeRepository;
    @Override
    @Transactional
    public ResponseEntity<String> likeBoard(BaseEntity user, Long boardId) {
        String username = user.getUsername();
        if(!freeBoardLikeRepository.existsByUsernameAndBoardId(username,boardId)){
            FreeBoardLike freeBoardLike = new FreeBoardLike(username,boardId);
            freeBoardLikeRepository.save(freeBoardLike);

            //303코드 보내서 중복요청 막고싶은데...~.~ 그러면 좋아요 취소기능을 하나더 만들어야하나?
            return new ResponseEntity<>("좋아요 증가", HttpStatus.OK);//중복요청
       }
        else{
          freeBoardLikeRepository.deleteByUsernameAndBoardId(username,boardId);

            return new ResponseEntity<>("좋아요 취소",HttpStatus.OK);
        }
//        return  new ResponseEntity<>("이미 좋아요를 눌렀습니다",HttpStatus.BAD_REQUEST);
    }

    @Override
    public Long countLike(Long boardID) {

     return  freeBoardLikeRepository.countByBoardId(boardID);
    }

    @Override
    @Transactional
    public Long checkLike(User user, Long boardId) {
        String username = user.getUsername();
        if (!freeBoardLikeRepository.existsByUsernameAndBoardId(username,boardId)) {
            return 0L;
        }else {
            return 1L;
        }
    }
}
