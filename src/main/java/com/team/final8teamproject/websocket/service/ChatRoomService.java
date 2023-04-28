package com.team.final8teamproject.websocket.service;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.base.repository.BaseRepository;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import com.team.final8teamproject.websocket.dto.ChatRoomDto;
import com.team.final8teamproject.websocket.dto.GetMyAllRoomResponseDto;
import com.team.final8teamproject.websocket.entity.ChatRoom;
import com.team.final8teamproject.websocket.repository.ChatMessageRepository;
import com.team.final8teamproject.websocket.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository roomRepository;
    private final BaseRepository baseRepository;

    public ChatRoomDto createRoom(BaseEntity owner, BaseEntity user){
        ChatRoom chatRoom = ChatRoom.builder()
                .owner(owner)
                .user(user)
                .build();
        roomRepository.save(chatRoom);
        return new ChatRoomDto(chatRoom);
    }

    /**
     * 사업자와 유저에 따라서 보내주는 리스트를 나눔
     * 나중에는 메소드를 분리하고 팩토리 형태로 바꿀 예정
     */
    public List<GetMyAllRoomResponseDto> getMyAllRoom(String username){
        BaseEntity base=baseRepository.findByUsername(username).orElseThrow(
                ()-> new CustomException(ExceptionStatus.NOT_FOUNT_USER)
        );
        String userNickName = base.getNickName();
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
    //선택한 채팅방 가져오기
    public ResponseEntity<ChatRoomDto> getChatRoom(BaseEntity me, String nickName) {
        BaseEntity user = baseRepository.findByUsername(nickName).orElseThrow(
                ()-> new CustomException(ExceptionStatus.NOT_FOUNT_USER)
        );
        BaseEntity username, ownerName;
        String roomName;
        if(user.getRole().equals(UserRoleEnum.MEMBER)){
            username = user;
            ownerName = me;
            roomName = nickName+me.getNickName();
        }else{
            username = me;
            ownerName = user;
            roomName = me.getNickName()+nickName;
        }

        //채팅방이 있으면 채팅방 가져오기
        if (roomRepository.existsByRoomTitle(roomName)) {
            return ResponseEntity.ok().body(new ChatRoomDto(roomRepository.findByRoomTitle(roomName)));
        }
        //채팅방이 없으면 채팅방 생성
        else {
            return ResponseEntity.ok().body(createRoom(ownerName, username));
        }
    }


//    /**
//     * 채팅방을 삭제
//     * @param OwnerName 방주인인지 확인
//     * @param roomName  삭제할 룸이름확인
//     * @return
//     */
//    public ResponseEntity<String> deleteRoom(String OwnerName, String roomName){
//        try {
//            ChatRoom chatRoom= roomRepository.findByOwnerNickNameAndRoomTitleWithMessages(OwnerName, roomName).orElseThrow(
//                    () -> new CustomException(ExceptionStatus.ROOM_NOT_EXIST)
//            );
//            chatRoom.setLive(false);
//            Set<ChatRoomMessage> messages = chatRoom.getMessages();
//            for (ChatRoomMessage message : messages) {
//                message.getChatMessage().setLive(false);
//                chatMessageRepository.save(message.getChatMessage());
//            }
//            return new ResponseEntity<>("deletedSuccess", HttpStatus.OK);
//        }catch (Exception e){
//            throw new CustomException(ExceptionStatus.BOARD_NOT_EXIST);
//        }
//    }
}
