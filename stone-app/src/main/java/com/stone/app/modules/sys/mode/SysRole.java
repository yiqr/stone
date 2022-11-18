package com.stone.app.modules.sys.mode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Sets;
import com.stone.app.core.jpa.mode.Domain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Set;

/**
 * @author rose
 * @date 2022/11/18 15:55
 */
@ApiModel("用户角色")
@Entity
@Table(name = "sys_role", uniqueConstraints = {
        @UniqueConstraint(columnNames = "role_id"),
})
@Getter
@Setter
public class SysRole extends Domain {

    @ApiModelProperty(name = "角色编号")
    @Column(name = "role_id", nullable = false, length = ID_LENGTH)
    private String roleId;

    @ApiModelProperty(name = "角色名称")
    @Column(name = "role_name", nullable = false, length = NAME_LENGTH)
    private String roleName;

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
    @ManyToOne(optional = false)
    @JoinColumn(name = "office_id", nullable = false)
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private SysOffice office;


    @ApiModelProperty(name = "拥有该角色的员工")
    @JsonIgnore
    @ManyToMany
    @JoinTable(name="sys_user_role_rel",//中间表的名称
            //中间表user_role_rel字段关联sys_role表的主键字段role_id
            joinColumns={@JoinColumn(name="role_id",referencedColumnName="role_id")},
            //中间表user_role_rel的字段关联sys_user表的主键user_id
            inverseJoinColumns={@JoinColumn(name="user_id",referencedColumnName="user_id")}
    )
    private Set<SysUser> users = Sets.newHashSet();

}
