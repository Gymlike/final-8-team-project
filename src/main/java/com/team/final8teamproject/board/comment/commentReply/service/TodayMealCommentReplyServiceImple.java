package com.team.final8teamproject.board.comment.commentReply.service;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.board.comment.commentReply.dto.CreatCommentReplyRequestDTO;
import com.team.final8teamproject.board.comment.commentReply.entity.TodayMealCommentReply;
import com.team.final8teamproject.board.comment.commentReply.repository.TodayMealCommentReplyRepository;
import com.team.final8teamproject.board.comment.entity.TodayMealComment;
import com.team.final8teamproject.board.comment.repository.TodayMealCommentRepository;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import com.team.final8teamproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodayMealCommentReplyServiceImple implements TodayMealCommentReplyService {

    private final TodayMealCommentRepository todayMealCommentRepository;

    private final TodayMealCommentReplyRepository todayMealCommentReplyRepository;

    @Override
    @Transactional
    public ResponseEntity<String> creatCommentRely(Long commentId, String commentContent, String username,String userNickname) {
        if(todayMealCommentRepository.existsById(commentId)){
            TodayMealComment comment = todayMealCommentRepository.findById((commentId)).orElseThrow(()->new CustomException(ExceptionStatus.COMMENT_NOT_EXIST));
            TodayMealCommentReply t_exerciseCommentReply = new TodayMealCommentReply(commentContent,username,comment,userNickname);
            todayMealCommentReplyRepository.save(t_exerciseCommentReply);
        return new ResponseEntity<>("대댓글 작성완료", HttpStatus.OK);
        }throw new CustomException(ExceptionStatus.COMMENT_NOT_EXIST);
    }

    @Override
    @Transactional
    public ResponseEntity<String> updateCommentReply(CreatCommentReplyRequestDTO requestDTO,String writer, Long commentID) {
        TodayMealCommentReply commentReply = todayMealCommentReplyRepository.findById(commentID).orElseThrow(() -> new CustomException(ExceptionStatus.COMMENT_REPLY_NOT_EXIST));

        String comment = requestDTO.getComment();
        if (commentReply.isWriter(writer)) {
           commentReply.update(comment);
           return new ResponseEntity<>("대댓글 수정완료",HttpStatus.OK);
        }
        throw new CustomException(ExceptionStatus.WRONG_USER_T0_COMMENT_REPLY);
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteCommentReply(String  writer, Long commentId) {

        TodayMealCommentReply commentReply = todayMealCommentReplyRepository.findById(commentId).orElseThrow(() -> new CustomException(ExceptionStatus.COMMENT_REPLY_NOT_EXIST));
        if(commentReply.isWriter(writer)){
            todayMealCommentReplyRepository.deleteById(commentId);

            return new ResponseEntity<>("대댓글 삭제완료",HttpStatus.OK);
        }
        throw new CustomException(ExceptionStatus.WRONG_USER_T0_COMMENT_REPLY);
    }
}
