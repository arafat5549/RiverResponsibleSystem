package com.jqm.ssm.entity;

import java.io.Serializable;
import java.util.Date;

public class Permission implements Serializable {
    //自增主键,所属表字段为water_permission.id
    private Integer id;

    //菜单资源名称,所属表字段为water_permission.name
    private String name;

    //菜单资源URL,所属表字段为water_permission.url
    private String url;

    //菜单资源简要描述,所属表字段为water_permission.remark
    private String remark;

    //父级id,所属表字段为water_permission.parent_id
    private Integer parentId;

    //菜单的层级结构,所属表字段为water_permission.structure
    private String structure;

    //排序号,所属表字段为water_permission.sort_no
    private Integer sortNo;

    //所属系统模块的标记,所属表字段为water_permission.module_flag
    private String moduleFlag;

    //记录生成人,所属表字段为water_permission.create_person
    private String createPerson;

    //记录生成时间,所属表字段为water_permission.create_date
    private Date createDate;

    //最后更新人,所属表字段为water_permission.update_person
    private String updatePerson;

    //最后更新时间,所属表字段为water_permission.update_date
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getModuleFlag() {
        return moduleFlag;
    }

    public void setModuleFlag(String moduleFlag) {
        this.moduleFlag = moduleFlag;
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
        sb.append(", url=").append(url);
        sb.append(", remark=").append(remark);
        sb.append(", parentId=").append(parentId);
        sb.append(", structure=").append(structure);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", moduleFlag=").append(moduleFlag);
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
        url("url"),
        remark("remark"),
        parentId("parent_id"),
        structure("structure"),
        sortNo("sort_no"),
        moduleFlag("module_flag"),
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