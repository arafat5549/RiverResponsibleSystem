package com.jqmkj.WaterResponseSystem.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MonitorSiteTree implements Serializable{
    private Long id;
    private String text;    // 名称
    private String code;    //
    private String url;     // 菜单路径
    private String iconCls;    // 图标
    private String state;
    private boolean checked = false;
    private Integer sort;   // 排序号
    private Long parentId; // 父级
    private String parentName; // 父级
    private List<MonitorSiteTree> children = new ArrayList<MonitorSiteTree>();
    private String sno;
    private String sectionNo;
    private String sectionName;
    private Byte functionType;
    private Byte type;
    private String regionalism;
    private String drainageBasin;
    private String riverSystem;
    private String position;
    private String longitude;
    private String latitude;
    private String integrator;
    private String operator;
    private String communication;
    private Byte status;
    private String photo;
    private Long userId;
    private String username;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<MonitorSiteTree> getChildren() {
        return children;
    }

    public void setChildren(List<MonitorSiteTree> children) {
        this.children = children;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
