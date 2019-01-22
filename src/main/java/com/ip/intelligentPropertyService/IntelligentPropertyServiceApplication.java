package com.ip.intelligentPropertyService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ip.IntelligentPropertyService.dao")
public class IntelligentPropertyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntelligentPropertyServiceApplication.class, args);
	}

}

