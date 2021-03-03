package com.certdevops.certdevops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication // provides a load of defaults
// @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,
// XADataSourceAutoConfiguration.class})
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class CertdevopsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CertdevopsApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
