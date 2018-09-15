package com.jqm.ssm.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigUtil {
	
	//private @Value("${cas.server.url}")String casServerUrl;
	//private @Value("${cas.service.url}")String casServiceUrl;
	//public String getCasServerUrl() {
	//	return casServerUrl;
	//}

	//public String getCasServiceUrl() {
	//	return casServiceUrl;
	//}



	private @Value("${web.basepath}")String basePath;
	private @Value("${inc.basepath}")String incBasePath;
	private @Value("${proj.type}") String projectType;
	private @Value("${redis.on}") Boolean reidsOn;


	public String getBasePath() {
		return basePath;
	}
	
	public String getIncBasePath() {
		return incBasePath;
	}

	public String getProjectType() {
		return projectType;
	}

	public Boolean isRedisOn() {
		return reidsOn;
	}
}
