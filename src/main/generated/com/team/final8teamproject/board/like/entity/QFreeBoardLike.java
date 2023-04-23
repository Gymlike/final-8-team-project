package com.team.final8teamproject.board.like.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFreeBoardLike is a Querydsl query type for FreeBoardLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFreeBoardLike extends EntityPathBase<FreeBoardLike> {

    private static final long serialVersionUID = 982857661L;

    public static final QFreeBoardLike freeBoardLike = new QFreeBoardLike("freeBoardLike");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath username = createString("username");

    public QFreeBoardLike(String variable) {
        super(FreeBoardLike.class, forVariable(variable));
    }

    public QFreeBoardLike(Path<? extends FreeBoardLike> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFreeBoardLike(PathMetadata metadata) {
        super(FreeBoardLike.class, metadata);
    }

}

