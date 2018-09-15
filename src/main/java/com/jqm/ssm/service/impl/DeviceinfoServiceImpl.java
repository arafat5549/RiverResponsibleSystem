package com.jqm.ssm.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jqm.ssm.dao.DeviceinfoPictureDao;
import com.jqm.ssm.entity.DeviceinfoPicture;
import com.jqm.ssm.entity.User;
import com.jqm.ssm.entity.relate.DeviceinfoSearchModel;
import com.jqm.ssm.util.StringUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jqm.ssm.service.IDeviceinfoService;
import com.jqm.ssm.dao.DeviceinfoDao;
import com.jqm.ssm.entity.Deviceinfo;

import com.jqm.ssm.util.SpringContextHolder;
import com.jqm.ssm.cache.RedisCache;
import com.jqm.ssm.util.PageUtil;

import com.google.common.base.Strings;
import com.jqm.ssm.util.page.Page;
import com.jqm.ssm.misc.Constants;
import com.google.common.collect.Maps;
/**
 * 
 * IDeviceinfoService 接口实现类
 *
 * @author wang
 */
@Service(value = "deviceinfoService")
public class DeviceinfoServiceImpl implements IDeviceinfoService {

	private static final Logger LOG = LoggerFactory.getLogger(DeviceinfoServiceImpl.class);
	private static final String PREFIX_CAHCE = RedisCache.CAHCENAME + "|Deviceinfo|";
	private static final Class<Deviceinfo> SELF_CLASS = Deviceinfo.class;
	@Value("${inc.basepath}")
	private String incPath;

	@Autowired
	private DeviceinfoDao mapper;
	@Autowired
	private DeviceinfoPictureDao dao;
	@Autowired
	private RedisCache redisCache;

	@Autowired
	private SpringContextHolder springContextHolder;
	
	@Override
	public int selectCountByMap(Map<Object, Object> map) {
		return mapper.selectCountByMap(map);
	}
	@Override
	public Deviceinfo selectObjByMap(Map<Object, Object> map,String cacheKey){
		List<Deviceinfo> list = selectListByMap(map,cacheKey);
		return list.size()>=1 ? list.get(0) : null;
    }
	@Override
	public Deviceinfo selectObjByMap(Map<Object, Object> map){
		return selectObjByMap(map,null);
    }
	@Override
	public List<Deviceinfo> selectListByMap(Map<Object, Object> map,String cacheKey) {
		String cache_key = PREFIX_CAHCE + "selectListByMap";
		if(springContextHolder.getRedisAcaliable() && !Strings.isNullOrEmpty(cacheKey))
		{
			cache_key = cache_key + "|" + cacheKey;
			return redisCache.cacheList(cache_key, SELF_CLASS, map, mapper, LOG);
		}
		return mapper.selectListByMap(map);
	}
	@Override
	public List<Deviceinfo> selectListByMap( Map<Object, Object> map) {
    	return selectListByMap(map,"");
    }

