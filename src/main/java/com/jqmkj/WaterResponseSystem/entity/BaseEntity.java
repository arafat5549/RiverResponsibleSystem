package com.jqmkj.WaterResponseSystem.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jqmkj.WaterResponseSystem.config.ExcelAttribute;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * 公共实体对象
 * @author Administrator
 *
 */
@MappedSuperclass
public class BaseEntity implements Serializable{

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY,generator = "JDBC")//insert返回主键
	@GeneratedValue(strategy=GenerationType.IDENTITY)
//	@GeneratedValue(generator = "idGenerator")
//	@GenericGenerator(name = "idGenerator", strategy = "com.jqmkj.WaterResponseSystem.test",
//			parameters = { @Parameter(name = "dataCenterID", value = "20") ,@Parameter(name = "idLength", value = "10")})

	@Column(name = "id")
	private Long id;
	//private String available = "1";
	@Column(length=20,name="create_person")
	private String createPerson;

	@Column(length=25,name="create_date")
	private LocalDateTime createDate;

	@Column(length=20,name="update_person")
	private String updatePerson;
	@Column(length=25,name="update_date")
	@ExcelAttribute(name="最后操作时间")
	private LocalDateTime updateDate;

	public BaseEntity(){};

	public  BaseEntity(Long id){ this.id = id; }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public String getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return
				"id=" + id +
				", createPerson='" + createPerson + '\'' +
				", createDate=" + createDate +
				", updatePerson='" + updatePerson + '\'' +
				", updateDate=" + updateDate ;
	}
}
