package com.stone.app.core.jpa.mode;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMode is a Querydsl query type for Mode
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QMode extends EntityPathBase<Mode<? extends java.io.Serializable>> {

    private static final long serialVersionUID = 698289839L;

    public static final QMode mode = new QMode("mode");

    public final SimplePath<java.io.Serializable> k = createSimple("k", java.io.Serializable.class);

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QMode(String variable) {
        super((Class) Mode.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QMode(Path<? extends Mode> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QMode(PathMetadata metadata) {
        super((Class) Mode.class, metadata);
    }

}

