package com.certdevops.certdevops;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.mockito.Mockito;

@Profile("test")
@Configuration
public class ProductServiceTestConfiguration {
   @Bean
   @Primary
   public ProductService productService() {
      return Mockito.mock(ProductService.class);
   }
}