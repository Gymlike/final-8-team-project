package com.team.final8teamproject.owner.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QOwner is a Querydsl query type for Owner
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOwner extends EntityPathBase<Owner> {

    private static final long serialVersionUID = 823630843L;

    public static final QOwner owner = new QOwner("owner");

    public final com.team.final8teamproject.base.entity.QBaseEntity _super = new com.team.final8teamproject.base.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final StringPath createdDateString = _super.createdDateString;

    //inherited
    public final StringPath email = _super.email;

    public final NumberPath<Long> experience = createNumber("experience", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final BooleanPath inLive = _super.inLive;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    //inherited
    public final StringPath nickName = _super.nickName;

    public final StringPath ownerName = createString("ownerName");

    public final StringPath ownerNumber = createString("ownerNumber");

    //inherited
    public final StringPath password = _super.password;

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath profileImage = createString("profileImage");

    //inherited
    public final EnumPath<com.team.final8teamproject.user.entity.UserRoleEnum> role = _super.role;

    public final StringPath start_dt = createString("start_dt");

    public final StringPath storeName = createString("storeName");

    //inherited
    public final StringPath username = _super.username;

    //inherited
    public final StringPath writerName = _super.writerName;

    public QOwner(String variable) {
        super(Owner.class, forVariable(variable));
    }

    public QOwner(Path<? extends Owner> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOwner(PathMetadata metadata) {
        super(Owner.class, metadata);
    }

}

