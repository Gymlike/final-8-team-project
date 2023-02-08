package com.team.final8teamproject.board.service;

import com.team.final8teamproject.user.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface T_exerciseService {
    @Transactional
    ResponseEntity<String> creatTExerciseBord(String title, String content, MultipartFile file, User user) throws NullPointerException, IOException;
}
