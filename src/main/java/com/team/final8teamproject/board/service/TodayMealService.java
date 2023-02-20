package com.team.final8teamproject.board.service;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.board.dto.CreatBordRequestDTO;
import com.team.final8teamproject.board.dto.TodayMealBoardResponseDTO;

import com.team.final8teamproject.board.entity.TodayMeal;
import com.team.final8teamproject.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TodayMealService {

    ResponseEntity<String> creatTodayMealBord(String title, String content, MultipartFile file, User user) throws NullPointerException, IOException;

    List<TodayMealBoardResponseDTO> getAllTodayBoards(Pageable pageRequest, String search);

    TodayMealBoardResponseDTO getTodayMealBoard(Long boardId);

    ResponseEntity<String> deletePost(Long boardId, BaseEntity user);

    ResponseEntity<String> editPost(Long boardId, CreatBordRequestDTO creatBordRequestDTO, User user, MultipartFile file) throws IOException;


    TodayMeal findBoardById(Long id);
}