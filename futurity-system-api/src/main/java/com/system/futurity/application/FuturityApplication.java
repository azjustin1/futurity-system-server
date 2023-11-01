package com.system.futurity.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "com.system.futurity.data.modules" })
@ComponentScan(basePackages = { "com.system.futurity.api.modules" })
@EntityScan(basePackages = { "com.system.futurity.data.modules" })
public class FuturityApplication {

  public static void main(String[] args) {
    SpringApplication.run(FuturityApplication.class, args);
  }

}
