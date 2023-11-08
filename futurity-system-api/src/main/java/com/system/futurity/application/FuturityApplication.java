package com.system.futurity.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;

@SpringBootApplication(scanBasePackages = "com.system.futurity")
@EnableAutoConfiguration(exclude = { RabbitAutoConfiguration.class })
public class FuturityApplication {

  public static void main(String[] args) {
    SpringApplication.run(FuturityApplication.class, args);
  }

}
