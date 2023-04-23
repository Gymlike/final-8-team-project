package com.team.final8teamproject.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QManager is a Querydsl query type for Manager
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QManager extends EntityPathBase<Manager> {

    private static final long serialVersionUID = -1335656261L;

    public static final QManager manager = new QManager("manager");

    public final com.team.final8teamproject.base.entity.QBaseEntity _super = new com.team.final8teamproject.base.entity.QBaseEntity(this);

    public final BooleanPath applyManager = createBoolean("applyManager");

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

    //inherited
    public final StringPath password = _super.password;

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath profileImage = createString("profileImage");

    //inherited
    public final EnumPath<com.team.final8teamproject.user.entity.UserRoleEnum> role = _super.role;

    //inherited
    public final StringPath username = _super.username;

    public final EnumPath<com.team.final8teamproject.user.entity.UserRoleEnum> userRoleEnum = createEnum("userRoleEnum", com.team.final8teamproject.user.entity.UserRoleEnum.class);

    //inherited
    public final StringPath writerName = _super.writerName;

    public QManager(String variable) {
        super(Manager.class, forVariable(variable));
    }

    public QManager(Path<? extends Manager> path) {
        super(path.getType(), path.getMetadata());
    }

    public QManager(PathMetadata metadata) {
        super(Manager.class, metadata);
    }

}

