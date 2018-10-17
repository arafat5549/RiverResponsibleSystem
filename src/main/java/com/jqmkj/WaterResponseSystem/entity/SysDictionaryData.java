package com.jqmkj.WaterResponseSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="d_dictionary_data")
public class SysDictionaryData extends BaseEntity {

	@Column(name="dictionary_type_id")
	private Long dictionaryTypeId;              //字典中的code,比如sex

	@Column(name="name")
	private String name;         				//字典具体值,比如男

	@Column(name="sort")
	private Integer sort;

	@Column(name="available")
	private Byte available = 1;     //1可用，0不可用

	@Transient
	private SysDictionaryType dictionaryType;//辅助字段

	public Long getDictionaryTypeId() {
		return dictionaryTypeId;
	}

	public void setDictionaryTypeId(Long dictionaryTypeId) {
		this.dictionaryTypeId = dictionaryTypeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Byte getAvailable() {
		return available;
	}

	public void setAvailable(Byte available) {
		this.available = available;
	}

	public SysDictionaryType getDictionaryType() {
		return dictionaryType;
	}

	public void setDictionaryType(SysDictionaryType dictionaryType) {
		this.dictionaryType = dictionaryType;
	}
}
