package com.jqm.ssm.service.impl;

import java.util.*;

import com.jqm.ssm.entity.User;
import com.jqm.ssm.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jqm.ssm.service.IDepartmentService;
import com.jqm.ssm.dao.DepartmentDao;
import com.jqm.ssm.entity.Department;

import com.jqm.ssm.util.SpringContextHolder;
import com.jqm.ssm.cache.RedisCache;
import com.jqm.ssm.util.PageUtil;

import com.google.common.base.Strings;
import com.jqm.ssm.util.page.Page;
import com.jqm.ssm.misc.Constants;
import com.google.common.collect.Maps;
/**
 * 
 * IDepartmentService 接口实现类
 *
 * @author wang
 */
@Service(value = "departmentService")
public class DepartmentServiceImpl implements IDepartmentService {

	private static final Logger LOG = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	private static final String PREFIX_CAHCE = RedisCache.CAHCENAME + "|Department|";
	private static final Class<Department> SELF_CLASS = Department.class;

	@Autowired
	private DepartmentDao mapper;

	@Autowired
	private RedisCache redisCache;

	@Autowired
	private SpringContextHolder springContextHolder;
	
	@Override
	public int selectCountByMap(Map<Object, Object> map) {
		return mapper.selectCountByMap(map);
	}
	@Override
	public Department selectObjByMap(Map<Object, Object> map,String cacheKey){
		List<Department> list = selectListByMap(map,cacheKey);
		return list.size()>=1 ? list.get(0) : null;
    }
	@Override
	public Department selectObjByMap(Map<Object, Object> map){
		return selectObjByMap(map,null);
    }
	@Override
	public List<Department> selectListByMap(Map<Object, Object> map,String cacheKey) {
		String cache_key = PREFIX_CAHCE + "selectListByMap";
		if(springContextHolder.getRedisAcaliable() && !Strings.isNullOrEmpty(cacheKey))
		{
			cache_key = cache_key + "|" + cacheKey;
			return redisCache.cacheList(cache_key, SELF_CLASS, map, mapper, LOG);
		}
		return mapper.selectListByMap(map);
	}
	@Override
	public List<Department> selectListByMap( Map<Object, Object> map) {
    	return selectListByMap(map,"");
    }

