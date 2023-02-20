package com.team.final8teamproject.board.comment.commentReply.service;

import com.team.final8teamproject.board.comment.commentReply.dto.CreatCommentReplyRequestDTO;
import com.team.final8teamproject.board.comment.commentReply.entity.T_exerciseCommentReply;
import com.team.final8teamproject.board.comment.commentReply.repository.T_exerciseCommentReplyRepository;
import com.team.final8teamproject.board.comment.entity.T_exerciseComment;
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
public class T_exerciseCommentReplyServiceImpl implements T_exerciseCommentReplyService {
    private final T_exerciseCommentRepository tExerciseCommentRepository;

    private final T_exerciseCommentReplyRepository tExerciseCommentReplyRepository;
    @Override
    @Transactional
    public ResponseEntity<String> creatCommentRely(Long commentId, String commentContent, String username,String nickname) {
        if(tExerciseCommentRepository.existsById(commentId)){
            T_exerciseComment comment = tExerciseCommentRepository.findById((commentId)).orElseThrow(()->new CustomException(ExceptionStatus.COMMENT_NOT_EXIST));
            T_exerciseCommentReply t_exerciseCommentReply = new T_exerciseCommentReply(commentContent,username,comment,nickname);
            tExerciseCommentReplyRepository.save(t_exerciseCommentReply);
        return new ResponseEntity<>("대댓글 작성완료", HttpStatus.OK);
        }throw new CustomException(ExceptionStatus.COMMENT_NOT_EXIST);
    }

    @Override
    @Transactional
    public ResponseEntity<String> updateCommentReply(CreatCommentReplyRequestDTO requestDTO, String username, Long commentID) {
        T_exerciseCommentReply commentReply = tExerciseCommentReplyRepository.findById(commentID).orElseThrow(() -> new CustomException(ExceptionStatus.COMMENT_REPLY_NOT_EXIST));
        String comment = requestDTO.getComment();
        if (commentReply.isWriter(username)) {
           commentReply.update(comment);
           return new ResponseEntity<>("대댓글 수정완료",HttpStatus.OK);
        }
        throw new CustomException(ExceptionStatus.WRONG_USER_T0_COMMENT_REPLY);
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteCommentReply(String username, Long commentId) {
        T_exerciseCommentReply commentReply = tExerciseCommentReplyRepository.findById(commentId).orElseThrow(() -> new CustomException(ExceptionStatus.COMMENT_REPLY_NOT_EXIST));
        if(commentReply.isWriter(username)){
            tExerciseCommentReplyRepository.deleteById(commentId);
            return new ResponseEntity<>("대댓글 삭제완료",HttpStatus.OK);
        }
        throw new CustomException(ExceptionStatus.WRONG_USER_T0_COMMENT_REPLY);
    }
}
