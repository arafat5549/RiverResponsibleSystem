package com.jqm.ssm.entity;

import com.jqm.ssm.dto.ExcelAttribute;

import java.io.Serializable;
import java.util.Date;

public class SystemLog implements Serializable {
    //自增主键,所属表字段为d_system_log.id
    private Integer id;

    //日志类型ID 1,所属表字段为d_system_log.log_type_id
    @ExcelAttribute(name="类型")
    private Byte logTypeId;

    //日志描述,所属表字段为d_system_log.remark
    @ExcelAttribute(name="描述")
    private String remark;

    //记录生成人,所属表字段为d_system_log.create_person
    @ExcelAttribute(name="创建人")
    private String createPerson;

    //记录生成时间,所属表字段为d_system_log.create_date
    @ExcelAttribute(name="创建时间")
    private Date createDate;

    private static final long serialVersionUID = 1L;

    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getLogTypeId() {
        return logTypeId;
    }

    public void setLogTypeId(Byte logTypeId) {
        this.logTypeId = logTypeId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", logTypeId=").append(logTypeId);
        sb.append(", remark=").append(remark);
        sb.append(", createPerson=").append(createPerson);
        sb.append(", createDate=").append(createDate);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id"),
        logTypeId("log_type_id"),
        remark("remark"),
        createPerson("create_person"),
        createDate("create_date");

        private final String column;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        Column(String column) {
            this.column = column;
        }

        public String desc() {
            return this.column + " DESC";
        }

        public String asc() {
            return this.column + " ASC";
        }
    }
}