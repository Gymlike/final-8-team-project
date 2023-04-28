package com.team.final8teamproject.board.comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTodayMealComment is a Querydsl query type for TodayMealComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodayMealComment extends EntityPathBase<TodayMealComment> {

    private static final long serialVersionUID = -1854832265L;

    public static final QTodayMealComment todayMealComment = new QTodayMealComment("todayMealComment");

    public final com.team.final8teamproject.share.QTimestamped _super = new com.team.final8teamproject.share.QTimestamped(this);

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final StringPath comment = createString("comment");

    public final ListPath<com.team.final8teamproject.board.comment.commentReply.entity.TodayMealCommentReply, com.team.final8teamproject.board.comment.commentReply.entity.QTodayMealCommentReply> commentReplyList = this.<com.team.final8teamproject.board.comment.commentReply.entity.TodayMealCommentReply, com.team.final8teamproject.board.comment.commentReply.entity.QTodayMealCommentReply>createList("commentReplyList", com.team.final8teamproject.board.comment.commentReply.entity.TodayMealCommentReply.class, com.team.final8teamproject.board.comment.commentReply.entity.QTodayMealCommentReply.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final StringPath createdDateString = _super.createdDateString;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath username = createString("username");

    public final StringPath userNickname = createString("userNickname");

    public QTodayMealComment(String variable) {
        super(TodayMealComment.class, forVariable(variable));
    }

    public QTodayMealComment(Path<? extends TodayMealComment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTodayMealComment(PathMetadata metadata) {
        super(TodayMealComment.class, metadata);
    }

}

