package com.team.final8teamproject.board.service;


import com.team.final8teamproject.board.comment.dto.CreatCommentRequestDTO;
import com.team.final8teamproject.board.dto.CreatBordRequestDTO;

import com.team.final8teamproject.base.entity.BaseEntity;

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


    CreatCommentRequestDTO creatTExerciseBord(String title, String content, String imageUrl, BaseEntity base) throws NullPointerException, IOException;


    T_exerciseServiceImple.Result getAllT_exerciseBoards(Pageable pageRequest, String search, Integer size, Integer page);

    T_exerciseBoardResponseDTO getT_exerciseBoard(Long boardId);


    ResponseEntity<String> deletePost(Long boardId, BaseEntity base);





    @Transactional
    ResponseEntity<String> editPost(Long boardId,
                                    CreatBordRequestDTO creatTExerciseBordRequestDTO,
                                    BaseEntity user,
                                    String imageUrl) throws IOException;

    T_exercise findT_exerciseBoardById(Long id);

    List<T_exerciseBoardResponseDTO> getTop3PostByLike();


   /* 파일서비스 인터페이스 만들어서～
    이거통해서。。。 갈아끼우자...
    아니면 다운하는 api만들기~!

     S3 ->주소생김
    */

}
