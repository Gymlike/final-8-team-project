package com.team.final8teamproject.websocket.controller;

import com.team.final8teamproject.websocket.dto.CreateRoomRequestDto;
import com.team.final8teamproject.websocket.dto.DeleteRoomRequestDto;
import com.team.final8teamproject.websocket.dto.GetMyAllRoomResponseDto;
import com.team.final8teamproject.websocket.dto.GetMyRoomRequestDto;
import com.team.final8teamproject.websocket.entity.ChatRoom;
import com.team.final8teamproject.websocket.repository.ChatRoomRepository;
import com.team.final8teamproject.websocket.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
@Slf4j
public class ChatRoomController {
    private final ChatRoomService roomService;
    @PostMapping("/room/create")
    public ResponseEntity<String> creatRoom(@RequestBody CreateRoomRequestDto roomRequestDto){
        return roomService.createRoom(roomRequestDto.getOwnerNickName(), roomRequestDto.getUserNickName());
    }

    @PostMapping("/room/delete")
    public ResponseEntity<String> deleteRoom(@RequestBody DeleteRoomRequestDto roomRequestDto){
        return roomService.deleteRoom(roomRequestDto.getOwnerNickName(), roomRequestDto.getRoomTitle());
    }

    //되는거 확인하면 페이징 처리해서
    //몇개만 보여주기
    @GetMapping("/rooms")
    public List<GetMyAllRoomResponseDto> getMyRoom(@RequestBody GetMyRoomRequestDto roomRequestDto,
                                                   @AuthenticationPrincipal UserDetails userDetails){
        return roomService.getMyAllRoom(roomRequestDto.getUserNickName(), userDetails.getUsername());
    }


}
