package com.team.final8teamproject.websocket.dto;

public class GetMyAllRoomResponseDto {
    private String roomTitle;

    public GetMyAllRoomResponseDto(String roomTitle){
        this.roomTitle = roomTitle;
    }
    public String getRoomTitle(){
        return this.roomTitle;
    }
}
