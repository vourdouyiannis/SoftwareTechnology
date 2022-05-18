package com.test.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.test.spring.boot.controller"})
@ComponentScan({"com.test.spring.boot.service"})
public class SecureCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureCourseApplication.class, args);
	}

}
