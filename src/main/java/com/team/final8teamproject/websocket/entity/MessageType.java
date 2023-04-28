package com.team.final8teamproject.websocket.entity;

public enum MessageType {
    SEND(UserChatRoomType.SEND),
    NEW(UserChatRoomType.NEW),
    READ(UserChatRoomType.READ),
    CLOSE(UserChatRoomType.CLOSE);
    private final String chatType;
    MessageType(String chatType){
        this.chatType = chatType;
    }
    public String getChatType(){
        return this.chatType;
    }
    public static class UserChatRoomType{
        public static final String SEND = "SEND";
        public static final String NEW = "NEW";
        public static final String READ = "READ";
        public static final String CLOSE = "CLOSE";
    }
}
