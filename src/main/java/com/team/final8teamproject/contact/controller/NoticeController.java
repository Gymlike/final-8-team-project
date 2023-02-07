package com.team.final8teamproject.contact.controller;

import com.team.final8teamproject.contact.dto.NoticeRequest;
import com.team.final8teamproject.contact.service.NoticeService;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RequestMapping("/api/managers/notice")
@RestController
public class NoticeController {

    private final NoticeService noticeService;

    //관리자 공지사항 등록
    @PostMapping("")
    public ResponseEntity saveNotice(@RequestBody NoticeRequest noticeRequest,
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
        noticeService.saveNotice(noticeRequest, userDetails.getUser().getId());
        return ResponseEntity.ok("등록완료");
//관리자 공지사항 수정
//관리자 공지사항 삭제
    }
}
