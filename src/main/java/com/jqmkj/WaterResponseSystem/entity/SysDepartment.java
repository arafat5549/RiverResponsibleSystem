package com.jqmkj.WaterResponseSystem.entity;

import javax.persistence.*;

/**
 * 组织架构
 * @author Administrator
 *
 */
@Entity
@Table(name="water_department")
public class SysDepartment extends BaseEntity {

	private static final long serialVersionUID = 1L;
	@Column(length=30,name="name")
	private String name;
	//部门描述,所属表字段为water_department.remark
	@Column(length=500,name="remark")
	private String remark;
	//部门的层级结构,所属表字段为water_department.structure
	@Column(length=50,name="structure")
	private String structure;
	@Column(length=50,name="icon_cls")
	private String iconCls;    // 图标
	@Column(length=11,name="sort")
	private Integer sort;   // 排序号
	@Column(length=11,name="parent_id")
	private Long parentId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}


	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}
