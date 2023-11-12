package com.system.futurity.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { RabbitAutoConfiguration.class })
@ComponentScan(basePackages = "com.system.futurity")
@EnableJpaRepositories(basePackages = { "com.system.futurity" })
@EntityScan(basePackages = { "com.system.futurity" })
public class FuturitySystemUserApplication {

  public static void main(String[] args) {
    SpringApplication.run(FuturitySystemUserApplication.class, args);
  }

}
