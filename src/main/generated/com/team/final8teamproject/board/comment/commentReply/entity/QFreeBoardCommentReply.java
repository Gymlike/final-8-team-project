package com.team.final8teamproject.board.comment.commentReply.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFreeBoardCommentReply is a Querydsl query type for FreeBoardCommentReply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFreeBoardCommentReply extends EntityPathBase<FreeBoardCommentReply> {

    private static final long serialVersionUID = 656236910L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFreeBoardCommentReply freeBoardCommentReply = new QFreeBoardCommentReply("freeBoardCommentReply");

    public final com.team.final8teamproject.share.QTimestamped _super = new com.team.final8teamproject.share.QTimestamped(this);

    public final StringPath commentContent = createString("commentContent");

    public final com.team.final8teamproject.board.comment.entity.QFreeBoardComment comments;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final StringPath createdDateString = _super.createdDateString;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath username = createString("username");

    public final StringPath userNickname = createString("userNickname");

    public QFreeBoardCommentReply(String variable) {
        this(FreeBoardCommentReply.class, forVariable(variable), INITS);
    }

    public QFreeBoardCommentReply(Path<? extends FreeBoardCommentReply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFreeBoardCommentReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFreeBoardCommentReply(PathMetadata metadata, PathInits inits) {
        this(FreeBoardCommentReply.class, metadata, inits);
    }

    public QFreeBoardCommentReply(Class<? extends FreeBoardCommentReply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comments = inits.isInitialized("comments") ? new com.team.final8teamproject.board.comment.entity.QFreeBoardComment(forProperty("comments")) : null;
    }

}

