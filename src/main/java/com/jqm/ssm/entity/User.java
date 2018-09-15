package com.jqm.ssm.entity;

import com.jqm.ssm.dto.ExcelAttribute;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    //UUID主键,所属表字段为water_user.id

    private Integer id;

    //登录用户名,所属表字段为water_user.username
    @ExcelAttribute(name="用户名")
    private String username;

    //登录密码,所属表字段为water_user.password
    private String password;

    //姓名,所属表字段为water_user.fullname
    @ExcelAttribute(name = "姓名")
    private String fullname;

    //性别：1男0女,所属表字段为water_user.gender
    @ExcelAttribute(name="性别")
    private Boolean gender;

    //是否管理员：1是0否,所属表字段为water_user.is_admin
    private Boolean isAdmin;

    //外键，所属部门Id,所属表字段为water_user.department_id
    private Integer departmentId;

    //是否锁定：1是0否,所属表字段为water_user.is_lock
    @ExcelAttribute(name="是否锁定")
    private Boolean isLock;

    //是否删除：1是0否,所属表字段为water_user.delete_flag
    private Boolean deleteFlag;

    //记录生成人,所属表字段为water_user.create_person
    private String createPerson;

    //记录生成时间,所属表字段为water_user.create_date
    private Date createDate;

    //最后更新人,所属表字段为water_user.update_person
    @ExcelAttribute(name="最后修改人")
    private String updatePerson;

    //最后更新时间,所属表字段为water_user.update_date
    @ExcelAttribute(name="最后修改时间")
    private Date updateDate;

    private static final long serialVersionUID = 1L;

    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    @ExcelAttribute(name="部门")
    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Boolean getIsLock() {
        return isLock;
    }

    public void setIsLock(Boolean isLock) {
        this.isLock = isLock;
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
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", fullname=").append(fullname);
        sb.append(", gender=").append(gender);
        sb.append(", isAdmin=").append(isAdmin);
        sb.append(", departmentId=").append(departmentId);
        sb.append(", isLock=").append(isLock);
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
        username("username"),
        password("password"),
        fullname("fullname"),
        gender("gender"),
        isAdmin("is_admin"),
        departmentId("department_id"),
        isLock("is_lock"),
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