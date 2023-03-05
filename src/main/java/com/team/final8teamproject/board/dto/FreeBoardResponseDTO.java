package com.team.final8teamproject.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.team.final8teamproject.board.comment.dto.FreeBoardCommentResponseDTO;
import com.team.final8teamproject.board.entity.FreeBoard;
import com.team.final8teamproject.board.entity.T_exercise;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class FreeBoardResponseDTO {


    private  Long like=(long)0;

    private final Long id;
    private final String title;

    private final String content;

    private final String image;

    private final String userName;

    private final String userNickname;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm")
    private final LocalDateTime modifiedDate;

    private  List<FreeBoardCommentResponseDTO> commentList;


    public FreeBoardResponseDTO(Long like, FreeBoard freeBoard, List<FreeBoardCommentResponseDTO> commentList, String nickname) {
        this.like= like;
        this.id = freeBoard.getId();
        this.title = freeBoard.getTitle();
        this.content =freeBoard.getContent();
        this.image = freeBoard.getImageUrl(); // 이게 image url 로 바뀌나?
        this.modifiedDate =freeBoard.getModifiedDate();
        this.userName = freeBoard.getUser().getUsername();
        this.userNickname = nickname;
        this.commentList = commentList;
    }
    public FreeBoardResponseDTO(Long like, Long postID, String title, String content, String filepath, LocalDateTime modifiedDate, String userName, String nickname) {
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
