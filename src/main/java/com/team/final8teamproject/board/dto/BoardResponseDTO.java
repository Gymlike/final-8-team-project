package com.team.final8teamproject.board.dto;

import com.team.final8teamproject.board.entity.T_exercise;
import com.team.final8teamproject.board.comment.dto.CommentResponseDTO;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class BoardResponseDTO {


    private  Long like=(long)0;

    private final Long id;
    private final String title;

    private final String content;

    private final String image;

    private final LocalDateTime createdDate;

    private  List<CommentResponseDTO> commentList;


    public BoardResponseDTO(Long like, T_exercise t_exercise, List<CommentResponseDTO> commentList) {
        this.like= like;
        this.id = t_exercise.getId();
        this.title = t_exercise.getTitle();
        this.content =t_exercise.getContent();
        this.image = t_exercise.getFilepath(); // 이게 image url 로 바뀌나?
        this.createdDate =t_exercise.getCreatedDate();
        this.commentList = commentList;
    }
    public BoardResponseDTO(T_exercise t_exercise) {
        this.id = t_exercise.getId();
        this.title = t_exercise.getTitle();
        this.content =t_exercise.getContent();
        this.image = t_exercise.getFilepath(); // 이게 image url 로 바뀌나?
        this.createdDate =t_exercise.getCreatedDate();
    }
}
