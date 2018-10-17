package com.jqmkj.WaterResponseSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="c_category")
public class Category extends BaseEntity {

    @Column(name="type")
    private Integer type;
    //分类名称,所属表字段为c_category.name
    @Column(name="name")
    private String name;

    //中文名称的分类结构,所属表字段为c_category.struct_name
    @Column(name="struct_name")
    private String structName;

    //层级：1,2,3,所属表字段为c_category.level
    @Column(name="level")
    private Byte level;

    //父级id,所属表字段为c_category.parent_id
    @Column(name="parent_id")
    private Integer parentId;

    //排序号,所属表字段为c_category.sort_no
    @Column(name="sort")
    private Integer sort;

    //是否删除：1是0否,所属表字段为c_category.delete_flag
    @Column(name="delete_flag")
    private Boolean deleteFlag;

    //private static final long serialVersionUID = 1L;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStructName() {
        return structName;
    }

    public void setStructName(String structName) {
        this.structName = structName;
    }

    public Byte getLevel() {
        return level;
    }

    public void setLevel(Byte level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(super.toString());
        sb.append(", name=").append(name);
        sb.append(", structName=").append(structName);
        sb.append(", level=").append(level);
        sb.append(", parentId=").append(parentId);
        sb.append(", sortNo=").append(sort);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append("]");
        return sb.toString();
    }
}