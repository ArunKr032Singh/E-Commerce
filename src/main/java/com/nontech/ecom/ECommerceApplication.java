package com.nontech.ecom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ECommerceApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ECommerceApplication.class, args);
//		System.out.println("Hello World");
	}

}
