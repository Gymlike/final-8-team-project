package com.team.final8teamproject.contact.Comment.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QContactComment is a Querydsl query type for ContactComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QContactComment extends EntityPathBase<ContactComment> {

    private static final long serialVersionUID = 1523063061L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QContactComment contactComment = new QContactComment("contactComment");

    public final com.team.final8teamproject.share.QTimestamped _super = new com.team.final8teamproject.share.QTimestamped(this);

    public final ListPath<ContactComment, QContactComment> children = this.<ContactComment, QContactComment>createList("children", ContactComment.class, QContactComment.class, PathInits.DIRECT2);

    public final StringPath comments = createString("comments");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    //inherited
    public final StringPath createdDateString = _super.createdDateString;

    public final NumberPath<Integer> depth = createNumber("depth", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> inquiryId = createNumber("inquiryId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath nickName = createString("nickName");

    public final QContactComment parent;

    public final StringPath username = createString("username");

    public QContactComment(String variable) {
        this(ContactComment.class, forVariable(variable), INITS);
    }

    public QContactComment(Path<? extends ContactComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QContactComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QContactComment(PathMetadata metadata, PathInits inits) {
        this(ContactComment.class, metadata, inits);
    }

    public QContactComment(Class<? extends ContactComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QContactComment(forProperty("parent"), inits.get("parent")) : null;
    }

}

