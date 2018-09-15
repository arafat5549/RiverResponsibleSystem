package com.jqm.ssm.entity;

import java.io.Serializable;
import java.util.Date;

public class Monitorsite implements Serializable {
    //自增主键,所属表字段为c_monitorsite.id
    private Integer id;

    //名称,所属表字段为c_monitorsite.name
    private String name;

    //负责人id,所属表字段为c_monitorsite.user_id
    private Integer userId;

    //父级id,所属表字段为c_monitorsite.parent_id
    private Integer parentId;

    //层级结构,所属表字段为c_monitorsite.structure
    private String structure;

    //记录生成人,所属表字段为c_monitorsite.create_person
    private String createPerson;

    //记录生成时间,所属表字段为c_monitorsite.create_date
    private Date createDate;

    //更新人,所属表字段为c_monitorsite.update_person
    private String updatePerson;

    //更新时间,所属表字段为c_monitorsite.update_date
    private Date updateDate;

    private static final long serialVersionUID = 1L;

    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure;
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

    public String getUpdatePerson() {
        return updatePerson;
    }

    public void setUpdatePerson(String updatePerson) {
        this.updatePerson = updatePerson;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", userId=").append(userId);
        sb.append(", parentId=").append(parentId);
        sb.append(", structure=").append(structure);
        sb.append(", createPerson=").append(createPerson);
        sb.append(", createDate=").append(createDate);
        sb.append(", updatePerson=").append(updatePerson);
        sb.append(", updateDate=").append(updateDate);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id"),
        name("name"),
        userId("user_id"),
        parentId("parent_id"),
        structure("structure"),
        createPerson("create_person"),
        createDate("create_date"),
        updatePerson("update_person"),
        updateDate("update_date");

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