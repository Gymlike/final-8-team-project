package com.team.final8teamproject.websocket.service;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.base.repository.BaseRepository;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import com.team.final8teamproject.websocket.dto.GetMyAllRoomResponseDto;
import com.team.final8teamproject.websocket.entity.ChatRoom;
import com.team.final8teamproject.websocket.entity.ChatRoomMessage;
import com.team.final8teamproject.websocket.entity.Message;
import com.team.final8teamproject.websocket.repository.ChatMessageRepository;
import com.team.final8teamproject.websocket.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository roomRepository;

    private final ChatMessageRepository chatMessageRepository;
    private final BaseRepository baseRepository;

    /**
     * 1:1 채팅방 생성
     * @param ownerNickName 생성하는 사업자닉네임
     * @param userNickName  들어올 수 있는 유저 닉네임
     * 여기서 문제가 있음 닉네임을 변경했을때.
     * 다른 사람이 들어갈 수도 있다는점 어떻게 할것인가
     * 1. 닉네임 중복검사를 하며 닉네임 변경시 변경전 후 채팅창, 채팅메시지를 다 삭제한다.
     *                      (단점 변경을 다같이 할때 서버에 무리가 와서 내려갈수있다.)
     * @return 성공했다면 성공을 보낸다.
     */
    public ResponseEntity<String> createRoom(String ownerNickName, String userNickName){
        String roomName = ownerNickName+userNickName;
        ChatRoom chatRoom = ChatRoom.builder()
                .roomTitle(roomName)
                .ownerNickName(ownerNickName)
                .userNickName(userNickName)
                .build();
        roomRepository.save(chatRoom);

        return new ResponseEntity<>("생성성공", HttpStatus.OK);
    }

    /**
     * 채팅방을 삭제
     * @param OwnerName 방주인인지 확인
     * @param roomName  삭제할 룸이름확인
     * @return
     */
    public ResponseEntity<String> deleteRoom(String OwnerName, String roomName){
        try {
            ChatRoom chatRoom= roomRepository.findByOwnerNickNameAndRoomTitleWithMessages(OwnerName, roomName).orElseThrow(
                    () -> new CustomException(ExceptionStatus.ROOM_NOT_EXIST)
            );
            chatRoom.setLive(false);
            Set<ChatRoomMessage> messages = chatRoom.getMessages();
            for (ChatRoomMessage message : messages) {
                message.getMessage().setLive(false);
                chatMessageRepository.save(message.getMessage());
            }
            return new ResponseEntity<>("삭제성공", HttpStatus.OK);
        }catch (Exception e){
            throw new CustomException(ExceptionStatus.BOARD_NOT_EXIST);
        }
    }

    /**
     * 사업자와 유저에 따라서 보내주는 리스트를 나눔
     * 나중에는 메소드를 분리하고 팩토리 형태로 바꿀 예정
     * @param userNickName
     * @param username
     * @return
     */
    public List<GetMyAllRoomResponseDto> getMyAllRoom(String userNickName, String username){
        BaseEntity base=baseRepository.findByUsername(username).orElseThrow(
                ()-> new CustomException(ExceptionStatus.NOT_FOUNT_USER)
        );

        if(base.getRole().equals(UserRoleEnum.OWNER)){
            List<ChatRoom> chatRoomsList = roomRepository.findByOwnerNickName(userNickName).stream().toList();
            List<GetMyAllRoomResponseDto> roomResponseDtos = new ArrayList<>();
            for(ChatRoom chatRoom : chatRoomsList){
                roomResponseDtos.add(new GetMyAllRoomResponseDto(chatRoom.getRoomTitle()));
            }
            return roomResponseDtos;
        }else if(base.getRole().equals(UserRoleEnum.MEMBER)){
            List<ChatRoom> chatRoomsList = roomRepository.findByUserNickName(userNickName).stream().toList();
            List<GetMyAllRoomResponseDto> roomResponseDtos = new ArrayList<>();
            for(ChatRoom chatRoom : chatRoomsList){
                roomResponseDtos.add(new GetMyAllRoomResponseDto(chatRoom.getRoomTitle()));
            }
            return roomResponseDtos;
        }
        throw new CustomException(ExceptionStatus.ACCESS_DENINED);
    }
}
