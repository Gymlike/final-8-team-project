package com.team.final8teamproject.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QKaKao is a Querydsl query type for KaKao
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QKaKao extends EntityPathBase<KaKao> {

    private static final long serialVersionUID = 108156195L;

    public static final QKaKao kaKao = new QKaKao("kaKao");

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

    public final NumberPath<Long> kakaoId = createNumber("kakaoId", Long.class);

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
    public final EnumPath<UserRoleEnum> role = _super.role;

    //inherited
    public final StringPath username = _super.username;

    //inherited
    public final StringPath writerName = _super.writerName;

    public QKaKao(String variable) {
        super(KaKao.class, forVariable(variable));
    }

    public QKaKao(Path<? extends KaKao> path) {
        super(path.getType(), path.getMetadata());
    }

    public QKaKao(PathMetadata metadata) {
        super(KaKao.class, metadata);
    }

}

