package com.jqm.ssm.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jqm.ssm.entity.relate.UserSearchModel;
import com.jqm.ssm.util.StringUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jqm.ssm.service.IUserService;
import com.jqm.ssm.dao.UserDao;
import com.jqm.ssm.entity.User;

import com.jqm.ssm.util.SpringContextHolder;
import com.jqm.ssm.cache.RedisCache;
import com.jqm.ssm.util.PageUtil;

import com.google.common.base.Strings;
import com.jqm.ssm.util.page.Page;
import com.jqm.ssm.misc.Constants;
import com.google.common.collect.Maps;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 
 * IUserService 接口实现类
 *
 * @author wang
 */
@Service(value = "userService")
public class UserServiceImpl implements IUserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	private static final String PREFIX_CAHCE = RedisCache.CAHCENAME + "|User|";
	private static final Class<User> SELF_CLASS = User.class;

	@Autowired
	private UserDao mapper;

	@Autowired
	private RedisCache redisCache;

	@Autowired
	private SpringContextHolder springContextHolder;
	
	@Override
	public int selectCountByMap(Map<Object, Object> map) {
		return mapper.selectCountByMap(map);
	}
	@Override
	public User selectObjByMap(Map<Object, Object> map,String cacheKey){
		List<User> list = selectListByMap(map,cacheKey);
		return list.size()>=1 ? list.get(0) : null;
    }
	@Override
	public User selectObjByMap(Map<Object, Object> map){
		return selectObjByMap(map,null);
    }
	@Override
	public List<User> selectListByMap(Map<Object, Object> map,String cacheKey) {
		String cache_key = PREFIX_CAHCE + "selectListByMap";
		if(springContextHolder.getRedisAcaliable() && !Strings.isNullOrEmpty(cacheKey))
		{
			cache_key = cache_key + "|" + cacheKey;
			return redisCache.cacheList(cache_key, SELF_CLASS, map, mapper, LOG);
		}
		return mapper.selectListByMap(map);
	}
	@Override
	public List<User> selectListByMap( Map<Object, Object> map) {
    	return selectListByMap(map,"");
    }

	@Override
	public List<User> listPage(int offset,int limit,Map<Object, Object> map,String cacheKey)
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
    public List<User> listPage(int offset,int limit,Map<Object, Object> map)
    {
    	return listPage(offset,limit,map,"");
    }

	@Override
	public User selectByPrimaryKey(Integer id) {
		String cache_key = PREFIX_CAHCE + "selectByPrimaryKey|" + id;
		if(springContextHolder.getRedisAcaliable())
		{
			User result_cache = redisCache.getCache(cache_key, SELF_CLASS);
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
	public int insert(User record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(User record) {
		return mapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return mapper.updateByPrimaryKey(record);
	}


    /**  START 以下为自己编写的代码区域 一般是多表之间的联合查询  START  **/

	private void setPersonInsert(User user,User operator)
	{
		Date d = new Date();
		user.setIsLock(false);
		user.setDeleteFlag(false);
		user.setCreatePerson(operator.getUsername());
		user.setUpdatePerson(operator.getUsername());
		user.setCreateDate(d);
		user.setUpdateDate(d);
	}
	private void setPersonUpdate(User user, User operator)
	{
		Date d = new Date();
		user.setUpdatePerson(operator.getUsername());
		user.setUpdateDate(d);
	}

	@Override
	public User getUserByUsername(String username) {
		return mapper.getUserByUsername(username);

	}

	@Override
	public User getUserById(Integer id)
	{
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer getUserTotalBySearch(UserSearchModel searchModel)
	{
		return mapper.getUserTotalBySearch(searchModel);
	}

	@Override
	public List<User> getUserListBySearch(UserSearchModel searchModel)
	{
		return mapper.getUserListBySearch(searchModel);
	}

	@Override
	public Integer createUser(User user,User operator)
	{
		setPersonInsert(user,operator);
		user.setPassword(StringUtil.makeMD5(user.getPassword()));
		return mapper.insertSelective(user);
	}

	@Override
	public Integer updateUserById(User user,User operator)
	{
		setPersonUpdate(user,operator);
		return mapper.updateByPrimaryKeySelective(user);
	}

	@Override
	@Transactional
	public Integer deleteUserById(Integer id,User operator)
	{
		User user = getUserById(id);
		if(user==null)
		{
			return 0;
		}

		//本设计中，设置用户是不能物理删除的，保证了所有记录用户操作日志的地方，关联用户可以用inner join，提高效率
		mapper.deleteUserRoleById(id);
		user.setDeleteFlag(true);
		setPersonUpdate(user,operator);
		return mapper.updateByPrimaryKeySelective(user);
	}

	@Override
	@Transactional
	public String assignRole(Integer id,String selectedStr)
	{
		String[] selectedArr = selectedStr.split(",");
		List<Integer> roleIds = new ArrayList<Integer>();

		for(String s:selectedArr)
		{
			if(StringUtils.hasText(s))
			{
				roleIds.add(Integer.parseInt(s));
			}
		}

		mapper.deleteUserRoleById(id);
		if(roleIds.size()>0)
		{
			mapper.assignRoles(roleIds, id);
		}

		return Constants.Success;
	}

	@Override
	public String getUserDataTables(UserSearchModel searchModel)
	{
		Integer total = getUserTotalBySearch(searchModel);
		List<User> userList = getUserListBySearch(searchModel);
		if(userList==null || userList.size()==0)
		{
			return "{\"iTotalRecords\":0,\"iTotalDisplayRecords\":0,\"aaData\":[]}";
		}

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("{\"iTotalRecords\":%d,\"iTotalDisplayRecords\":%d,\"aaData\":[",total,total));
		int i= 0;
		for(User u:userList)
		{
			if(i != 0) sb.append(",");
			addDataRow(sb,u);
			i++;
		}
		sb.append("]}");
		return sb.toString();
	}

	@Override
	public String getUserDataRow(Integer id)
	{
		User u = getUserById(id);
		StringBuilder sb = new StringBuilder();
		addDataRow(sb,u);
		return sb.toString();
	}

	private void addDataRow(StringBuilder sb,User u)
	{
		sb.append("[");
		sb.append("\"<input type=\\\"checkbox\\\" name=\\\"id[]\\\" value=\\\"").append(u.getId()).append("\\\">\"");
		sb.append(",").append(u.getId());
		sb.append(",\"").append(u.getUsername()).append("\"");
		sb.append(",\"").append(u.getFullname()).append("\"");
		sb.append(",\"").append(u.getGender()?"男":"女").append("\"");
		sb.append(",\"").append(u.getIsAdmin()?"管理员":"普通").append("\"");
		sb.append(",\"").append(u.getIsLock()?"是":"否").append("\"");
		sb.append(",\"").append(u.getDepartmentName()==null?"":u.getDepartmentName()).append("\"");
		sb.append(",\"").append(u.getUpdatePerson()).append("\"");
		sb.append(",\"").append(StringUtil.formatDate(u.getUpdateDate(),"yyyy-MM-dd HH:mm:ss")).append("\"");
		sb.append(",\"")
				.append("<a href=\\\"javascript:User.update_click('").append(u.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-edit\\\"></i> 修改</a>")
				.append("&nbsp;&nbsp;<a href=\\\"javascript:").append(u.getIsLock()?"User.unlock('":"User.lock('").append(u.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-").append(u.getIsLock()?"un":"").append("lock\\\"></i> ").append(u.getIsLock()?"解锁":"锁定").append("</a>")
				.append("&nbsp;&nbsp;<a href=\\\"javascript:User.remove('").append(u.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-times\\\"></i> 删除</a>")
				.append("&nbsp;&nbsp;<a href=\\\"javascript:User.assign_click('").append(u.getId()).append("');\\\" class=\\\"btn btn-xs default btn-editable\\\"><i class=\\\"fa fa-key\\\"></i> 分配角色</a>")
				.append("\"");
		sb.append("]");
	}

    /**  END 以下为自己编写的代码区域 一般是多表之间的联合查询  END      **/
}
