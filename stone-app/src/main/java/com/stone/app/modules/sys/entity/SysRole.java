package com.stone.app.modules.sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import com.stone.app.core.jpa.mode.Domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Set;

/**
 * @author rose
 * @date 2022/11/18 15:55
 */
@ApiModel("用户角色")
@Entity
@Table(name = "sys_role")
@Getter
@Setter
public class SysRole extends Domain {

    @ApiModelProperty(name = "角色名称")
    @Column(name = "name", nullable = false, length = NAME_LENGTH)
    private String name;

    @ApiModelProperty(name = "数据范围(0-所有,1-本人，2-所在机构，3-所在机构以及下属机构)")
    @Column(name = "data_scope", nullable = false, length = BOOLEAN_LENGTH)
    private int dataScope;

    @ApiModelProperty(name = "是否启用(0,1)")
    @Column(name = "active", nullable = false, length = BOOLEAN_LENGTH)
    private int active;

    @ApiModelProperty(name = "排序号")
    @Column(name = "order_num", nullable = false, length = 4)
    private int orderNum;

    @ApiModelProperty("角色归属机构")
    @ManyToOne(fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private SysOffice office;


    @ApiModelProperty(name = "拥有该角色的员工")
    @JsonIgnore
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SysUser> users = Sets.newHashSet();


    @ApiModelProperty(value = "角色菜单", required = true)
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_role_menu",
            joinColumns =
            @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "menu_id", referencedColumnName = "id")
    )
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SysMenu> menus = Sets.newHashSet();


    @ApiModelProperty(value = "角色权限", required = true)
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "sys_role_permission",
            joinColumns =
            @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns =
            @JoinColumn(name = "permission_id", referencedColumnName = "id")
    )
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<SysPermission> permissions = Sets.newHashSet();
}
