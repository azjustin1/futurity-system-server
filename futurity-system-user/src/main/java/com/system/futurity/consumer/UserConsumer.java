package com.system.futurity.consumer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "rabbitmq.queue.user", id = "consumer")
public class UserConsumer {
  private static Logger logger = LogManager.getLogger(UserConsumer.class.toString());

  @RabbitHandler
  public void receiver(String hello) {
    logger
        .info("MenuOrder listener invoked - Consuming Message with MenuOrder Identifier : " + hello);
  }
}
