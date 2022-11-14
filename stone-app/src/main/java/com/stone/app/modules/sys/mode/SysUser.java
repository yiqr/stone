package com.stone.app.modules.sys.mode;

import com.stone.app.core.jpa.mode.Domain;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author rose
 * @date 2022/11/9 09:34
 */
@Getter
@Setter
@Entity
@Table(name = "sys_user", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id"}),
        indexes = {@Index(columnList = "login_name"), @Index(columnList = "phone_no")}
)
public class SysUser extends Domain {

    @NotBlank
    @ApiModelProperty(name = "用户ID（唯一）")
    @Column(name = "user_id", nullable = false, length = ID_LENGTH)
    private String userId;

    @NotBlank
    @ApiModelProperty(name = "登录名")
    @Column(name = "login_name", nullable = false, length = ID_LENGTH)
    private String loginName;

    @NotBlank
    @ApiModelProperty(name = "登录密码")
    @Column(name = "login_pwd", nullable = false, length = 40)
    private String loginPwd;

    @NotBlank
    @ApiModelProperty(name = "加密盐")
    @Column(name = "salt", nullable = false, length = 64)
    private String salt;

    @ApiModelProperty(name = "用户头像")
    @Column(name = "avatar")
    private String avatar;

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

    @ApiModelProperty(value = "删除标记")
    @Column(name = "del_flag", length = BOOLEAN_LENGTH)
    private Integer delFlag;

    @ApiModelProperty(value = "删除时间")
    @Column(name = "del_time")
    private LocalDateTime delTime;
}
