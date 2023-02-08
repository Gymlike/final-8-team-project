package com.team.final8teamproject.comment.service;

import com.team.final8teamproject.board.entity.T_exercise;
import com.team.final8teamproject.board.service.T_exerciseService;
import com.team.final8teamproject.comment.dto.CreatT_exerciseCommentRequestDTO;
import com.team.final8teamproject.comment.entity.T_exerciseComment;
import com.team.final8teamproject.comment.repository.T_exerciseCommentRepository;
import com.team.final8teamproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class T_exerciseCommentServiceImple implements T_exerciseCommentService {

    private final T_exerciseService t_exerciseService;
    private final T_exerciseCommentRepository commentRepository;
    @Override
    @Transactional
    public ResponseEntity<String> createComment(String comment, Long id, String userName) {
        t_exerciseService.findT_exerciseBoardById(id);
        T_exerciseComment t_exerciseComment = new T_exerciseComment(comment,userName);
        commentRepository.save(t_exerciseComment);
        return new ResponseEntity<>("댓글 작성완료", HttpStatus.OK);
    }
}
