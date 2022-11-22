package com.stone.app.modules.sys.entity;

import com.google.common.collect.Sets;
import com.stone.app.core.jpa.mode.Domain;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author rose
 * @date 2022/11/9 09:34
 */
@Getter
@Setter
@Entity
@Table(name = "sys_user",
        indexes = {@Index(columnList = "login_name"), @Index(columnList = "phone_no")}
)
public class SysUser extends Domain {
    @NotBlank
    @ApiModelProperty(name = "登录名")
    @Column(name = "login_name", nullable = false, length = ID_LENGTH)
    private String loginName;

    @NotBlank
    @ApiModelProperty(name = "登录密码")
    @Column(name = "login_pwd", nullable = false, length = 40)
    private String loginPwd;

//    @NotBlank
//    @ApiModelProperty(name = "加密盐")
//    @Column(name = "salt", nullable = false, length = 64)
//    private String salt;

    @ApiModelProperty(name = "用户头像")
    @Column(name = "avatar")
    private String avatar;

    @ApiModelProperty(name = "用户名称")
    @Column(name = "name", length = NAME_LENGTH)
    private String name;

    @ApiModelProperty(name = "用户昵称")
    @Column(name = "nickname", length = NAME_LENGTH)
    private String nickname;

    @ApiModelProperty(name = "性别")
    @Column(name = "gender")
    private Integer gender;

    @ApiModelProperty(name = "用户手机号(唯一)")
    @Column(name = "phone_no", length = 16)
    private String phoneNo;

    @ApiModelProperty(value = "邮箱")
    @Column(name = "email", length = NAME_LENGTH)
    private String email;

    @ApiModelProperty(value = "真实姓名")
    @Column(name = "actual_name", length = NAME_LENGTH)
    private String actualName;

    @ApiModelProperty(name = "是否锁定")
    @Column(name = "locked")
    private boolean locked;

    @ApiModelProperty(value = "锁定时间")
    @Column(name = "locked_time")
    private LocalDateTime lockedTime;

    @ApiModelProperty("员工所属部门")
    @ManyToOne(fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private SysOffice office;

    @ApiModelProperty(value = "用户角色", required = true)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_user_role",//中间表的名称
            //中间表user_role_rel的字段关联sys_user表的主键user_id
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            //中间表user_role_rel字段关联sys_role表的主键字段role_id
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SysRole> roles = Sets.newHashSet();
}
