package com.doan.cnpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class CnpmApplication {

	public static void main(String[] args) {
		SpringApplication.run(CnpmApplication.class, args);
	}

}
