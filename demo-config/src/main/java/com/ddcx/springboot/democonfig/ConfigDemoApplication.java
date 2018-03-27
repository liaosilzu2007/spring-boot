package com.ddcx.springboot.democonfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ConfigDemoApplication {

	public static void main(String[] args) {
		//读取默认配置文件的启动方式
		SpringApplication.run(ConfigDemoApplication.class, args);

		//读取自定义配置文件名的启动方式
		new SpringApplicationBuilder(ConfigDemoApplication.class)
				.properties("spring.config.location=classpath:/springboot-config.properties")
				.run(args);
	}
}
