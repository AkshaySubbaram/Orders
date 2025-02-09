package com.order.thread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.order.thread")
public class ThreadApplication {
	public static void main(String[] args) {
		SpringApplication.run(ThreadApplication.class, args);
	}

}
