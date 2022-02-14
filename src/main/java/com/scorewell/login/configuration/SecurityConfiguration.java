package com.scorewell.login.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.scorewell.db.MongoDBManager;

@Configuration
public class SecurityConfiguration implements WebMvcConfigurer {

	@Autowired
    private Environment env;
	
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "mongoDBManager")
    public MongoDBManager mongoBatchDBManager() {
		return new MongoDBManager(env.getProperty("mongodb.host"), env.getProperty("mongodb.db"));
    }
}