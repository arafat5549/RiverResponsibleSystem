package com.jqmkj.WaterResponseSystem.aop.ehcache;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
//标注启动了缓存
@EnableCaching
public class CacheConfiguration {
	 Logger logger = Logger.getLogger(CacheConfiguration.class);

 /*
  * ehcache 主要的管理器
  */
 @Bean(name = "appEhCacheCacheManager")
 public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
	 logger.info("EhCacheCacheManager 注入成功");
     return new EhCacheCacheManager (bean.getObject ());
 }

 /*
  * 据shared与否的设置,Spring分别通过CacheManager.creeate()或new CacheManager()方式来创建一个ehcache基地.
  */
 @Bean
 public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
     EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean ();
     cacheManagerFactoryBean.setConfigLocation (new ClassPathResource ("ehcache.xml"));
     cacheManagerFactoryBean.setShared (true);
     return cacheManagerFactoryBean;
 }
}
