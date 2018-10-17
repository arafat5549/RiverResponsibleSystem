package com.jqmkj.WaterResponseSystem.entity;

import com.jqmkj.WaterResponseSystem.config.ExcelAttribute;
import com.jqmkj.WaterResponseSystem.util.FormSearchCondition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="c_deviceinfo")
public class Deviceinfo extends BaseEntity {

    //设备序列号,所属表字段为c_deviceinfo.sno
    @ExcelAttribute(name="序列号")
    @FormSearchCondition(like = "keyword")
    @Column(name="sno")
    private String sno;

    //商品名称,所属表字段为c_deviceinfo.name
    @ExcelAttribute(name="名称")
    @FormSearchCondition(like = "keyword")
    @Column(name="name")
    private String name;

    //协议,所属表字段为c_deviceinfo.protocol
    @ExcelAttribute(name="协议")
    @FormSearchCondition(like = "keyword")
    @Column(name="protocol")
    private String protocol;

    //所属品牌ID,所属表字段为c_deviceinfo.brand_id
    @Column(name="brand_id")
    private Long brandId;

    //所属分类ID,所属表字段为c_deviceinfo.category_id
    @Column(name="category_id")
    private Long categoryId;

    //供应商名称,所属表字段为c_deviceinfo.supplier
    @ExcelAttribute(name="供应商")
    @FormSearchCondition(like = "keyword")
    @Column(name="supplier")
    private String supplier;

    //状态,所属表字段为c_deviceinfo.status
    @Column(name="status")
    private Byte status;

    //图片是否上传完整，1是0否,所属表字段为c_deviceinfo.is_picture_finish
    @Column(name="is_picture_finish")
    private Boolean isPictureFinish;

    //是否删除：1是0否,所属表字段为c_deviceinfo.delete_flag
    @Column(name="delete_flag")
    private Boolean deleteFlag;

    //量程上限，所属表字段为c_deviceinfo.measure_max
    @Column(name="measure_max")
    private Double measureMax;

    //量程下限，所属表字段为c_deviceinfo.measure_min
    @Column(name="measure_min")
    private Double measureMin;

    //设备数据单位，所属表字段为c_deviceinfo.data_unit
    @Column(name="data_unit")
    private String dataUnit;
    //所要检索的因子组合
    @Column(length=1920,name="factors")
    private String factors;

    //private static final long serialVersionUID = 1L;
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

    @ExcelAttribute(name="品牌")
    @Transient
    private String brandName;

    @ExcelAttribute(name="分类")
    @Transient
    private String categoryName;
   /* @Transient
    private List<DeviceinfoPicture> deviceinfoPictures;*/

   private String deviceinfoPictureName;

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

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
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

    public Double getMeasureMax() {
        return measureMax;
    }

    public void setMeasureMax(Double measureMax) {
        this.measureMax = measureMax;
    }

    public Double getMeasureMin() {
        return measureMin;
    }

    public void setMeasureMin(Double measureMin) {
        this.measureMin = measureMin;
    }

    public String getDataUnit() {
        return dataUnit;
    }

    public void setDataUnit(String dataUnit) {
        this.dataUnit = dataUnit;
    }

  /*  public List<DeviceinfoPicture> getDeviceinfoPictures() {
        return deviceinfoPictures;
    }

    public void setDeviceinfoPictures(List<DeviceinfoPicture> deviceinfoPictures) {
        this.deviceinfoPictures = deviceinfoPictures;
    }*/

    public String getDeviceinfoPictureName() {
        return deviceinfoPictureName;
    }

    public void setDeviceinfoPictureName(String deviceinfoPictureName) {
        this.deviceinfoPictureName = deviceinfoPictureName;
    }

    public String getFactors() {
        return factors;
    }

    public void setFactors(String factors) {
        this.factors = factors;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append(super.toString());
        sb.append("Hash = ").append(hashCode());
        sb.append(", sno=").append(sno);
        sb.append(", name=").append(name);
        sb.append(", protocol=").append(protocol);
        sb.append(", brandId=").append(brandId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", supplier=").append(supplier);
        sb.append(", status=").append(status);
        sb.append(", isPictureFinish=").append(isPictureFinish);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append("]");
        return sb.toString();
    }


    public enum EnumColumn {
        id("id"),
        createPerson("createPerson"),
        createDate("createDate"),
        updatePerson("updatePerson"),
        updateDate("updateDate"),
        sno("sno"),
        name("name"),
        protocol("protocol"),
        brandId("brandId"),
        categoryId("categoryId"),
        supplier("supplier"),
        status("status"),
        isPictureFinish("isPictureFinish"),
        deleteFlag("deleteFlag"),
        measureMax("measureMax"),
        measureMin("measureMin"),
        dataUnit("dataUnit"),
        factors("factors"),
        deviceinfoPictureName("deviceinfoPictureName")
        ;
        private final String column;
        public String value() {return this.column;}
        public String getValue() {return this.column;}
        EnumColumn(String column) {this.column = column;}
    }


}