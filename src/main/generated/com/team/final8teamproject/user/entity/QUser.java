package com.team.final8teamproject.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -2074405045L;

    public static final QUser user = new QUser("user");

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

    //inherited
    public final StringPath password = _super.password;

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath profileImage = createString("profileImage");

    //inherited
    public final EnumPath<UserRoleEnum> role = _super.role;

    //inherited
    public final StringPath username = _super.username;

    //inherited
    public final StringPath writerName = _super.writerName;

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

