package com.stone.app.modules.sys.entity;

import com.stone.app.core.jpa.mode.Domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author rose
 * @date 2022-11-19 15:47
 */
@ApiModel("认证令牌")
@Entity
@Table(name = "sys_persistent_token", indexes = {
        @Index(columnList = "username")
})
@Getter
@Setter
public class SysPersistentToken extends Domain {

    @NotNull
    @ApiModelProperty("令牌值")
    @Column(name = "token", nullable = false, length = 400)
    private String token;

    @ApiModelProperty("用户名/登陆编号")
    @Column(name = "username", nullable = false)
    private String username;

    @ApiModelProperty("过期时间")
    @Column(name = "expire")
    private LocalDateTime expire;

    @ApiModelProperty("IP地址")
    @Size(min = 0, max = 39)
    @Column(name = "ip_address", length = 39)
    private String ipAddress;
}