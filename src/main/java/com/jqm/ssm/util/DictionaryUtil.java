/**
 * Copyright &copy; 2012-2014 <a href="http://www.iwantclick.com">iWantClick</a>iwc.shop All rights reserved.
 */
package com.jqm.ssm.util;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jqm.ssm.cache.RedisCache;
import com.jqm.ssm.entity.Dictionary;
import com.jqm.ssm.service.impl.DictionaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 字典工具类
 * 使用redis缓存
 * @author Tony Wong
 * @version 2013-5-29
 */
@Component
public class DictionaryUtil {


	@Autowired
	private DictionaryServiceImpl dictionaryService;
	@Autowired
	private RedisCache redisCache;

	public static final String CACHE_DICT_MAP = "dictionaryCacheMap";
	public static final String CACHE_DICT = "dictionaryCache";

//	public int getDictCode(DictionayEnum e){
//		Dictionary dict = getDict(e.getGroup(),code);
//		return  dict!=null ? dict.getId() : -1;
//	}

	public int getDictId(String group,int code){
		Dictionary dict = getDict(group,code);
		return  dict!=null ? dict.getId() : -1;
	}

	public String getDictName(String group,int code){
		Dictionary dict = getDict(group,code);
		return  dict!=null ? dict.getName() : "";
	}
	public Dictionary getDict(String group,int code){
		if (!Strings.isNullOrEmpty(group) && code>=0){
			for (Dictionary dict : getDictList(group)){
				if (group.equals(dict.getGroup()) && code ==dict.getCode()){
					return dict;
				}
			}
		}
		return null;
	}
	public int getDictIdByLogType() {
		List<Dictionary> dictList =dictionaryService.selectListByMap(null,CACHE_DICT);
		return -1;
	}
	//
	public List<String> getDictGroups(){
		List<Dictionary> dictList =dictionaryService.selectListByMap(null,CACHE_DICT);
		List<String> groups = Lists.newArrayList();
		for (Dictionary dict : dictList){
			groups.add(dict.getGroup());
		}

		return groups;
	}

	/**
	 * 读取所有字典 按group缓存
	 * @param group
	 * @return
	 */
	public  List<Dictionary> getDictList(String group){
		Map<String, List<Dictionary>> dictMap = (Map<String, List<Dictionary>>) redisCache.getCache(CACHE_DICT_MAP,Dictionary.class);
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			for (Dictionary dict : dictionaryService.selectListByMap(null,CACHE_DICT)){
				List<Dictionary> dictList = dictMap.get(dict.getGroup());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getGroup(), Lists.newArrayList(dict));
				}
			}
			redisCache.putCache(CACHE_DICT_MAP, dictMap);
		}
		List<Dictionary> dictList = dictMap.get(group);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}
	
}
