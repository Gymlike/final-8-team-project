package com.team.final8teamproject.websocket.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QChatRoom is a Querydsl query type for ChatRoom
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QChatRoom extends EntityPathBase<ChatRoom> {

    private static final long serialVersionUID = -254395617L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QChatRoom chatRoom = new QChatRoom("chatRoom");

    public final QChatTimestamped _super = new QChatTimestamped(this);

    //inherited
    public final StringPath createdDate = _super.createdDate;

    //inherited
    public final NumberPath<Long> createdDateTime = _super.createdDateTime;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath live = createBoolean("live");

    public final com.team.final8teamproject.base.entity.QBaseEntity owner;

    public final StringPath roomTitle = createString("roomTitle");

    public final com.team.final8teamproject.base.entity.QBaseEntity user;

    public QChatRoom(String variable) {
        this(ChatRoom.class, forVariable(variable), INITS);
    }

    public QChatRoom(Path<? extends ChatRoom> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QChatRoom(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QChatRoom(PathMetadata metadata, PathInits inits) {
        this(ChatRoom.class, metadata, inits);
    }

    public QChatRoom(Class<? extends ChatRoom> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.owner = inits.isInitialized("owner") ? new com.team.final8teamproject.base.entity.QBaseEntity(forProperty("owner")) : null;
        this.user = inits.isInitialized("user") ? new com.team.final8teamproject.base.entity.QBaseEntity(forProperty("user")) : null;
    }

}

