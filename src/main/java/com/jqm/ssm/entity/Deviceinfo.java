package com.jqm.ssm.entity;

import com.jqm.ssm.dto.ExcelAttribute;

import java.io.Serializable;
import java.util.Date;

public class Deviceinfo implements Serializable {
    //自增主键,所属表字段为c_deviceinfo.id
    private Integer id;

    //设备序列号,所属表字段为c_deviceinfo.sno
    @ExcelAttribute(name="序列号")
    private String sno;

    //商品名称,所属表字段为c_deviceinfo.name
    @ExcelAttribute(name="名称")
    private String name;

    //协议,所属表字段为c_deviceinfo.protocol
    @ExcelAttribute(name="协议")
    private String protocol;

    //所属品牌ID,所属表字段为c_deviceinfo.brand_id
    private Integer brandId;

    //所属分类ID,所属表字段为c_deviceinfo.category_id
    private Integer categoryId;

    //供应商名称,所属表字段为c_deviceinfo.supplier
    @ExcelAttribute(name="供应商")
    private String supplier;

    //状态,所属表字段为c_deviceinfo.status
    private Byte status;

    //图片是否上传完整，1是0否,所属表字段为c_deviceinfo.is_picture_finish
    private Boolean isPictureFinish;

    //是否删除：1是0否,所属表字段为c_deviceinfo.delete_flag
    private Boolean deleteFlag;

    //记录生成人,所属表字段为c_deviceinfo.create_person
    private String createPerson;

    //记录生成时间,所属表字段为c_deviceinfo.create_date
    private Date createDate;

    //更新人,所属表字段为c_deviceinfo.update_person
    private String updatePerson;

    //更新时间,所属表字段为c_deviceinfo.update_date
    private Date updateDate;

    private static final long serialVersionUID = 1L;

    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

    @ExcelAttribute(name="品牌")
    private String brandName;

    @ExcelAttribute(name="分类")
    private String categoryName;

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getIsPictureFinish() {
        return isPictureFinish;
    }

    public void setIsPictureFinish(Boolean isPictureFinish) {
        this.isPictureFinish = isPictureFinish;
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
        sb.append(", sno=").append(sno);
        sb.append(", name=").append(name);
        sb.append(", protocol=").append(protocol);
        sb.append(", brandId=").append(brandId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", supplier=").append(supplier);
        sb.append(", status=").append(status);
        sb.append(", isPictureFinish=").append(isPictureFinish);
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
        sno("sno"),
        name("name"),
        protocol("protocol"),
        brandId("brand_id"),
        categoryId("category_id"),
        supplier("supplier"),
        status("status"),
        isPictureFinish("is_picture_finish"),
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