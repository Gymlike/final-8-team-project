package com.team.final8teamproject.board.service;

import com.team.final8teamproject.board.dto.CreatBordRequestDTO;
import com.team.final8teamproject.board.dto.T_exerciseBoardResponseDTO;
import com.team.final8teamproject.board.entity.T_exercise;
import com.team.final8teamproject.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface T_exerciseService {
    @Transactional
    ResponseEntity<String> creatTodayMealBord(String title, String content, MultipartFile file, User user) throws NullPointerException, IOException;

    List<T_exerciseBoardResponseDTO> getAllT_exerciseBoards(Pageable pageRequest, String search);

    T_exerciseBoardResponseDTO getT_exerciseBoard(Long boardId);

    ResponseEntity<String> deletePost(Long boardId, User user);

    ResponseEntity<String> editPost(Long boardId, CreatBordRequestDTO creatTExerciseBordRequestDTO, User user, MultipartFile file) throws IOException;


    T_exercise findT_exerciseBoardById(Long id);

   /* 파일서비스 인터페이스 만들어서～
    이거통해서。。。 갈아끼우자...
    아니면 다운하는 api만들기~!

     S3 ->주소생김
    */

}
