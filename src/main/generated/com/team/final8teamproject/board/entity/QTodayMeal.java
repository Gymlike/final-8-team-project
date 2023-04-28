package com.team.final8teamproject.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QTodayMeal is a Querydsl query type for TodayMeal
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTodayMeal extends EntityPathBase<TodayMeal> {

    private static final long serialVersionUID = -1484262375L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTodayMeal todayMeal = new QTodayMeal("todayMeal");

    public final com.team.final8teamproject.share.QTimestamped _super = new com.team.final8teamproject.share.QTimestamped(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final StringPath createdDateString = _super.createdDateString;

    public final StringPath filepath = createString("filepath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath title = createString("title");

    public final com.team.final8teamproject.base.entity.QBaseEntity user;

    public QTodayMeal(String variable) {
        this(TodayMeal.class, forVariable(variable), INITS);
    }

    public QTodayMeal(Path<? extends TodayMeal> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTodayMeal(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTodayMeal(PathMetadata metadata, PathInits inits) {
        this(TodayMeal.class, metadata, inits);
    }

    public QTodayMeal(Class<? extends TodayMeal> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.team.final8teamproject.base.entity.QBaseEntity(forProperty("user")) : null;
    }

}

