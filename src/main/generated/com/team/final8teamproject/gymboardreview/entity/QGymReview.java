package com.team.final8teamproject.gymboardreview.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGymReview is a Querydsl query type for GymReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGymReview extends EntityPathBase<GymReview> {

    private static final long serialVersionUID = -211076837L;

    public static final QGymReview gymReview = new QGymReview("gymReview");

    public final com.team.final8teamproject.share.QTimestamped _super = new com.team.final8teamproject.share.QTimestamped(this);

    public final StringPath comment = createString("comment");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final StringPath createdDateString = _super.createdDateString;

    public final NumberPath<Long> gymId = createNumber("gymId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final NumberPath<Long> rating = createNumber("rating", Long.class);

    public final StringPath username = createString("username");

    public QGymReview(String variable) {
        super(GymReview.class, forVariable(variable));
    }

    public QGymReview(Path<? extends GymReview> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGymReview(PathMetadata metadata) {
        super(GymReview.class, metadata);
    }

}

