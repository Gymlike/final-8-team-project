package com.team.final8teamproject.board.dto;

import com.team.final8teamproject.board.comment.dto.T_exerciseCommentResponseDTO;
import com.team.final8teamproject.board.comment.dto.TodayMealCommentResponseDTO;
import com.team.final8teamproject.board.entity.TodayMeal;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class TodayMealBoardResponseDTO {


    private  Long like=(long)0;

    private final Long id;
    private final String title;

    private final String content;

    private final String image;

    private final LocalDateTime createdDate;

    private  List<TodayMealCommentResponseDTO> commentList;


    public TodayMealBoardResponseDTO(Long like, TodayMeal todayMeal, List<TodayMealCommentResponseDTO> commentList) {
        this.like= like;
        this.id = todayMeal.getId();
        this.title = todayMeal.getTitle();
        this.content =todayMeal.getContent();
        this.image = todayMeal.getFilepath(); // 이게 image url 로 바뀌나?
        this.createdDate =todayMeal.getCreatedDate();
        this.commentList = commentList;
    }
    public TodayMealBoardResponseDTO(TodayMeal todayMeal) {
        this.id = todayMeal.getId();
        this.title = todayMeal.getTitle();
        this.content =todayMeal.getContent();
        this.image = todayMeal.getFilepath(); // 이게 image url 로 바뀌나?
        this.createdDate =todayMeal.getCreatedDate();
    }
}
