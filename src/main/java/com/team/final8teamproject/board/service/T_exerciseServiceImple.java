package com.team.final8teamproject.board.service;


import com.team.final8teamproject.board.entity.T_exercise;
import com.team.final8teamproject.board.reository.T_exerciseRepository;
import com.team.final8teamproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class T_exerciseServiceImple  implements  T_exerciseService{
    private final T_exerciseRepository t_exerciseRepository;

    /**
     * 오운완 게시물 생성
     * @param title  제목
     * @param content  내용
     * @param file   이게 올릴 이미지임..!
     * @param user   관계를 맺기 위해 ~ 인증된 객체 꺼내옴
     * @return    http status
     * @throws NullPointerException  ?
     * @throws IOException ?
     */
    @Transactional
    @Override
    public ResponseEntity<String> creatTExerciseBord(String title, String content, MultipartFile file, User user) throws NullPointerException, IOException {
        UUID uuid = UUID.randomUUID();
        String filename = uuid+"_"+file.getOriginalFilename();
        String filepath = System.getProperty("user.dir")+"/src/main/resources/static/files";
        File savefile = new File(filepath, filename);
        file.transferTo(savefile);

        T_exercise t_exercise = new T_exercise(title,content,filename,filepath,user);
        t_exerciseRepository.save(t_exercise);

        return new ResponseEntity<>("등록완료", HttpStatus.OK);
    }
}
