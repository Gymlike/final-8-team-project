package com.team.final8teamproject.gymboard.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGymPrice is a Querydsl query type for GymPrice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGymPrice extends EntityPathBase<GymPrice> {

    private static final long serialVersionUID = 1144297998L;

    public static final QGymPrice gymPrice = new QGymPrice("gymPrice");

    public final com.team.final8teamproject.share.QTimestamped _super = new com.team.final8teamproject.share.QTimestamped(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final StringPath createdDateString = _super.createdDateString;

    public final StringPath gymId = createString("gymId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath price = createString("price");

    public final StringPath username = createString("username");

    public QGymPrice(String variable) {
        super(GymPrice.class, forVariable(variable));
    }

    public QGymPrice(Path<? extends GymPrice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGymPrice(PathMetadata metadata) {
        super(GymPrice.class, metadata);
    }

}

