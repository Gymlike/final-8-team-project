package com.team.final8teamproject.board.service;

import com.team.final8teamproject.board.dto.BoardResponseDTO;
import com.team.final8teamproject.board.dto.CreatBordRequestDTO;
import com.team.final8teamproject.board.entity.T_exercise;
import com.team.final8teamproject.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TodayMealService {

    ResponseEntity<String> creatTodayMealBord(String title, String content, MultipartFile file, User user) throws NullPointerException, IOException;

    List<BoardResponseDTO> getAllT_exerciseBoards(Pageable pageRequest, String search);

    BoardResponseDTO getT_exerciseBoard(Long boardId);

    ResponseEntity<String> deleteSalePost(Long boardId, User user);

    ResponseEntity<String> editSalePost(Long boardId, CreatBordRequestDTO creatTExerciseBordRequestDTO, User user, MultipartFile file) throws IOException;


    T_exercise findT_exerciseBoardById(Long id);
}
