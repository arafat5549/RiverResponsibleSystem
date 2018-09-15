package com.jqm.ssm.entity.relate;

import com.jqm.ssm.misc.Constants;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户查询SearchModel
 */
public class UserSearchModel extends BaseSearchModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

    /**
     * keyword模糊查询：包括 用户名,姓名
     */

    private Boolean gender;

    private Boolean isAdmin;

    private Integer departmentId;

    private Boolean isLock;

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean admin) {
        isAdmin = admin;
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

    public void setIsLock(Boolean lock) {
        isLock = lock;
    }


}
