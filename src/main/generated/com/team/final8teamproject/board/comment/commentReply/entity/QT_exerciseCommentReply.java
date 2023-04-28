package com.team.final8teamproject.board.comment.commentReply.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QT_exerciseCommentReply is a Querydsl query type for T_exerciseCommentReply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QT_exerciseCommentReply extends EntityPathBase<T_exerciseCommentReply> {

    private static final long serialVersionUID = -1879783131L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QT_exerciseCommentReply t_exerciseCommentReply = new QT_exerciseCommentReply("t_exerciseCommentReply");

    public final com.team.final8teamproject.share.QTimestamped _super = new com.team.final8teamproject.share.QTimestamped(this);

    public final StringPath commentContent = createString("commentContent");

    public final com.team.final8teamproject.board.comment.entity.QT_exerciseComment comments;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final StringPath createdDateString = _super.createdDateString;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath username = createString("username");

    public final StringPath userNickname = createString("userNickname");

    public QT_exerciseCommentReply(String variable) {
        this(T_exerciseCommentReply.class, forVariable(variable), INITS);
    }

    public QT_exerciseCommentReply(Path<? extends T_exerciseCommentReply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QT_exerciseCommentReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QT_exerciseCommentReply(PathMetadata metadata, PathInits inits) {
        this(T_exerciseCommentReply.class, metadata, inits);
    }

    public QT_exerciseCommentReply(Class<? extends T_exerciseCommentReply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comments = inits.isInitialized("comments") ? new com.team.final8teamproject.board.comment.entity.QT_exerciseComment(forProperty("comments")) : null;
    }

}

