package com.stone.app.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import com.stone.app.core.jpa.mode.Domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * @author rose
 * @date 2022-11-18 22:30
 */

@ApiModel("菜单")
@Getter
@Setter
@Entity
@Table(name = "sys_menu")
public class SysMenu extends Domain {

    @ApiModelProperty(name = "菜单名称")
    @Column(name = "name", length = NAME_LENGTH, nullable = false)
    private String name;

    @ApiModelProperty(name = "菜单类型(0-目录，1-菜单，2-按钮)")
    @Column(name = "type", length = 2)
    private int type;

    @ApiModelProperty(name = "图标")
    @Column(name = "icon", length = NAME_LENGTH)
    private String icon;

    @ApiModelProperty(name = "路由地址")
    @Column(name = "route_path", length = NAME_LENGTH)
    private String routePath;

    @ApiModelProperty(name = "接口")
    @Column(name = "api_uri", length = NAME_LENGTH)
    private String apiUri;

    @ApiModelProperty(name = "权限")
    @Column(name = "permission_code", length = NAME_LENGTH)
    private String permission_code;

    @ApiModelProperty("上级菜单")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private SysMenu parentId;

    @ApiModelProperty(name = "拥有该菜单的角色")
    @JsonIgnore
    @ManyToMany(mappedBy = "menus", fetch = FetchType.LAZY)
    private Set<SysRole> roles = Sets.newHashSet();

}
