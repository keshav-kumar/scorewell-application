package com.scorewell.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.scorewell.db.MongoDBManager;

//@Configuration
//@ComponentScan(basePackages = "com.scorewell")
//@PropertySource({ "classpath:app.properties" })
//@EnableWebMvc
public class ScoreWellMvcConfig{// extends WebMvcConfigurerAdapter{

//	@Autowired
//    private Environment env;
//	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/pdf/**").addResourceLocations("/WEB-INF/pdf/");
//
//		registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
//		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/js/");
//		registry.addResourceHandler("/img/**").addResourceLocations("/WEB-INF/img/");
//		registry.addResourceHandler("/gif/**").addResourceLocations("/WEB-INF/gif/");
//		registry.addResourceHandler("/styles/**").addResourceLocations("/WEB-INF/styles/");
//		registry.addResourceHandler("/plugins/**").addResourceLocations("/WEB-INF/plugins/");
//		registry.addResourceHandler("/icon/**").addResourceLocations("/WEB-INF/icon/");
//	}
//	
//	
//	@Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/jsp/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }
//	
//	@Bean(name = "mongoDBManager")
//    public MongoDBManager mongoBatchDBManager() {
//		return new MongoDBManager(env.getProperty("mongodb.host"), env.getProperty("mongodb.db"));
////        return new MongoDBManager("localhost", "jobmatcher");
//    }
	
//	@Bean(name = "userService")
//    public UserService serService() {
//		return new UserService();
//    }
	
}
