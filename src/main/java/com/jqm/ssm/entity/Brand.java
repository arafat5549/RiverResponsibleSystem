package com.jqm.ssm.entity;

import com.jqm.ssm.dto.ExcelAttribute;

import java.io.Serializable;
import java.util.Date;

public class Brand implements Serializable {
    //自增主键,所属表字段为c_brand.id
    private Integer id;

    //品牌名称,所属表字段为c_brand.name
    @ExcelAttribute(name = "名称")
    private String name;

    //英文名称,所属表字段为c_brand.ename
    @ExcelAttribute(name = "ename")
    private String ename;

    //品牌网址,所属表字段为c_brand.website
    @ExcelAttribute(name = "网址")
    private String website;

    //品牌大图(140*120),所属表字段为c_brand.pic_large
    private String picLarge;

    //品牌中图(110*50),所属表字段为c_brand.pic_middle
    private String picMiddle;

    //品牌小图(85*40),所属表字段为c_brand.pic_small
    private String picSmall;

    //归属哪个字母：A-Z,所属表字段为c_brand.letter
    private String letter;

    //排序号,所属表字段为c_brand.sort_no
    private Integer sortNo;

    //是否删除：1是0否,所属表字段为c_brand.delete_flag
    private Boolean deleteFlag;

    //记录生成人,所属表字段为c_brand.create_person
    private String createPerson;

    //记录生成时间,所属表字段为c_brand.create_date
    private Date createDate;

    //更新人,所属表字段为c_brand.update_person
    private String updatePerson;

    //更新时间,所属表字段为c_brand.update_date
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
        sb.append(", ename=").append(ename);
        sb.append(", website=").append(website);
        sb.append(", picLarge=").append(picLarge);
        sb.append(", picMiddle=").append(picMiddle);
        sb.append(", picSmall=").append(picSmall);
        sb.append(", letter=").append(letter);
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
        ename("ename"),
        website("website"),
        picLarge("pic_large"),
        picMiddle("pic_middle"),
        picSmall("pic_small"),
        letter("letter"),
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