package com.scorewell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScorewellApplication {
	
	private static Logger logger = LoggerFactory.getLogger(ScorewellApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ScorewellApplication.class, args);
        
        logger.info("Scorewell Application started ...:)");
    }
}
