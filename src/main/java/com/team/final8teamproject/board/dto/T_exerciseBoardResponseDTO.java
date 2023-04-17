package com.team.final8teamproject.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.team.final8teamproject.board.entity.T_exercise;
import com.team.final8teamproject.board.comment.dto.T_exerciseCommentResponseDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class T_exerciseBoardResponseDTO implements Serializable {

    private  Long like=(long)0;
    private Long id;
    private String title;
    private String content;
    private  String image;
    private String userName;
    private String userNickname;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime modifiedDate;

    private  List<T_exerciseCommentResponseDTO> commentList;

    public T_exerciseBoardResponseDTO(){};

    public T_exerciseBoardResponseDTO(Long like, T_exercise t_exercise, List<T_exerciseCommentResponseDTO> commentList,String nickname) {
        this.like= like;
        this.id = t_exercise.getId();
        this.title = t_exercise.getTitle();
        this.content =t_exercise.getContent();
        this.image = t_exercise.getImageUrl();
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
        this.image = filepath;
        this.modifiedDate =modifiedDate;
        this.userName = userName;
        this.userNickname = nickname;
    }
}
