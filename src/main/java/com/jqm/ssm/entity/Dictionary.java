package com.jqm.ssm.entity;

import com.jqm.ssm.dto.ExcelAttribute;

import java.io.Serializable;
import java.util.Date;

public class Dictionary implements Serializable {
    //自增主键,所属表字段为d_dictionary.id
    private Integer id;

    //状态分组,所属表字段为d_dictionary.group
    @ExcelAttribute(name="分组")
    private String group;

    //状态代码,所属表字段为d_dictionary.code
    @ExcelAttribute(name="状态码")
    private Byte code;

    //状态名称,所属表字段为d_dictionary.name
    @ExcelAttribute(name="名称")
    private String name;

    //排序号,所属表字段为d_dictionary.sort_no
    private Byte sortNo;

    //记录生成人,所属表字段为d_dictionary.create_person
    @ExcelAttribute(name="创建人")
    private String createPerson;

    //记录生成时间,所属表字段为d_dictionary.create_date
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Byte getCode() {
        return code;
    }

    public void setCode(Byte code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getSortNo() {
        return sortNo;
    }

    public void setSortNo(Byte sortNo) {
        this.sortNo = sortNo;
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
        sb.append(", group=").append(group);
        sb.append(", code=").append(code);
        sb.append(", name=").append(name);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", createPerson=").append(createPerson);
        sb.append(", createDate=").append(createDate);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id"),
        group("group"),
        code("code"),
        name("name"),
        sortNo("sort_no"),
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