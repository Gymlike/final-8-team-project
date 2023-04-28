package com.team.final8teamproject.gymboardreview.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGymDeleteReview is a Querydsl query type for GymDeleteReview
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGymDeleteReview extends EntityPathBase<GymDeleteReview> {

    private static final long serialVersionUID = -156049306L;

    public static final QGymDeleteReview gymDeleteReview = new QGymDeleteReview("gymDeleteReview");

    public final StringPath comment = createString("comment");

    public final NumberPath<Long> gymId = createNumber("gymId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> reviewId = createNumber("reviewId", Long.class);

    public final StringPath username = createString("username");

    public QGymDeleteReview(String variable) {
        super(GymDeleteReview.class, forVariable(variable));
    }

    public QGymDeleteReview(Path<? extends GymDeleteReview> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGymDeleteReview(PathMetadata metadata) {
        super(GymDeleteReview.class, metadata);
    }

}

