package com.brighttalk.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = { "com.brighttalk.employee"})
@EntityScan("com.brighttalk.employee")
@EnableJpaRepositories(basePackages = "com.brighttalk.employee")
@EnableSwagger2
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.out.println("new  @@@@@@@@12@");
		SpringApplication.run(Application.class, args);
	}

}
