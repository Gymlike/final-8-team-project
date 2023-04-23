package com.team.final8teamproject.board.comment.commentReply.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTodayMealCommentReply is a Querydsl query type for TodayMealCommentReply
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodayMealCommentReply extends EntityPathBase<TodayMealCommentReply> {

    private static final long serialVersionUID = 2072909112L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTodayMealCommentReply todayMealCommentReply = new QTodayMealCommentReply("todayMealCommentReply");

    public final com.team.final8teamproject.share.QTimestamped _super = new com.team.final8teamproject.share.QTimestamped(this);

    public final StringPath commentContent = createString("commentContent");

    public final com.team.final8teamproject.board.comment.entity.QTodayMealComment comments;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final StringPath createdDateString = _super.createdDateString;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath username = createString("username");

    public final StringPath userNickname = createString("userNickname");

    public QTodayMealCommentReply(String variable) {
        this(TodayMealCommentReply.class, forVariable(variable), INITS);
    }

    public QTodayMealCommentReply(Path<? extends TodayMealCommentReply> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTodayMealCommentReply(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTodayMealCommentReply(PathMetadata metadata, PathInits inits) {
        this(TodayMealCommentReply.class, metadata, inits);
    }

    public QTodayMealCommentReply(Class<? extends TodayMealCommentReply> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.comments = inits.isInitialized("comments") ? new com.team.final8teamproject.board.comment.entity.QTodayMealComment(forProperty("comments")) : null;
    }

}

