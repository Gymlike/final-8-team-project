package com.team.final8teamproject.board.comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QT_exerciseComment is a Querydsl query type for T_exerciseComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QT_exerciseComment extends EntityPathBase<T_exerciseComment> {

    private static final long serialVersionUID = -520232768L;

    public static final QT_exerciseComment t_exerciseComment = new QT_exerciseComment("t_exerciseComment");

    public final com.team.final8teamproject.share.QTimestamped _super = new com.team.final8teamproject.share.QTimestamped(this);

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final StringPath comment = createString("comment");

    public final ListPath<com.team.final8teamproject.board.comment.commentReply.entity.T_exerciseCommentReply, com.team.final8teamproject.board.comment.commentReply.entity.QT_exerciseCommentReply> commentReplyList = this.<com.team.final8teamproject.board.comment.commentReply.entity.T_exerciseCommentReply, com.team.final8teamproject.board.comment.commentReply.entity.QT_exerciseCommentReply>createList("commentReplyList", com.team.final8teamproject.board.comment.commentReply.entity.T_exerciseCommentReply.class, com.team.final8teamproject.board.comment.commentReply.entity.QT_exerciseCommentReply.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final StringPath createdDateString = _super.createdDateString;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath username = createString("username");

    public final StringPath userNickname = createString("userNickname");

    public QT_exerciseComment(String variable) {
        super(T_exerciseComment.class, forVariable(variable));
    }

    public QT_exerciseComment(Path<? extends T_exerciseComment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QT_exerciseComment(PathMetadata metadata) {
        super(T_exerciseComment.class, metadata);
    }

}

