package com.jqmkj.WaterResponseSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 数据字典
 * @author Administrator
 *
 */
@Entity
@Table(name="d_dictionary_type")
public class SysDictionaryType extends BaseEntity {

	@Column(length=50,name = "name")
	private String name;			//字典类型,比如性别
	@Column(length=50,name = "code")
	private String code;            //固定不变,比如sex

	private Byte available = 1;     //1可用，0不可用

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Byte getAvailable() {
		return available;
	}

	public void setAvailable(Byte available) {
		this.available = available;
	}
}
