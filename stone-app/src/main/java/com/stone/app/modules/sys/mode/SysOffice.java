package com.stone.app.modules.sys.mode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.stone.app.core.jpa.mode.Domain;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author rose
 * @date 2022/11/14 18:42
 */
@Getter
@Setter
@Entity
@Table(name = "sys_office", uniqueConstraints = @UniqueConstraint(columnNames = {"office_id"}),
        indexes = {@Index(columnList = "parent_id")}
)
public class SysOffice extends Domain {

    @NotBlank
    @ApiModelProperty(name = "机构ID（唯一）")
    @Column(name = "office_id", nullable = false, length = ID_LENGTH)
    private String userId;

    @NotBlank
    @ApiModelProperty(name = "父机构ID")
    @Column(name = "parent_id", nullable = false, length = ID_LENGTH)
    private String parentId;

    @NotBlank
    @ApiModelProperty(name = "父机构ID")
    @Column(name = "parent_ids", nullable = false, length = 160)
    private String parentIds;

    @NotBlank
    @ApiModelProperty(name = "机构名称")
    @Column(name = "name", nullable = false, length = NAME_LENGTH)
    private String name;

    @NotBlank
    @ApiModelProperty(name = "排序")
    @Column(name = "sort", nullable = false, length = 4)
    private Integer sort;

    @ApiModelProperty(name = "电话号码")
    @Column(name = "phone_no", nullable = false, length = 20)
    private String phoneNo;

    @ApiModelProperty(name = "传真")
    @Column(name = "fax", nullable = false, length = 120)
    private String fax;

    @ApiModelProperty(name = "邮件地址")
    @Column(name = "email", nullable = false, length = 120)
    private String email;

    @ApiModelProperty(name = "机构地址")
    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @ApiModelProperty(name = "邮政编码")
    @Column(name = "zip_code", nullable = false, length = 10)
    private String zipCode;

    @ApiModelProperty(value = "删除标记")
    @Column(name = "del_flag", length = BOOLEAN_LENGTH)
    private Integer delFlag;

    @ApiModelProperty(value = "删除时间")
    @Column(name = "del_time")
    private LocalDateTime delTime;

    @ApiModelProperty("机构员工")
    @JsonIgnore
    @OneToMany(mappedBy = "office", fetch = FetchType.LAZY)
    @OrderBy("createdAt asc ")
    private List<SysUser> users = Lists.newArrayList();

    @ApiModelProperty("机构员工")
    @JsonIgnore
    @OneToMany(mappedBy = "office", fetch = FetchType.LAZY)
    @OrderBy("createdAt asc ")
    private List<SysRole> roles = Lists.newArrayList();

}
