package com.team.final8teamproject.manager.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGeneralManager is a Querydsl query type for GeneralManager
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGeneralManager extends EntityPathBase<GeneralManager> {

    private static final long serialVersionUID = 946154711L;

    public static final QGeneralManager generalManager = new QGeneralManager("generalManager");

    public final com.team.final8teamproject.base.entity.QBaseEntity _super = new com.team.final8teamproject.base.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final StringPath createdDateString = _super.createdDateString;

    //inherited
    public final StringPath email = _super.email;

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

    //inherited
    public final StringPath phoneNumber = _super.phoneNumber;

    public final StringPath profileImage = createString("profileImage");

    //inherited
    public final EnumPath<com.team.final8teamproject.user.entity.UserRoleEnum> role = _super.role;

    //inherited
    public final StringPath username = _super.username;

    //inherited
    public final StringPath writerName = _super.writerName;

    public QGeneralManager(String variable) {
        super(GeneralManager.class, forVariable(variable));
    }

    public QGeneralManager(Path<? extends GeneralManager> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGeneralManager(PathMetadata metadata) {
        super(GeneralManager.class, metadata);
    }

}

