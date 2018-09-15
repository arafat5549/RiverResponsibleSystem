package com.jqm.ssm.entity;

import java.io.Serializable;
import java.util.Date;

public class Category implements Serializable {
    //自增主键,所属表字段为c_category.id
    private Integer id;

    //分类名称,所属表字段为c_category.name
    private String name;

    //中文名称的分类结构,所属表字段为c_category.struct_name
    private String structName;

    //层级：1,2,3,所属表字段为c_category.level
    private Byte level;

    //父级id,所属表字段为c_category.parent_id
    private Integer parentId;

    //排序号,所属表字段为c_category.sort_no
    private Integer sortNo;

    //是否删除：1是0否,所属表字段为c_category.delete_flag
    private Boolean deleteFlag;

    //记录生成人,所属表字段为c_category.create_person
    private String createPerson;

    //记录生成时间,所属表字段为c_category.create_date
    private Date createDate;

    //更新人,所属表字段为c_category.update_person
    private String updatePerson;

    //更新时间,所属表字段为c_category.update_date
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

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
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
        sb.append(", structName=").append(structName);
        sb.append(", level=").append(level);
        sb.append(", parentId=").append(parentId);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", deleteFlag=").append(deleteFlag);
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
        structName("struct_name"),
        level("level"),
        parentId("parent_id"),
        sortNo("sort_no"),
        deleteFlag("delete_flag"),
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