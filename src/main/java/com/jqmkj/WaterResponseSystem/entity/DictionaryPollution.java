package com.jqmkj.WaterResponseSystem.entity;

import java.io.Serializable;
import java.util.Date;

public class DictionaryPollution implements Serializable {
    //自增主键,所属表字段为d_dictionary_pollution.id
    private Integer id;

    //编码,所属表字段为d_dictionary_pollution.code
    private String code;

    //原编码,所属表字段为d_dictionary_pollution.origin_code
    private String originCode;

    //中文名称,所属表字段为d_dictionary_pollution.name
    private String name;

    //英文名称,所属表字段为d_dictionary_pollution.enname
    private String enname;

    //应用范围,所属表字段为d_dictionary_pollution.range
    private String range;

    //缺省计量单位1,所属表字段为d_dictionary_pollution.unit
    private String unit;

    //缺省计量单位2,所属表字段为d_dictionary_pollution.unit2
    private String unit2;

    //数据类型,所属表字段为d_dictionary_pollution.data_type
    private String dataType;

    //监测因子类别,所属表字段为d_dictionary_pollution.group
    private String group;

    //超标上限,所属表字段为d_dictionary_pollution.range_max
    private String rangeMax;

    //超标下限,所属表字段为d_dictionary_pollution.range_min
    private String rangeMin;

    //无效上限,所属表字段为d_dictionary_pollution.avaliable_max
    private String avaliableMax;

    //无效下限,所属表字段为d_dictionary_pollution.avaliable_min
    private String avaliableMin;

    //报警上限,所属表字段为d_dictionary_pollution.warn_max
    private String warnMax;

    //报警下限,所属表字段为d_dictionary_pollution.warn_min
    private String warnMin;

    //记录生成时间,所属表字段为d_dictionary_pollution.create_date
    private Date createDate;

    private static final long serialVersionUID = 1L;

    
    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/
    
    
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOriginCode() {
        return originCode;
    }

    public void setOriginCode(String originCode) {
        this.originCode = originCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit2() {
        return unit2;
    }

    public void setUnit2(String unit2) {
        this.unit2 = unit2;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(String rangeMax) {
        this.rangeMax = rangeMax;
    }

    public String getRangeMin() {
        return rangeMin;
    }

    public void setRangeMin(String rangeMin) {
        this.rangeMin = rangeMin;
    }

    public String getAvaliableMax() {
        return avaliableMax;
    }

    public void setAvaliableMax(String avaliableMax) {
        this.avaliableMax = avaliableMax;
    }

    public String getAvaliableMin() {
        return avaliableMin;
    }

    public void setAvaliableMin(String avaliableMin) {
        this.avaliableMin = avaliableMin;
    }

    public String getWarnMax() {
        return warnMax;
    }

    public void setWarnMax(String warnMax) {
        this.warnMax = warnMax;
    }

    public String getWarnMin() {
        return warnMin;
    }

    public void setWarnMin(String warnMin) {
        this.warnMin = warnMin;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", code=").append(code);
        sb.append(", originCode=").append(originCode);
        sb.append(", name=").append(name);
        sb.append(", enname=").append(enname);
        sb.append(", range=").append(range);
        sb.append(", unit=").append(unit);
        sb.append(", unit2=").append(unit2);
        sb.append(", dataType=").append(dataType);
        sb.append(", group=").append(group);
        sb.append(", rangeMax=").append(rangeMax);
        sb.append(", rangeMin=").append(rangeMin);
        sb.append(", avaliableMax=").append(avaliableMax);
        sb.append(", avaliableMin=").append(avaliableMin);
        sb.append(", warnMax=").append(warnMax);
        sb.append(", warnMin=").append(warnMin);
        sb.append(", createDate=").append(createDate);
        sb.append("]");
        return sb.toString();
    }

    public enum Column {
        id("id"),
        code("code"),
        originCode("origin_code"),
        name("name"),
        enname("enname"),
        range("range"),
        unit("unit"),
        unit2("unit2"),
        dataType("data_type"),
        group("group"),
        rangeMax("range_max"),
        rangeMin("range_min"),
        avaliableMax("avaliable_max"),
        avaliableMin("avaliable_min"),
        warnMax("warn_max"),
        warnMin("warn_min"),
        createDate("create_date");

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