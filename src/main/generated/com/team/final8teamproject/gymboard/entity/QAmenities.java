package com.team.final8teamproject.gymboard.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAmenities is a Querydsl query type for Amenities
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAmenities extends EntityPathBase<Amenities> {

    private static final long serialVersionUID = 1975238487L;

    public static final QAmenities amenities = new QAmenities("amenities");

    public final BooleanPath event = createBoolean("event");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath locker = createBoolean("locker");

    public final BooleanPath parkingLot = createBoolean("parkingLot");

    public final BooleanPath showerRoom = createBoolean("showerRoom");

    public final BooleanPath towel = createBoolean("towel");

    public final BooleanPath wifi = createBoolean("wifi");

    public QAmenities(String variable) {
        super(Amenities.class, forVariable(variable));
    }

    public QAmenities(Path<? extends Amenities> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAmenities(PathMetadata metadata) {
        super(Amenities.class, metadata);
    }

}

