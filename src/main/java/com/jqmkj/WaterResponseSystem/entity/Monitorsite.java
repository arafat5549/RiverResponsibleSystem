package com.jqmkj.WaterResponseSystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "c_monitorsite")
public class Monitorsite extends BaseEntity {

    //名称,所属表字段为c_monitorsite.name
    @Column(name="name")
    private String name;

    //站点编号
    @Column(name="sno")
    private String sno;

    //断面编号
    @Column(name="section_no")
    private String sectionNo;

    //断面名称
    @Column(name="section_name")
    private String sectionName;

    //(断面)功能类型
    @Column(name = "function_type")
    private Byte functionType;

    //站点类型
    @Column(name="type")
    private Byte type;

    //所属行政区划
    @Column(name="regionalism")
    private String regionalism;

    //所属流域
    @Column(name="drainage_basin")
    private String drainageBasin;

    //所在水系
    @Column(name="river_system")
    private String riverSystem;

    @Column(name="position")
    private String position;

    //所在经度
    @Column(name="longitude")
    private String longitude;

    //所在维度
    @Column(name="latitude")
    private String latitude;

    //集成商
    @Column(name="integrator")
    private String integrator;

    //运营商
    @Column(name="operator")
    private String operator;

    //通讯方式
    @Column(name="communication")
    private String communication;

    //站点状态
    @Column(name="status")
    private Byte status;

    //站点图片
    @Column(name="photo")
    private String photo;


    //负责人id,所属表字段为c_monitorsite.user_id
    @Column(name="user_id")
    private Long userId;

    @Column(name="sort")
    private Integer sort;

    //父级id,所属表字段为c_monitorsite.parent_id
    @Column(name="parent_id")
    private Long parentId;
    //
    @Transient
    private Integer gpsType;
    public Integer getGpsType() {
        return gpsType;
    }
    public void setGpsType(Integer gpsType) {
        this.gpsType = gpsType;
    }
    //

    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSectionNo() {
        return sectionNo;
    }

    public void setSectionNo(String sectionNo) {
        this.sectionNo = sectionNo;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Byte getFunctionType() {
        return functionType;
    }

    public void setFunctionType(Byte functionType) {
        this.functionType = functionType;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getRegionalism() {
        return regionalism;
    }

    public void setRegionalism(String regionalism) {
        this.regionalism = regionalism;
    }

    public String getDrainageBasin() {
        return drainageBasin;
    }

    public void setDrainageBasin(String drainageBasin) {
        this.drainageBasin = drainageBasin;
    }

    public String getRiverSystem() {
        return riverSystem;
    }

    public void setRiverSystem(String riverSystem) {
        this.riverSystem = riverSystem;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getIntegrator() {
        return integrator;
    }

    public void setIntegrator(String integrator) {
        this.integrator = integrator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCommunication() {
        return communication;
    }

    public void setCommunication(String communication) {
        this.communication = communication;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Monitorsite{");
        sb.append(super.toString());
        sb.append("name='").append(name).append('\'');
        sb.append(", sno='").append(sno).append('\'');
        sb.append(", sectionNo='").append(sectionNo).append('\'');
        sb.append(", sectionName='").append(sectionName).append('\'');
        sb.append(", functionType=").append(functionType);
        sb.append(", type=").append(type);
        sb.append(", regionalism='").append(regionalism).append('\'');
        sb.append(", drainageBasin='").append(drainageBasin).append('\'');
        sb.append(", riverSystem='").append(riverSystem).append('\'');
        sb.append(", position='").append(position).append('\'');
        sb.append(", longitude='").append(longitude).append('\'');
        sb.append(", latitude='").append(latitude).append('\'');
        sb.append(", integrator='").append(integrator).append('\'');
        sb.append(", operator='").append(operator).append('\'');
        sb.append(", communication='").append(communication).append('\'');
        sb.append(", status=").append(status);
        sb.append(", photo='").append(photo).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", parentId=").append(parentId);
        sb.append('}');
        return sb.toString();
    }
}