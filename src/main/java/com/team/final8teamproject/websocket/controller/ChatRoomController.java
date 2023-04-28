package com.team.final8teamproject.websocket.controller;

import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.websocket.dto.*;
import com.team.final8teamproject.websocket.entity.ChatMessage;
import com.team.final8teamproject.websocket.service.ChatMessageService;
import com.team.final8teamproject.websocket.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/chat")
@Slf4j
public class ChatRoomController {
    private final ChatRoomService roomService;

    private final ChatMessageService messageService;
    @GetMapping("/rooms")
    public List<GetMyAllRoomResponseDto> getMyRoom(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ){
        return roomService.getMyAllRoom(userDetails.getUsername());
    }

    @GetMapping("/room/{nickName}")
    public ResponseEntity<ChatRoomDto> getChatRoom(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable String nickName
            ){
        return roomService.getChatRoom(userDetails.getBase(), nickName);
    }

    @GetMapping("/chat/{roomId}/message")
    public ResponseEntity<List<ChatMessage>> getRoomMessage(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long roomId
            ){
        return ResponseEntity.ok().body(messageService.getRoomMessages(roomId, userDetails.getUsername()));
    }

//    @PostMapping("/room/delete")
//    public ResponseEntity<String> deleteRoom(
//            @RequestBody DeleteRoomRequestDto roomRequestDto
//    ){
//        return roomService.deleteRoom(roomRequestDto.getOwnerNickName(), roomRequestDto.getRoomTitle());
//    }
}
