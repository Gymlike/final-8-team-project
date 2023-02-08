package com.team.final8teamproject.board.entity;

import com.team.final8teamproject.board.dto.CreatT_exerciseBordRequestDTO;
import com.team.final8teamproject.share.TimeStamp;
import com.team.final8teamproject.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter
@NoArgsConstructor
public class T_exercise extends TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

//    private String imageUrl;

    private String filename;

    private String filepath;


    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public T_exercise(String title, String content, String filename, String filepath,User user) {
        this.title = title;
        this.content = content;
        this.filename = filename;
        this.filepath = filepath;
        this.user = user;
    }

    public boolean isWriter(Long userid) {

        return this.user.isUserId(userid);

    }

    public void editSalePost(String title,String content,String filename,String filepath) {
        this.title = title;
        this.content = content;
        this.filename = filename;
        this.filepath = filepath;
    }


    public String getWriterName() {

        return user.getWriterName();
    }
}