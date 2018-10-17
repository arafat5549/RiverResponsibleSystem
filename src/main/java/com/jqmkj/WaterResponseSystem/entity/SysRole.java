package com.jqmkj.WaterResponseSystem.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * 角色
 * @author Administrator
 *
 */
@Entity
@Table(name="water_role")
public class SysRole extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(length=50,name="name")
	private String name;
	//角色描述,所属表字段为water_role.remark
	@Column(length=500,name="remark")
	private String remark;
	@ManyToMany(cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@JoinTable(name="water_user_role",joinColumns=@JoinColumn(name="role_id"),inverseJoinColumns=@JoinColumn(name="user_id"))
	private Set<SysUser> users = new HashSet<SysUser>();
	@ManyToMany(cascade = CascadeType.PERSIST,fetch=FetchType.LAZY)
	@JoinTable(name="water_role_permission",joinColumns=@JoinColumn(name="role_id"),inverseJoinColumns=@JoinColumn(name="permission_id"))
	private Set<SysPermission>  permissions = new HashSet<SysPermission>();
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

	public Set<SysUser> getUsers() {
		return users;
	}
	public void setUsers(Set<SysUser> users) {
		this.users = users;
	}
	public Set<SysPermission> getPermissions() {
		return  permissions;
	}
	public void setPermissions(Set<SysPermission>  permissions) {
		this. permissions =  permissions;
	}
}
