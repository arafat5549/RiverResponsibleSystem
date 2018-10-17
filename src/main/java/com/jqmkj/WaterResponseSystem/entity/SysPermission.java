package com.jqmkj.WaterResponseSystem.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "water_permission")
public class SysPermission extends BaseEntity {
    @Column(length=50,name = "name")
    private String name;    // 名称

    @Column(length=50,name = "url")
    private String url;     // 菜单路径
    @Column(length=50,name = "icon_cls")
    private String iconCls;    // 图标
    @Column(length=1,name = "type")
    private Integer type;   // 资源类型, 0菜单 1功能
    @Column(length=11,name = "sort")
    private Integer sort;   // 排序号
    //所属系统模块的标记,所属表字段为water_permission.module_flag
    private String moduleFlag;
    //菜单资源简要描述,所属表字段为water_permission.remark
    @Column(length=500,name = "remark")
    private String remark;
    //菜单的层级结构,所属表字段为water_permission.structure
    @Column(length=50,name = "structure")
    private String structure;
    @Column(length=11,name = "parent_id")
    private Long parentId;
    @ManyToMany(cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
    @JoinTable(name="water_role_permission",joinColumns=@JoinColumn(name="permission_id"),inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<SysRole> roles = new HashSet<SysRole>();
    public SysPermission(){}
    public SysPermission(Long id){
        super(id);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getIconCls() {
        return iconCls;
    }
    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getSort() {
        return sort;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getModuleFlag() {
        return moduleFlag;
    }

    public void setModuleFlag(String moduleFlag) {
        this.moduleFlag = moduleFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Set<SysRole> getRoles() {
        return roles;
    }
    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }
}
