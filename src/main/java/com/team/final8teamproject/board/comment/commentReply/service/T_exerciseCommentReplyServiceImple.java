package com.team.final8teamproject.board.comment.commentReply.service;

import com.team.final8teamproject.board.comment.commentReply.entity.T_exerciseCommentReply;
import com.team.final8teamproject.board.comment.commentReply.repository.T_exerciseCommentReplyRepository;
import com.team.final8teamproject.board.comment.repository.T_exerciseCommentRepository;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class T_exerciseCommentReplyServiceImple implements T_exerciseCommentReplyService {

    private final T_exerciseCommentRepository tExerciseCommentRepository;

    private final T_exerciseCommentReplyRepository tExerciseCommentReplyRepository;
    @Override
    @Transactional
    public ResponseEntity<String> creatCommentRely(Long commentId, String comment, String username) {
        if(tExerciseCommentRepository.existsById(commentId)){
            T_exerciseCommentReply t_exerciseCommentReply = new T_exerciseCommentReply(comment,username,commentId);
            tExerciseCommentReplyRepository.save(t_exerciseCommentReply);
        return new ResponseEntity<>("대댓글 작성완료", HttpStatus.OK);
        }throw new CustomException(ExceptionStatus.COMMENT_NOT_EXIST);
    }
}
