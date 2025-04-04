package com.ust.normalspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NormalspringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(NormalspringbootApplication.class, args);
	}

}
//netstat -ano | findstr ":8081" to kill the port
//taskkill /PID 1736 /F
