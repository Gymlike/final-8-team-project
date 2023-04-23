package com.team.final8teamproject.websocket.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QChatTimestamped is a Querydsl query type for ChatTimestamped
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QChatTimestamped extends EntityPathBase<ChatTimestamped> {

    private static final long serialVersionUID = -1553122959L;

    public static final QChatTimestamped chatTimestamped = new QChatTimestamped("chatTimestamped");

    public final StringPath createdDate = createString("createdDate");

    public final NumberPath<Long> createdDateTime = createNumber("createdDateTime", Long.class);

    public QChatTimestamped(String variable) {
        super(ChatTimestamped.class, forVariable(variable));
    }

    public QChatTimestamped(Path<? extends ChatTimestamped> path) {
        super(path.getType(), path.getMetadata());
    }

    public QChatTimestamped(PathMetadata metadata) {
        super(ChatTimestamped.class, metadata);
    }

}

