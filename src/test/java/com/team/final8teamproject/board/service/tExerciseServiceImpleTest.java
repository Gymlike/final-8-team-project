package com.team.final8teamproject.board.service;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.board.comment.dto.CreatCommentRequestDTO;
import com.team.final8teamproject.board.comment.service.T_exerciseCommentService;
import com.team.final8teamproject.board.dto.ResultDTO;
import com.team.final8teamproject.board.entity.T_exercise;
import com.team.final8teamproject.board.like.service.T_exerciseLikeService;
import com.team.final8teamproject.board.repository.T_exerciseRepository;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class tExerciseServiceImpleTest {

    //test 주체?
    T_exerciseService t_exerciseService;

    //협력자 가짜객체?
    @Mock
    T_exerciseRepository t_exerciseRepository;

    @Mock
    T_exerciseCommentService t_exerciseCommentService;

    @Mock
    T_exerciseLikeService t_exerciseLikeService;

    @Mock
    UserService userService;

    @Mock
    BaseEntity user;

    @Mock
    Pageable pageRequest;

    //test를 실행하기 전마다 가짜객체를 주입시켜준다!..
    @BeforeEach
    void  setUp(){
        t_exerciseService = new T_exerciseServiceImple(t_exerciseRepository,t_exerciseCommentService,t_exerciseLikeService,userService);
    }
    @Test
    @DisplayName("오운완게시글 생성확인")
    void creatTExerciseBord() throws IOException {

        //given
        T_exercise t_exercise = new T_exercise("제목","내용","이미지주소",user);
        //프라이빗필드 채워줌..
        ReflectionTestUtils.setField(t_exercise,"id",1L);

        T_exercise tExercise = t_exerciseRepository.save(t_exercise);


        Mockito.when(tExercise).thenReturn(t_exercise);

        //when
        CreatCommentRequestDTO response = t_exerciseService.creatTExerciseBord("제목", "내용", "주소", user);

        //then
        assertThat(response.getComment()).isEqualTo("등록완료");
    }


    @Test
    @DisplayName("전체 게시글 조히")
    void getTotalBoard() throws IOException{
        //given

        T_exercise t_exercise = new T_exercise("제목","내용","이미지주소",user);
        ReflectionTestUtils.setField(t_exercise,"id",1L);

        T_exercise t_exercise2 = new T_exercise("제목","내용","이미지주소",user);
        ReflectionTestUtils.setField(t_exercise2,"id",2L);

        T_exercise savet_exercise1 = t_exerciseRepository.save(t_exercise);
        T_exercise savet_exercise2 = t_exerciseRepository.save(t_exercise2);

        //when
        ResultDTO response = t_exerciseService.getAllT_exerciseBoards(pageRequest, "", 2, 1);

        //then

        assertThat(response.getResult().getCountPage()).isEqualTo(2);
    }
}