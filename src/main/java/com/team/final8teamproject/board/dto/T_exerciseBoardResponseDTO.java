package com.team.final8teamproject.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.team.final8teamproject.board.entity.T_exercise;
import com.team.final8teamproject.board.comment.dto.T_exerciseCommentResponseDTO;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class T_exerciseBoardResponseDTO {


    private  Long like=(long)0;

    private final Long id;
    private final String title;

    private final String content;

    private final String image;

    private final String userName;

    private final String userNickname;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private final LocalDateTime modifiedDate;

    private  List<T_exerciseCommentResponseDTO> commentList;


    public T_exerciseBoardResponseDTO(Long like, T_exercise t_exercise, List<T_exerciseCommentResponseDTO> commentList,String nickname) {
        this.like= like;
        this.id = t_exercise.getId();
        this.title = t_exercise.getTitle();
        this.content =t_exercise.getContent();
        this.image = t_exercise.getImageUrl(); // 이게 image url 로 바뀌나?
        this.modifiedDate =t_exercise.getModifiedDate();
        this.userName = t_exercise.getUser().getUsername();
        this.userNickname = nickname;
        this.commentList = commentList;
    }
    public T_exerciseBoardResponseDTO(Long like,Long postID,String title,String content,String filepath,LocalDateTime modifiedDate,String userName,String nickname) {
        this.like = like;
        this.id = postID;
        this.title = title;
        this.content =content;
        this.image = filepath; // 이게 image url 로 바뀌나?
        this.modifiedDate =modifiedDate;
        this.userName = userName;
        this.userNickname = nickname;
    }
}
