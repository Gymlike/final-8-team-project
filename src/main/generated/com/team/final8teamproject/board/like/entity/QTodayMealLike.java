package com.team.final8teamproject.board.like.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTodayMealLike is a Querydsl query type for TodayMealLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodayMealLike extends EntityPathBase<TodayMealLike> {

    private static final long serialVersionUID = -1905044089L;

    public static final QTodayMealLike todayMealLike = new QTodayMealLike("todayMealLike");

    public final NumberPath<Long> boardId = createNumber("boardId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath username = createString("username");

    public QTodayMealLike(String variable) {
        super(TodayMealLike.class, forVariable(variable));
    }

    public QTodayMealLike(Path<? extends TodayMealLike> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTodayMealLike(PathMetadata metadata) {
        super(TodayMealLike.class, metadata);
    }

}

