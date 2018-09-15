package com.jqm.ssm.entity.relate;

import com.jqm.ssm.misc.Constants;

import java.io.Serializable;

public class BrandSearchModel extends BaseSearchModel implements Serializable {

    private static final long serialVersionUID = 1L;

   /* public BrandSearchModel(){}*/

    /*public BrandSearchModel(Integer pageNo, Integer pageSize) {
        super(pageNo, pageSize);
    }*/

    //名称
    private String name;
    //英文名
    private String ename;

    private String website;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
