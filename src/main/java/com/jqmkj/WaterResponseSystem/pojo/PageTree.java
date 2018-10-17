package com.jqmkj.WaterResponseSystem.pojo;

import com.jqmkj.WaterResponseSystem.entity.BaseEntity;
import com.jqmkj.WaterResponseSystem.util.PageTreeAttrbute;
import com.jqmkj.WaterResponseSystem.util.ReflectionUtil;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class PageTree implements Serializable{
	private Long id;             
	private String text;    // 名称
	private String code;    //
	private String url;     // 菜单路径
	private String iconCls;    // 图标
	private Integer type;   // 资源类型, 0菜单 1功能
	private String state;
	private boolean checked = false;
	private Integer sort;   // 排序号
	private Long parentId; // 父级
	private String parentName; // 父级
	/*private String available;      //1有效，0删除
	private String createDate;
	private String modifyDate;
	private String description; // 描述*/
	private List<PageTree> children = new ArrayList<PageTree>();
	//private List<PageTree> nodes = new ArrayList<PageTree>();
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	/*public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}*/
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public List<PageTree> getChildren() {
		return children;
	}
	public void setChildren(List<PageTree> children) {
		this.children = children;
		//this.children=children;
	}

	public <T extends BaseEntity> T paseToEnTity(Class<T> t) throws IllegalAccessException, InstantiationException {
		T instance = t.newInstance();
		Field[] tFields = t.getDeclaredFields();
		Field[] tSuperFields = t.getSuperclass().getDeclaredFields();
		Field[] treeFields = this.getClass().getDeclaredFields();
		for (Field treeField : treeFields) {
			setInstance((T) instance, tSuperFields, treeField);
			setInstance((T) instance, tFields, treeField);
		}
		return instance;
	}

	private <T extends BaseEntity> void setInstance(T instance, Field[] tFields, Field treeField) {
		for (Field field : tFields){
            boolean isTreeColumn = field.isAnnotationPresent(PageTreeAttrbute.class);
            if(isTreeColumn){
                PageTreeAttrbute attr = field.getAnnotation(PageTreeAttrbute.class);
                if(treeField.getName().equals(attr.pageTreeColumn())){
                    Object obj = ReflectionUtil.getFieldValue(this,treeField.getName());
                    if(!StringUtils.isEmpty(obj)){
                        ReflectionUtil.setFieldValue(instance,field.getName(),obj);
                    }
                }
            }
        }
	}
}
