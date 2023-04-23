package com.team.final8teamproject.websocket.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChatMessage is a Querydsl query type for ChatMessage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatMessage extends EntityPathBase<ChatMessage> {

    private static final long serialVersionUID = 1492578115L;

    public static final QChatMessage chatMessage = new QChatMessage("chatMessage");

    public final QChatTimestamped _super = new QChatTimestamped(this);

    //inherited
    public final StringPath createdDate = _super.createdDate;

    //inherited
    public final NumberPath<Long> createdDateTime = _super.createdDateTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath live = createBoolean("live");

    public final StringPath message = createString("message");

    public final StringPath receiver = createString("receiver");

    public final NumberPath<Long> roomId = createNumber("roomId", Long.class);

    public QChatMessage(String variable) {
        super(ChatMessage.class, forVariable(variable));
    }

    public QChatMessage(Path<? extends ChatMessage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatMessage(PathMetadata metadata) {
        super(ChatMessage.class, metadata);
    }

}

