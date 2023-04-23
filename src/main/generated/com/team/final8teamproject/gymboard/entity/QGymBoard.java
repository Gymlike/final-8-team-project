package com.team.final8teamproject.gymboard.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGymBoard is a Querydsl query type for GymBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGymBoard extends EntityPathBase<GymBoard> {

    private static final long serialVersionUID = 1131272107L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGymBoard gymBoard = new QGymBoard("gymBoard");

    public final com.team.final8teamproject.share.QTimestamped _super = new com.team.final8teamproject.share.QTimestamped(this);

    public final QAmenities amenities;

    public final StringPath amenitiesDetail = createString("amenitiesDetail");

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final StringPath createdDateString = _super.createdDateString;

    public final StringPath discount = createString("discount");

    public final StringPath gymName = createString("gymName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imageUrl = createString("imageUrl");

    public final BooleanPath inLive = createBoolean("inLive");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath openTime = createString("openTime");

    public final StringPath ownerNumber = createString("ownerNumber");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath price = createString("price");

    public final NumberPath<Long> rating = createNumber("rating", Long.class);

    public final StringPath region = createString("region");

    public final StringPath title = createString("title");

    public final StringPath username = createString("username");

    public QGymBoard(String variable) {
        this(GymBoard.class, forVariable(variable), INITS);
    }

    public QGymBoard(Path<? extends GymBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGymBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGymBoard(PathMetadata metadata, PathInits inits) {
        this(GymBoard.class, metadata, inits);
    }

    public QGymBoard(Class<? extends GymBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.amenities = inits.isInitialized("amenities") ? new QAmenities(forProperty("amenities")) : null;
    }

}

