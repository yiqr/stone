package com.stone.app.core.security.model;

import com.stone.app.modules.sys.entity.SysUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author rose
 * @date 2022-11-19 15:54
 */
@Data
public class PersistentToken {

    @ApiModelProperty("用户编号/令牌subject")
    private String userId;

    @ApiModelProperty("令牌值")
    private String token;

    @ApiModelProperty("用户名/登陆编号")
    private String username;

    @ApiModelProperty("过期时间")
    private long expire;

    @ApiModelProperty("IP地址")
    private String ipAddress;

    public static PersistentToken of(SysUser sysUser) {

        return null;
    }
}
