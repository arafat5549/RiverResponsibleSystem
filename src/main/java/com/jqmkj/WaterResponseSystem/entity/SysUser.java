package com.jqmkj.WaterResponseSystem.entity;

import com.jqmkj.WaterResponseSystem.config.ExcelAttribute;
import com.jqmkj.WaterResponseSystem.util.FormSearchCondition;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="water_user")
public class SysUser extends BaseEntity {

    //登录用户名,所属表字段为water_user.username
    @javax.persistence.Column( name = "username")
    @FormSearchCondition(like = "name")
    @ExcelAttribute(name="用户名")
    private String username;

    //登录密码,所属表字段为water_user.password
    @javax.persistence.Column( name = "password")
    private String password;

    //姓名,所属表字段为water_user.name
    @javax.persistence.Column( name = "fullname")
    @FormSearchCondition(like = "name")
    @ExcelAttribute(name="姓名")
    private String fullname;

    //性别：1男0女,所属表字段为water_user.gender
    @javax.persistence.Column( name = "gender")
    private Boolean gender;

    @javax.persistence.Column( name = "birthday")
    private String birthday;

    @javax.persistence.Column( name = "cellphone")
    private String cellphone;

    @javax.persistence.Column( name = "telephone")
    private String telephone;

    @javax.persistence.Column( name = "email")
    private String email;

    //是否管理员：1是0否,所属表字段为water_user.is_admin
    @javax.persistence.Column( name = "is_admin")
    private Boolean isAdmin;

    //外键，所属部门Id,所属表字段为water_user.department_id
    @javax.persistence.Column( name = "department_id")
    private Long departmentId;

    //是否锁定：1是0否,所属表字段为water_user.is_lock
    @javax.persistence.Column( name = "is_lock")
    private Boolean isLock;

    //是否删除：1是0否,所属表字段为water_user.delete_flag
    @javax.persistence.Column( name = "delete_flag")
    private Boolean deleteFlag;

    @ManyToMany(cascade = CascadeType.PERSIST,fetch= FetchType.LAZY)
    @JoinTable(name="water_user_role",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<SysRole> roles = new HashSet<SysRole>();


    private static final long serialVersionUID = 1L;


    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    @Transient
    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Set<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SysUser{" + super.toString()+
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", gender=" + gender +
                ", birthday='" + birthday + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", isAdmin=" + isAdmin +
                ", departmentId=" + departmentId +
                ", isLock=" + isLock +
                ", deleteFlag=" + deleteFlag +
                ", roles=" + roles +
                ", departmentName='" + departmentName + '\'' +
                '}';
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
        createTime("create_date"),
        updatePerson("update_person"),
        updateTime("update_date");

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
