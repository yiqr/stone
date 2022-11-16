package com.stone.app.core.jpa.mode;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDomain is a Querydsl query type for Domain
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QDomain extends EntityPathBase<Domain> {

    private static final long serialVersionUID = 784242384L;

    public static final QDomain domain = new QDomain("domain");

    public final QMode _super = new QMode(this);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath createdBy = createString("createdBy");

    public final StringPath k = createString("k");

    public final StringPath remark = createString("remark");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public final StringPath updatedBy = createString("updatedBy");

    public QDomain(String variable) {
        super(Domain.class, forVariable(variable));
    }

    public QDomain(Path<? extends Domain> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDomain(PathMetadata metadata) {
        super(Domain.class, metadata);
    }

}

