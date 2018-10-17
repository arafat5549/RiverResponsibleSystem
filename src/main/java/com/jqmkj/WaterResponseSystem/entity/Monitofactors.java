package com.jqmkj.WaterResponseSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity//监测因子
@Table(name="c_monitofactors")
public class Monitofactors extends  BaseEntity {

    @Column(name="sno")
    private String sno;//监测因子编号

    @Column(name="origin_sno")
    private String originSno;//原始监测因子编号

    @Column(name="name")
    private String name;

    @Column(name="ename")
    private String ename;

    @Column(name="unit")
    private String unit;//单位

    @Column(name="overproof_max",columnDefinition="double default 0")
    private Double overproofMax;//超标上限

    @Column(name="overproof_min",columnDefinition="double default 0")
    private Double overproofMin;//超标下限

    @Column(name="invalid_max",columnDefinition="double default 0")
    private Double invalidMax;//无效上限

    @Column(name="invalid_min",columnDefinition="double default 0")
    private Double invalidMin;//无效下限

    @Column(name="warn_max",columnDefinition="double default 0")
    private Integer warnMax;//报警上限

    @Column(name="warn_min",columnDefinition="double default 0")
    private Integer warnMin;//报警下限

   /* @Column(name="decimal_digits")
    private Integer decimalDigits;//小数位数*/

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

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getOverproofMax() {
        return overproofMax;
    }

    public void setOverproofMax(Double overproofMax) {
        this.overproofMax = overproofMax;
    }

    public Double getOverproofMin() {
        return overproofMin;
    }

    public void setOverproofMin(Double overproofMin) {
        this.overproofMin = overproofMin;
    }

    public Double getInvalidMax() {
        return invalidMax;
    }

    public void setInvalidMax(Double invalidMax) {
        this.invalidMax = invalidMax;
    }

    public Double getInvalidMin() {
        return invalidMin;
    }

    public void setInvalidMin(Double invalidMin) {
        this.invalidMin = invalidMin;
    }

    public Integer getWarnMax() {
        return warnMax;
    }

    public void setWarnMax(Integer warnMax) {
        this.warnMax = warnMax;
    }

    public Integer getWarnMin() {
        return warnMin;
    }

    public void setWarnMin(Integer warnMin) {
        this.warnMin = warnMin;
    }

/*    public Integer getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(Integer decimalDigits) {
        this.decimalDigits = decimalDigits;
    }*/

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Monitofactors{");
        sb.append(super.toString());
        sb.append("sno='").append(sno).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", ename='").append(ename).append('\'');
        sb.append(", unit='").append(unit).append('\'');
        sb.append(", overproofMax=").append(overproofMax);
        sb.append(", overproofMin=").append(overproofMin);
        sb.append(", invalidMax=").append(invalidMax);
        sb.append(", invalidMin=").append(invalidMin);
        sb.append(", warnMax=").append(warnMax);
        sb.append(", warnMin=").append(warnMin);
     /*   sb.append(", decimalDigits=").append(decimalDigits);*/
        sb.append('}');
        return sb.toString();
    }
}
