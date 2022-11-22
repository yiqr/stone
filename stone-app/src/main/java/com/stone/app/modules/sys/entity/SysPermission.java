package com.stone.app.modules.sys.entity;

import com.google.common.collect.Sets;
import com.stone.app.core.jpa.mode.Domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * @author rose
 * @date 2022-11-19 8:11
 */
@ApiModel("接口权限")
@Entity
@Table(name = "sys_permission", uniqueConstraints = {
        @UniqueConstraint(columnNames = "code"),
})
@Getter
@Setter
public class SysPermission extends Domain {

    @NotBlank
    @ApiModelProperty(value = "权限编码", required = true)
    @Column(name = "code", nullable = false, length = NAME_LENGTH)
    private String code;

    @NotBlank
    @ApiModelProperty(value = "权限名称", required = true)
    @Column(name = "name", nullable = false, length = NAME_LENGTH)
    private String name;

    @ApiModelProperty(value = "拥有该权限的角色")
    @ManyToMany(mappedBy = "permissions")
    private Set<SysRole> roles = Sets.newHashSet();
}
