package com.system.futurity.api.messaging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMessageProducer {
  @Autowired
  private AmqpTemplate rabbitTemplate;
  @Autowired
  private Queue queue;
  private static Logger logger = LogManager.getLogger(UserMessageProducer.class.toString());

  public void send() {
    rabbitTemplate.convertAndSend(queue.getName(), "Hello from API");
    logger.info("Sending Message to the Queue ");
  }
}
