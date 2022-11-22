package com.stone.app.modules.sys.entity;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.*;

import javax.annotation.processing.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QSysOffice is a Querydsl query type for SysOffice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSysOffice extends EntityPathBase<SysOffice> {

    private static final long serialVersionUID = 2097083177L;

    public static final QSysOffice sysOffice = new QSysOffice("sysOffice");

    public final com.stone.app.core.jpa.mode.QDomain _super = new com.stone.app.core.jpa.mode.QDomain(this);

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Integer> delFlag = createNumber("delFlag", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> delTime = createDateTime("delTime", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final StringPath fax = createString("fax");

    //inherited
    public final StringPath k = _super.k;

    public final StringPath name = createString("name");

    public final StringPath parentId = createString("parentId");

    public final StringPath parentIds = createString("parentIds");

    public final StringPath phoneNo = createString("phoneNo");

    //inherited
    public final StringPath remark = _super.remark;

    public final ListPath<SysRole, QSysRole> roles = this.<SysRole, QSysRole>createList("roles", SysRole.class, QSysRole.class, PathInits.DIRECT2);

    public final NumberPath<Integer> sort = createNumber("sort", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final ListPath<SysUser, QSysUser> users = this.<SysUser, QSysUser>createList("users", SysUser.class, QSysUser.class, PathInits.DIRECT2);

    public final StringPath zipCode = createString("zipCode");

    public QSysOffice(String variable) {
        super(SysOffice.class, forVariable(variable));
    }

    public QSysOffice(Path<? extends SysOffice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysOffice(PathMetadata metadata) {
        super(SysOffice.class, metadata);
    }

}

