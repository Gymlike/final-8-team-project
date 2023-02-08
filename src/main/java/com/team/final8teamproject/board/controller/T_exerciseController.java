package com.team.final8teamproject.board.controller;

import com.team.final8teamproject.board.dto.CreatT_exerciseBordRequestDTO;
import com.team.final8teamproject.board.service.T_exerciseService;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.user.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/t-exercise")
public class T_exerciseController {
    private final T_exerciseService t_exerciseService;


    //오운완 게시판 생성
    @PostMapping("/")
    public ResponseEntity<String> creatT_exerciseBord(@RequestPart("creatTExerciseBordRequestDTO") @Valid CreatT_exerciseBordRequestDTO creatTExerciseBordRequestDTO,
                                                      @RequestPart("file") MultipartFile file,
                                                      @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

        String content = creatTExerciseBordRequestDTO.getContent();
        String title = creatTExerciseBordRequestDTO.getTitle();
        User user = userDetails.getUser();

        return t_exerciseService.creatTExerciseBord(title,content,file,user);
    }
}
