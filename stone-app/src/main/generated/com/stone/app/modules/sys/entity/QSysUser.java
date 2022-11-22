package com.stone.app.modules.sys.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.processing.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSysUser is a Querydsl query type for SysUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSysUser extends EntityPathBase<SysUser> {

    private static final long serialVersionUID = 793433976L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSysUser sysUser = new QSysUser("sysUser");

    public final com.stone.app.core.jpa.mode.QDomain _super = new com.stone.app.core.jpa.mode.QDomain(this);

    public final StringPath actualName = createString("actualName");

    public final StringPath avatar = createString("avatar");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Integer> delFlag = createNumber("delFlag", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> delTime = createDateTime("delTime", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final NumberPath<Integer> gender = createNumber("gender", Integer.class);

    //inherited
    public final StringPath k = _super.k;

    public final StringPath loginName = createString("loginName");

    public final StringPath loginPwd = createString("loginPwd");

    public final StringPath nickname = createString("nickname");

    public final QSysOffice office;

    public final StringPath phoneNo = createString("phoneNo");

    //inherited
    public final StringPath remark = _super.remark;

    public final SetPath<SysRole, QSysRole> roles = this.<SysRole, QSysRole>createSet("roles", SysRole.class, QSysRole.class, PathInits.DIRECT2);

    public final StringPath salt = createString("salt");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public QSysUser(String variable) {
        this(SysUser.class, forVariable(variable), INITS);
    }

    public QSysUser(Path<? extends SysUser> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSysUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSysUser(PathMetadata metadata, PathInits inits) {
        this(SysUser.class, metadata, inits);
    }

    public QSysUser(Class<? extends SysUser> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.office = inits.isInitialized("office") ? new QSysOffice(forProperty("office")) : null;
    }

}

