package com.jqm.ssm.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.jqm.ssm.cache.RedisCache;
import com.jqm.ssm.dao.BrandDao;
import com.jqm.ssm.entity.Brand;
import com.jqm.ssm.entity.User;
import com.jqm.ssm.entity.relate.BrandSearchModel;
import com.jqm.ssm.service.IBrandService;
import com.jqm.ssm.util.DateUtils;
import com.jqm.ssm.util.PageUtil;
import com.jqm.ssm.util.SpringContextHolder;
import com.jqm.ssm.util.StringUtil;
import com.jqm.ssm.util.page.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 
 * IBrandService 接口实现类
 *
 * @author wang
 */
@Service(value = "brandService")
public class BrandServiceImpl implements IBrandService {

	private static final Logger LOG = LoggerFactory.getLogger(BrandServiceImpl.class);
	private static final String PREFIX_CAHCE = RedisCache.CAHCENAME + "|Brand|";
	private static final Class<Brand> SELF_CLASS = Brand.class;

	@Autowired
	private BrandDao mapper;

	@Autowired
	private RedisCache redisCache;

	@Autowired
	private SpringContextHolder springContextHolder;
	
	@Override
	public int selectCountByMap(Map<Object, Object> map) {
		return mapper.selectCountByMap(map);
	}
	@Override
	public Brand selectObjByMap(Map<Object, Object> map,String cacheKey){
		List<Brand> list = selectListByMap(map,cacheKey);
		return list.size()>=1 ? list.get(0) : null;
    }
	@Override
	public Brand selectObjByMap(Map<Object, Object> map){
		return selectObjByMap(map,null);
    }
	@Override
	public List<Brand> selectListByMap(Map<Object, Object> map,String cacheKey) {
		String cache_key = PREFIX_CAHCE + "selectListByMap";
		if(springContextHolder.getRedisAcaliable() && !Strings.isNullOrEmpty(cacheKey))
		{
			cache_key = cache_key + "|" + cacheKey;
			return redisCache.cacheList(cache_key, SELF_CLASS, map, mapper, LOG);
		}
		return mapper.selectListByMap(map);
	}
	@Override
	public List<Brand> selectListByMap( Map<Object, Object> map) {
    	return selectListByMap(map,"");
    }

	@Override
	public List<Brand> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey)
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
    public List<Brand> listPage(int offset,int limit,Map<Object, Object> map)
    {
    	return listPage(offset,limit,map,"");
    }

	@Override
	public Brand selectByPrimaryKey(Integer id) {
		String cache_key = PREFIX_CAHCE + "selectByPrimaryKey|" + id;
		if(springContextHolder.getRedisAcaliable())
		{
			Brand result_cache = redisCache.getCache(cache_key, SELF_CLASS);
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
	public int insert(Brand record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Brand record) {
		return mapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Brand record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Brand record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public Integer getBrandTotalBySearch(BrandSearchModel searchModel) {
		return mapper.getBrandTotalBySearch(searchModel);
	}

	@Override
	public List<Brand> getBrandListBySearch(BrandSearchModel searchModel) {
		return mapper.getBrandListBySearch(searchModel);
	}

	/**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

	@Override
	public String getBrandDataTables(BrandSearchModel searchModel) {
		Integer total = getBrandTotalBySearch(searchModel);
		List<Brand> brandList = getBrandListBySearch(searchModel);
		if(brandList==null || brandList.size()==0)
		{
			return "{\"iTotalRecords\":0,\"iTotalDisplayRecords\":0,\"aaData\":[]}";
		}
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("{\"iTotalRecords\":%d,\"iTotalDisplayRecords\":%d,\"aaData\":[",total, total));
		int i= 0;
		for(Brand u: brandList)
		{
			if(i != 0) sb.append(",");
			addDataRow(sb,u);
			i++;
		}
		sb.append("]}");
		return sb.toString();
	}

	@Override
	public Integer createBrand(Brand b, User operator) {
		setBrandInsert(b,operator);
		return mapper.insertSelective(b);
	}

	@Override
	public Brand getBrandById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer updateBrandById(Brand brand, User operator) {
		setBrandUpdate(brand,operator);
		return mapper.updateByPrimaryKeySelective(brand);
	}

	@Override
	public String getBrandDataRow(Integer id) {
		Brand u = getBrandById(id);
		StringBuilder sb = new StringBuilder();
		addDataRow(sb,u);
		return sb.toString();
	}

	@Override
	public Integer updateUserById(Brand brand, User operator) {
		setBrandUpdate(brand,operator);
		return mapper.updateByPrimaryKeySelective(brand);
	}

	public void setBrandInsert(Brand b, User operator) {
		Date d = new Date();
		b.setDeleteFlag(false);
		b.setCreatePerson(operator.getUsername());
		b.setUpdatePerson(operator.getUsername());
		b.setCreateDate(d);
		b.setUpdateDate(d);
	}

	private void setBrandUpdate(Brand b, User operator)
	{
		Date d = new Date();
		b.setUpdatePerson(operator.getUsername());
		b.setUpdateDate(d);
	}


	private void addDataRow(StringBuilder sb,Brand b) {
		sb.append("[");
		sb.append("\"<input type=\\\"checkbox\\\" name=\\\"id[]\\\" value=\\\"").append(b.getId()).append("\\\">\"");
		sb.append(",").append(b.getId());
		sb.append(",\"").append(b.getName()).append("\"");
		sb.append(",\"").append(b.getEname()).append("\"");
		sb.append(",\"").append(b.getWebsite()).append("\"");
		sb.append(",\"").append(b.getUpdatePerson()).append("\"");
		sb.append(",\"").append(StringUtil.formatDate(b.getUpdateDate(), "yyyy-MM-dd HH:mm:ss")).append("\"");

		sb.append(",\"")
				.append("<a href=\\\"javascript:Brand.update_click('").append(b.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-edit\\\"></i> 修改</a>")
				//.append("&nbsp;&nbsp;<a href=\\\"javascript:").append(u.getIsLock() ? "User.unlock('" : "User.lock('").append(u.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-").append(u.getIsLock() ? "un" : "").append("lock\\\"></i> ").append(u.getIsLock() ? "解锁" : "锁定").append("</a>")
				.append("&nbsp;&nbsp;<a href=\\\"javascript:Brand.remove('").append(b.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-times\\\"></i> 删除</a>")
				//.append("&nbsp;&nbsp;<a href=\\\"javascript:User.assign_click('").append(u.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-key\\\"></i> 分配角色</a>")
				.append("\"");

		sb.append("]");

	}
    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
