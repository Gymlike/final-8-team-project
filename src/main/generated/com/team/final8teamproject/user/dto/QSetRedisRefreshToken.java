package com.team.final8teamproject.user.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSetRedisRefreshToken is a Querydsl query type for SetRedisRefreshToken
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSetRedisRefreshToken extends EntityPathBase<SetRedisRefreshToken> {

    private static final long serialVersionUID = 1699251057L;

    public static final QSetRedisRefreshToken setRedisRefreshToken = new QSetRedisRefreshToken("setRedisRefreshToken");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath refreshToken = createString("refreshToken");

    public final EnumPath<com.team.final8teamproject.user.entity.UserRoleEnum> role = createEnum("role", com.team.final8teamproject.user.entity.UserRoleEnum.class);

    public QSetRedisRefreshToken(String variable) {
        super(SetRedisRefreshToken.class, forVariable(variable));
    }

    public QSetRedisRefreshToken(Path<? extends SetRedisRefreshToken> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSetRedisRefreshToken(PathMetadata metadata) {
        super(SetRedisRefreshToken.class, metadata);
    }

}

