package com.jqm.ssm.entity.relate;

import com.jqm.ssm.misc.Constants;

import java.io.Serializable;

public class DeviceinfoSearchModel extends BaseSearchModel implements Serializable {

    private static final long serialVersionUID = 1L;

    //序列号
    private String sno;
    //名称
    private String name;

    private String protocol;

    //所属品牌ID,所属表字段为c_deviceinfo.brand_id
    private Integer brandId;

    //所属分类ID,所属表字段为c_deviceinfo.category_id
    private Integer categoryId;

    //供应商名称,所属表字段为c_deviceinfo.supplier
    private String supplier;
    //状态
    private Byte status;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
