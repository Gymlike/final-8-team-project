package com.team.final8teamproject.board.comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFreeBoardComment is a Querydsl query type for FreeBoardComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFreeBoardComment extends EntityPathBase<FreeBoardComment> {

    private static final long serialVersionUID = -863704191L;

    public static final QFreeBoardComment freeBoardComment = new QFreeBoardComment("freeBoardComment");

    public final com.team.final8teamproject.share.QTimestamped _super = new com.team.final8teamproject.share.QTimestamped(this);

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final StringPath comment = createString("comment");

    public final ListPath<com.team.final8teamproject.board.comment.commentReply.entity.FreeBoardCommentReply, com.team.final8teamproject.board.comment.commentReply.entity.QFreeBoardCommentReply> commentReplyList = this.<com.team.final8teamproject.board.comment.commentReply.entity.FreeBoardCommentReply, com.team.final8teamproject.board.comment.commentReply.entity.QFreeBoardCommentReply>createList("commentReplyList", com.team.final8teamproject.board.comment.commentReply.entity.FreeBoardCommentReply.class, com.team.final8teamproject.board.comment.commentReply.entity.QFreeBoardCommentReply.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final StringPath createdDateString = _super.createdDateString;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath username = createString("username");

    public final StringPath userNickname = createString("userNickname");

    public QFreeBoardComment(String variable) {
        super(FreeBoardComment.class, forVariable(variable));
    }

    public QFreeBoardComment(Path<? extends FreeBoardComment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFreeBoardComment(PathMetadata metadata) {
        super(FreeBoardComment.class, metadata);
    }

}

