package com.jqmkj.WaterResponseSystem.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Component
 public class WebConfigurer extends WebMvcConfigurerAdapter {

    @Value("${file.localPath}")
    private  String localPath; //= "D:\\photo";

    @Value("${file.loadPath}")
    private  String loadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(loadPath).addResourceLocations("file:///"+localPath);
    }
}


