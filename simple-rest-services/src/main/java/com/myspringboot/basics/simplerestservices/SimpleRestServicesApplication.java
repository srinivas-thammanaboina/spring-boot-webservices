package com.myspringboot.basics.simplerestservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SimpleRestServicesApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SimpleRestServicesApplication.class, args);
		
		for (String name : applicationContext.getBeanDefinitionNames()) {
			System.out.println(name);
		}
		
	}
}
