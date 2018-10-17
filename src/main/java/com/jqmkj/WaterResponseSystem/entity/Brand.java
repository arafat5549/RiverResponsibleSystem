package com.jqmkj.WaterResponseSystem.entity;

import com.jqmkj.WaterResponseSystem.config.ExcelAttribute;
import com.jqmkj.WaterResponseSystem.util.FormSearchCondition;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="c_brand")
public class Brand extends BaseEntity {

    //品牌名称,所属表字段为c_brand.name
    @ExcelAttribute(name = "名称")
    @Column(name="name")
    @FormSearchCondition(like = "keyword")
    private String name;

    //英文名称,所属表字段为c_brand.ename
    @ExcelAttribute(name = "ename")
    @Column(name="ename")
    @FormSearchCondition(like = "keyword")
    private String ename;

    //品牌网址,所属表字段为c_brand.website
    @ExcelAttribute(name = "网址")
    @Column(name="website")
    private String website;

    //品牌大图(140*120),所属表字段为c_brand.pic_large
    @Column(name="pic_large")
    private String picLarge;

    //品牌中图(110*50),所属表字段为c_brand.pic_middle
    @Column(name="pic_middle")
    private String picMiddle;

    //品牌小图(85*40),所属表字段为c_brand.pic_small
    @Column(name="pic_small")
    private String picSmall;

    //归属哪个字母：A-Z,所属表字段为c_brand.letter
    @Column(name="letter")
    private String letter;

    //排序号,所属表字段为c_brand.sort_no
    @Column(name="sort_no")
    private Integer sortNo;

    //是否删除：1是0否,所属表字段为c_brand.delete_flag
    @Column(name="delete_flag")
    private Boolean deleteFlag;

    private static final long serialVersionUID = 1L;


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

    public String getPicLarge() {
        return picLarge;
    }

    public void setPicLarge(String picLarge) {
        this.picLarge = picLarge;
    }

    public String getPicMiddle() {
        return picMiddle;
    }

    public void setPicMiddle(String picMiddle) {
        this.picMiddle = picMiddle;
    }

    public String getPicSmall() {
        return picSmall;
    }

    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(super.toString());
        sb.append(", name=").append(name);
        sb.append(", ename=").append(ename);
        sb.append(", website=").append(website);
        sb.append(", picLarge=").append(picLarge);
        sb.append(", picMiddle=").append(picMiddle);
        sb.append(", picSmall=").append(picSmall);
        sb.append(", letter=").append(letter);
        sb.append(", sortNo=").append(sortNo);
        sb.append(", deleteFlag=").append(deleteFlag);
        sb.append("]");
        return sb.toString();
    }

}