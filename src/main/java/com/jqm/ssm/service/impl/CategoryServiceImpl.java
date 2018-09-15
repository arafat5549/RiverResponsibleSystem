package com.jqm.ssm.service.impl;

import java.util.*;

import com.jqm.ssm.entity.User;
import com.jqm.ssm.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jqm.ssm.service.ICategoryService;
import com.jqm.ssm.dao.CategoryDao;
import com.jqm.ssm.entity.Category;

import com.jqm.ssm.util.SpringContextHolder;
import com.jqm.ssm.cache.RedisCache;
import com.jqm.ssm.util.PageUtil;

import com.google.common.base.Strings;
import com.jqm.ssm.util.page.Page;
import com.jqm.ssm.misc.Constants;
import com.google.common.collect.Maps;
/**
 * 
 * ICategoryService 接口实现类
 *
 * @author wang
 */
@Service(value = "categoryService")
public class CategoryServiceImpl implements ICategoryService {

	private static final Logger LOG = LoggerFactory.getLogger(CategoryServiceImpl.class);
	private static final String PREFIX_CAHCE = RedisCache.CAHCENAME + "|Category|";
	private static final Class<Category> SELF_CLASS = Category.class;

	@Autowired
	private CategoryDao mapper;

	@Autowired
	private RedisCache redisCache;

	@Autowired
	private SpringContextHolder springContextHolder;
	
	@Override
	public int selectCountByMap(Map<Object, Object> map) {
		return mapper.selectCountByMap(map);
	}
	@Override
	public Category selectObjByMap(Map<Object, Object> map,String cacheKey){
		List<Category> list = selectListByMap(map,cacheKey);
		return list.size()>=1 ? list.get(0) : null;
    }
	@Override
	public Category selectObjByMap(Map<Object, Object> map){
		return selectObjByMap(map,null);
    }
	@Override
	public List<Category> selectListByMap(Map<Object, Object> map,String cacheKey) {
		String cache_key = PREFIX_CAHCE + "selectListByMap";
		if(springContextHolder.getRedisAcaliable() && !Strings.isNullOrEmpty(cacheKey))
		{
			cache_key = cache_key + "|" + cacheKey;
			return redisCache.cacheList(cache_key, SELF_CLASS, map, mapper, LOG);
		}
		return mapper.selectListByMap(map);
	}
	@Override
	public List<Category> selectListByMap( Map<Object, Object> map) {
    	return selectListByMap(map,"");
    }

