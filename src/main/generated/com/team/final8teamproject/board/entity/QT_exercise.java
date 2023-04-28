package com.team.final8teamproject.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QT_exercise is a Querydsl query type for T_exercise
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QT_exercise extends EntityPathBase<T_exercise> {

    private static final long serialVersionUID = 229101166L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QT_exercise t_exercise = new QT_exercise("t_exercise");

    public final com.team.final8teamproject.share.QTimestamped _super = new com.team.final8teamproject.share.QTimestamped(this);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final StringPath createdDateString = _super.createdDateString;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath title = createString("title");

    public final com.team.final8teamproject.base.entity.QBaseEntity user;

    public QT_exercise(String variable) {
        this(T_exercise.class, forVariable(variable), INITS);
    }

    public QT_exercise(Path<? extends T_exercise> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QT_exercise(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QT_exercise(PathMetadata metadata, PathInits inits) {
        this(T_exercise.class, metadata, inits);
    }

    public QT_exercise(Class<? extends T_exercise> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.team.final8teamproject.base.entity.QBaseEntity(forProperty("user")) : null;
    }

}

