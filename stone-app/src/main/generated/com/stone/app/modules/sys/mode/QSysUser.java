package com.stone.app.modules.sys.mode;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSysUser is a Querydsl query type for SysUser
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSysUser extends EntityPathBase<SysUser> {

    private static final long serialVersionUID = 793433976L;

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

    public final StringPath phoneNo = createString("phoneNo");

    //inherited
    public final StringPath remark = _super.remark;

    public final StringPath salt = createString("salt");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final StringPath userId = createString("userId");

    public QSysUser(String variable) {
        super(SysUser.class, forVariable(variable));
    }

    public QSysUser(Path<? extends SysUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSysUser(PathMetadata metadata) {
        super(SysUser.class, metadata);
    }

}

