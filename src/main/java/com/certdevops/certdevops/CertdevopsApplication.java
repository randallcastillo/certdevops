package com.certdevops.certdevops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication // provides a load of defaults
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class CertdevopsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CertdevopsApplication.class, args);
	}
}