	@Override
	public List<Category> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey)
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
    public List<Category> listPage(int offset,int limit,Map<Object, Object> map)
    {
    	return listPage(offset,limit,map,"");
    }

	@Override
	public Category selectByPrimaryKey(Integer id) {
		String cache_key = PREFIX_CAHCE + "selectByPrimaryKey|" + id;
		if(springContextHolder.getRedisAcaliable())
		{
			Category result_cache = redisCache.getCache(cache_key, SELF_CLASS);
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
	public int insert(Category record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Category record) {
		return mapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Category record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Category record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public String getCategoryTree() {
			List<Category> categoryList = mapper.selectListByMap(null);
			if(categoryList==null || categoryList.size()==0) return Constants.EmptyJsonObject;

			Collections.sort(categoryList,new ComparatorCategory());

			Set<Integer> setParent = new HashSet<Integer>();
			for(Category d:categoryList)
			{
				setParent.add(d.getParentId());
			}

			StringBuilder sb = new StringBuilder();
			sb.append("[");
			int i = 0;
			for(Category d:categoryList)
			{
				int level = d.getStructName().split("-").length;
				if(i!=0)
				{
					sb.append(",");
				}
				i++;
				sb.append("{")
						.append("\"id\":\"").append(d.getId()).append("\"")
						.append(",\"parent\":\"").append(d.getParentId()==0?"#":d.getParentId()).append("\"")
						.append(",\"text\":\"").append(d.getName()).append("\"")
						.append(",\"li_attr\":{\"sortNo\":").append(d.getSortNo()).append("}");;
				//前两个级别默认打开
				if(level <=3)
				{
					sb.append(",\"state\":{\"opened\":true}");
				}
				//最后一个级别换个绿色图标
				if(!setParent.contains(d.getId()))
				{
					sb.append(", \"icon\": \"fa fa-briefcase icon-success\"");
				}
				sb.append("}");
			}
			sb.append("]");
			return sb.toString();
	}

	@Override
	public Integer createCategory(Category category, User user) {
		Category categoryParent = mapper.selectByPrimaryKey(category.getParentId());
		StringBuffer sb = new StringBuffer("");
		if (categoryParent != null) {
			//String structNameP = categoryParent.getStructName();
			sb.append(categoryParent.getStructName())
			  .append("-");
		}else{
			sb.append("s").append("-");
		}
		setCategoryInsert(category,user);
		int result = mapper.insertSelective(category);
		sb.append(category.getId());
		category.setStructName(sb.toString());
		updateByPrimaryKeySelective(category);
		return result;
	}

	@Override
	public Integer updateCategory(Category category, User user) {
		setCategoryUpdate(category,user);
		return mapper.updateByPrimaryKeySelective(category);
	}


	/**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

	private void setCategoryInsert(Category category, User operator)
	{
		Date d = new Date();
		category.setCreatePerson(operator.getUsername());
		category.setUpdatePerson(operator.getUsername());
		category.setCreateDate(d);
		category.setUpdateDate(d);
	}
	private void setCategoryUpdate(Category category,User operator)
	{
		Date d = new Date();
		category.setUpdatePerson(operator.getUsername());
		category.setUpdateDate(d);
	}

	/**
	 * Category排序器，保证jsTree可以按照SortNo字段显示
	 */
	class ComparatorCategory implements Comparator<Category> {
		public int compare(Category r1, Category r2) {
			int l1 = r1.getStructName().length();
			int l2 = r2.getStructName().length();
			if(l1 == l2 )
			{
				return r1.getSortNo().compareTo(r2.getSortNo());
			}
			return l1>l2?1:-1;
		}
	}

	@Override
	public List<Category> getCategoryListForOption()
	{
		List<Category> categorys = selectListByMap(null);
		//categorys.forEach(c->c.setStructName("s-"+c.getStructName()));
		if(categorys==null || categorys.size()==0)
		{
			return null;
		}

		List<Category> tempImmediateCategorys = new ArrayList<Category>();
		for(Category d:categorys)
		{
			if(d.getParentId()==0)
			{
				//一级子节点
				tempImmediateCategorys.add(d);
			}
		}

		return buildCategoryListForOption(categorys,tempImmediateCategorys,"s");
	}

	/**
	 * 这种写法和直接对所有部门列表进行循环的写法比较
	 * 优点是：更少的循环次数，所以也就是更少的CPU计算和更快的返回时间；缺点是：更多的内存占用
	 * 也可以看做是一种空间换时间
	 * @param descendantCategorys
	 * @param immediateCategorys
	 * @param structure
	 * @return
	 */
	private List<Category> buildCategoryListForOption(List<Category> descendantCategorys,List<Category> immediateCategorys,String structure)
	{
		if(descendantCategorys == null || descendantCategorys.size()==0
				||immediateCategorys == null || immediateCategorys.size()==0)
		{
			return null;
		}

		Collections.sort(immediateCategorys,new ComparatorCategory());

		List<Category> result = new ArrayList<Category>();

		Integer index = 0;
		Integer level = structure.split("-").length;
		String prefix = "";
		for(int i=0;i<level-1;i++)
		{
			prefix += "&nbsp;&nbsp;&nbsp;";
		}
		for(Category category:immediateCategorys)
		{
			if(category.getStructName().split("-").length != level+1
					|| !category.getStructName().startsWith(structure+"-")
					)
			{
				continue;
			}
			category.setName(prefix + category.getName());
			result.add(category);

			List<Category> tempDescendantCategory = new ArrayList<Category>();
			List<Category> tempImmediateCategory = new ArrayList<Category>();
			for(Category r:descendantCategorys)
			{
				if(r.getStructName().startsWith(category.getStructName()+"-"))
				{
					if(r.getStructName().split("-").length == level + 2 )
					{
						//第一级子节点
						tempImmediateCategory.add(r);
					}
					//所有子节点
					tempDescendantCategory.add(r);
				}
			}
			if(tempDescendantCategory!=null && tempDescendantCategory.size()>0
					&& tempImmediateCategory!=null && tempImmediateCategory.size()>0)
			{
				List<Category> sub = buildCategoryListForOption(tempDescendantCategory,tempImmediateCategory, category.getStructName());

				if(sub!=null && sub.size()>0)
				{
					result.addAll(sub);
				}
			}
			index++;
		}
		return result;
	}

    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