	@Override
	public List<Deviceinfo> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey)
	{
		String cache_key = PREFIX_CAHCE + "listPage|" + offset +"|"+ limit;
		if(map == null) map = Maps.newHashMap();
		if(offset>=0 && limit>=0) {
			Page page = new Page(offset, limit);
			map.put("page",page);
		}
		if(springContextHolder.getRedisAcaliable()&& !Strings.isNullOrEmpty(cacheKey))
		{
    		cache_key = cache_key + "|" + cacheKey;
			return redisCache.cacheList(cache_key, SELF_CLASS, PageUtil.getPageParamMap(offset, limit,map), mapper, LOG);
		}
		return mapper.selectListByMap(map);
	}
    @Override
    public List<Deviceinfo> listPage(int offset,int limit,Map<Object, Object> map)
    {
    	return listPage(offset,limit,map,"");
    }

	@Override
	public Deviceinfo selectByPrimaryKey(Integer id) {
		String cache_key = PREFIX_CAHCE + "selectByPrimaryKey|" + id;
		if(springContextHolder.getRedisAcaliable())
		{
			Deviceinfo result_cache = redisCache.getCache(cache_key, SELF_CLASS);
			if (result_cache != null) {
				LOG.info("get cache with key:" + cache_key);
			} else {
				result_cache = mapper.selectByPrimaryKey(id);
				if(result_cache!=null){
					redisCache.putCacheWithExpireTime(cache_key, result_cache, RedisCache.CAHCETIME);
					LOG.info("put cache with key:" + cache_key);
				}
				else{
					LOG.error(cache_key+":获取的是空的对象");
				}
				return result_cache;
			}
			return result_cache;
		}
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}
	

	@Override
	public int insert(Deviceinfo record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Deviceinfo record) {
		return mapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Deviceinfo record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Deviceinfo record) {
		return mapper.updateByPrimaryKey(record);
	}


	/**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

	@Override
	public String getDeviceinfoDataTables(DeviceinfoSearchModel searchModel) {
		Integer total = getDeviceinfoTotalBySearch(searchModel);
		List<Deviceinfo> deviceinfoList = getDeviceinfoListBySearch(searchModel);
		if(deviceinfoList==null || deviceinfoList.size()==0)
		{
			return "{\"iTotalRecords\":0,\"iTotalDisplayRecords\":0,\"aaData\":[]}";
		}

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("{\"iTotalRecords\":%d,\"iTotalDisplayRecords\":%d,\"aaData\":[",total,total));
		int i= 0;
		for(Deviceinfo u:deviceinfoList)
		{
			if(i != 0) sb.append(",");
			addDataRow(sb,u);
			i++;
		}
		sb.append("]}");
		return sb.toString();
	}

	@Override
	public List<Deviceinfo> getDeviceinfoListBySearch(DeviceinfoSearchModel searchModel) {
		return mapper.getDeviceinfoListBySearch(searchModel);
	}

	@Override
	public Integer getDeviceinfoTotalBySearch(DeviceinfoSearchModel searchModel) {
			return mapper.getDeviceinfoTotalBySearch(searchModel);
	}

	@Override
	public Deviceinfo getDeviceinfoById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer createDeviceinfo(Deviceinfo deviceinfo, User operator) {
		setDeviceinfoInsert(deviceinfo,operator);
		return mapper.insertSelective(deviceinfo);
	}

	@Override
	public Integer updateDeviceinfoById(Deviceinfo deviceinfo, User operator) {
		setDepartmentUpdate(deviceinfo,operator);
		return mapper.updateByPrimaryKeySelective(deviceinfo);
	}

    @Override
    public String getDeviceinfoDataRow(Integer id) {
        Deviceinfo u = getDeviceinfoById(id);
        StringBuilder sb = new StringBuilder();
        addDataRow(sb,u);
        return sb.toString();
    }

    private void setDeviceinfoInsert(Deviceinfo deviceinfo, User operator)
	{
		Date d = new Date();
		deviceinfo.setCreatePerson(operator.getUsername());
		deviceinfo.setUpdatePerson(operator.getUsername());
		deviceinfo.setCreateDate(d);
		deviceinfo.setUpdateDate(d);
	}
	private void setDepartmentUpdate(Deviceinfo deviceinfo,User operator)
	{
		Date d = new Date();
		deviceinfo.setUpdatePerson(operator.getUsername());
		deviceinfo.setUpdateDate(d);
	}

	private void addDataRow(StringBuilder sb, Deviceinfo u) {
		Map<Object,Object> map = new HashMap<>();
		map.put("deviceinfoId",u.getId());
		List<DeviceinfoPicture> deviceinfoPictures = dao.selectListByMap(map);
        String src = "";
		if(deviceinfoPictures != null && deviceinfoPictures.size()>0) {
            src = incPath + "/upload/photo/small/" + deviceinfoPictures.get(0).getName();
        }
	    String img = "<a href='javascript:Deviceinfo.image_click("+u.getId()+")'><img src='"+src+"'> </a>"; /*onload='ReSizePic(this,35)'*/
       // "<a href=\\\"javascript:Deviceinfo.update_click('").append(u.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-edit\\\"></i> 修改</a>";
		sb.append("[");
		sb.append("\"<input type=\\\"checkbox\\\" name=\\\"id[]\\\" value=\\\"").append(u.getId()).append("\\\">\"");
		sb.append(",").append(u.getId());
		sb.append(",\"").append(u.getSno()).append("\"");
		sb.append(",\"").append(u.getName()).append("\"");
		sb.append(",\"").append(u.getProtocol()).append("\"");
		sb.append(",\"").append(u.getBrandName()==null? "" : u.getBrandName()).append("\"");
		sb.append(",\"").append(u.getCategoryName()==null? "" : u.getCategoryName()).append("\"");
		sb.append(",\"").append(img).append("\"");
		sb.append(",\"").append(u.getSupplier()).append("\"");
		sb.append(",\"").append(u.getUpdatePerson()).append("\"");
		sb.append(",\"").append(StringUtil.formatDate(u.getUpdateDate(),"yyyy-MM-dd HH:mm:ss")).append("\"");
		sb.append(",\"")
				.append("<a href=\\\"javascript:Deviceinfo.update_click('").append(u.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-edit\\\"></i> 修改</a>")
				//.append("&nbsp;&nbsp;<a href=\\\"javascript:").append(u.getIsLock()?"Deviceinfo.unlock('":"User.lock('").append(u.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-").append(u.getIsLock()?"un":"").append("lock\\\"></i> ").append(u.getIsLock()?"解锁":"锁定").append("</a>")
				.append("&nbsp;&nbsp;<a href=\\\"javascript:Deviceinfo.remove('").append(u.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-times\\\"></i> 删除</a>")
				.append("&nbsp;&nbsp;<a href=\\\"javascript:Deviceinfo.assign_click('").append(u.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-upload\\\"></i> 图片上传</a>")
				.append("\"");
		sb.append("]");
	}


	/**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
