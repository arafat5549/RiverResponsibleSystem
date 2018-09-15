package com.jqm.ssm.entity.relate;

import com.jqm.ssm.misc.Constants;

import java.io.Serializable;

/**
 * 角色查询SearchModel
 */
public class RoleSearchModel extends BaseSearchModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//角色名称
	private String name;

	private String remark;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
