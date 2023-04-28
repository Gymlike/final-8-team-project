package com.team.final8teamproject.board.like.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QT_exerciseLike is a Querydsl query type for T_exerciseLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QT_exerciseLike extends EntityPathBase<T_exerciseLike> {

    private static final long serialVersionUID = -52404274L;

    public static final QT_exerciseLike t_exerciseLike = new QT_exerciseLike("t_exerciseLike");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath username = createString("username");

    public QT_exerciseLike(String variable) {
        super(T_exerciseLike.class, forVariable(variable));
    }

    public QT_exerciseLike(Path<? extends T_exerciseLike> path) {
        super(path.getType(), path.getMetadata());
    }

    public QT_exerciseLike(PathMetadata metadata) {
        super(T_exerciseLike.class, metadata);
    }

}