	@Override
	public List<Department> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey)
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
    public List<Department> listPage(int offset,int limit,Map<Object, Object> map)
    {
    	return listPage(offset,limit,map,"");
    }

	@Override
	public Department selectByPrimaryKey(Integer id) {
		String cache_key = PREFIX_CAHCE + "selectByPrimaryKey|" + id;
		if(springContextHolder.getRedisAcaliable())
		{
			Department result_cache = redisCache.getCache(cache_key, SELF_CLASS);
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
	public int insert(Department record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Department record) {
		return mapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Department record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Department record) {
		return mapper.updateByPrimaryKey(record);
	}


    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

	private void setDepartmentInsert(Department department, User operator)
	{
		Date d = new Date();
		department.setCreatePerson(operator.getUsername());
		department.setUpdatePerson(operator.getUsername());
		department.setCreateDate(d);
		department.setUpdateDate(d);
	}
	private void setDepartmentUpdate(Department department,User operator)
	{
		Date d = new Date();
		department.setUpdatePerson(operator.getUsername());
		department.setUpdateDate(d);
	}

	@Override
	public Integer createDepartment(Department department, User user)
	{
		Department departmentParent = mapper.selectByPrimaryKey(department.getParentId());
		StringBuffer sb = new StringBuffer("");
		if (departmentParent != null) {
			//String structNameP = departmentParent.getStructName();
			sb.append(departmentParent.getStructure())
					.append("-");
		}else{
			sb.append("s").append("-");
		}
		setDepartmentInsert(department,user);
		int result = mapper.insertSelective(department);
		sb.append(department.getId());
		department.setStructure(sb.toString());
		updateByPrimaryKeySelective(department);
		return result;
		//生成structure
	/*	String structure = "1";
		Department parentDepartment  = mapper.selectByPrimaryKey(department.getParentId());

		Map<Object,Object> map = Maps.newHashMap();
		map.put("parentId",department.getParentId());
		List<Department> departments = mapper.selectListByMap(map);
		if(departments==null || departments.size()==0)
		{
			structure = parentDepartment.getStructure()+"-1";
		} else {
			Integer parentLevel = parentDepartment.getStructure().split("-").length;

			for(Department r:departments)
			{
				String[] structures = r.getStructure().split("-");
				if(structures.length == parentLevel + 1)
				{

					if(StringUtil.isNumber(structures[structures.length-1])&&StringUtil.compareTo(structures[structures.length-1], structure)>0)
					{
						structure = structures[structures.length-1];
					}
				}
			}
			structure = String.valueOf(Integer.parseInt(structure) + 1);
			structure = parentDepartment.getStructure()+"-" + structure;
		}
		department.setStructure(structure);

		setDepartmentInsert(department,user);
		return mapper.insertSelective(department);*/
	}

	@Override
	public Integer updateDepartment(Department department,User user) {
		setDepartmentUpdate(department,user);
		return mapper.updateByPrimaryKeySelective(department);
	}

	@Override
	public Boolean isUsedByUser(Integer departmentId)
	{
		return mapper.isUsedByUser(departmentId);
	}

	/**
	 * 获取treedata树型结构
	 */
	@Autowired
	public String getDepartmentTree()
	{
		List<Department> departmentList = mapper.selectListByMap(null);
		if(departmentList==null || departmentList.size()==0) return Constants.EmptyJsonObject;

		Collections.sort(departmentList,new ComparatorDepartment());

		Set<Integer> setParent = new HashSet<Integer>();
		for(Department d:departmentList)
		{
			setParent.add(d.getParentId());
		}

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		int i = 0;
		for(Department d:departmentList)
		{
			int level = d.getStructure().split("-").length;
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
	public List<Department> getDepartmentListForOption()
	{
		List<Department> departments = selectListByMap(null);
		if(departments==null || departments.size()==0)
		{
			return null;
		}

		List<Department> tempImmediateDepartments = new ArrayList<Department>();
		for(Department d:departments)
		{
			if(d.getParentId()==0)
			{
				//一级子节点
				tempImmediateDepartments.add(d);
			}
		}

		return buildDepartmentListForOption(departments,tempImmediateDepartments,"s");
	}

	/**
	 * 这种写法和直接对所有部门列表进行循环的写法比较
	 * 优点是：更少的循环次数，所以也就是更少的CPU计算和更快的返回时间；缺点是：更多的内存占用
	 * 也可以看做是一种空间换时间
	 * @param descendantDepartments
	 * @param immediateDepartments
	 * @param structure
	 * @return
	 */
	private List<Department> buildDepartmentListForOption(List<Department> descendantDepartments,List<Department> immediateDepartments,String structure)
	{
		if(descendantDepartments == null || descendantDepartments.size()==0
				||immediateDepartments == null || immediateDepartments.size()==0)
		{
			return null;
		}

		Collections.sort(immediateDepartments,new ComparatorDepartment());

		List<Department> result = new ArrayList<Department>();

		Integer index = 0;
		Integer level = structure.split("-").length;
		String prefix = "";
		for(int i=0;i<level-1;i++)
		{
			prefix += "&nbsp;&nbsp;&nbsp;";
		}
		for(Department department:immediateDepartments)
		{
			if(department.getStructure().split("-").length != level+1
					|| !department.getStructure().startsWith(structure+"-")
			)
			{
				continue;
			}
			department.setName(prefix + department.getName());
			result.add(department);

			List<Department> tempDescendantDepartment = new ArrayList<Department>();
			List<Department> tempImmediateDepartment = new ArrayList<Department>();
			for(Department r:descendantDepartments)
			{
				if(r.getStructure().startsWith(department.getStructure()+"-"))
				{
					if(r.getStructure().split("-").length == level + 2 )
					{
						//第一级子节点
						tempImmediateDepartment.add(r);
					}
					//所有子节点
					tempDescendantDepartment.add(r);
				}
			}
			if(tempDescendantDepartment!=null && tempDescendantDepartment.size()>0
					&& tempImmediateDepartment!=null && tempImmediateDepartment.size()>0)
			{
				List<Department> sub = buildDepartmentListForOption(tempDescendantDepartment,tempImmediateDepartment, department.getStructure());

				if(sub!=null && sub.size()>0)
				{
					result.addAll(sub);
				}
			}
			index++;
		}
		return result;
	}

	/**
	 * Department排序器，保证jsTree可以按照SortNo字段显示
	 */
	class ComparatorDepartment implements Comparator<Department> {
		public int compare(Department r1, Department r2) {
			int l1 = r1.getStructure().length();
			int l2 = r2.getStructure().length();
			if(l1 == l2 )
			{
				return r1.getSortNo().compareTo(r2.getSortNo());
			}
			return l1>l2?1:-1;
		}
	}

    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
