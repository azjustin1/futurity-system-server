package com.system.futurity.producer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.system.futurity.users.UserDTO;

@Service
public class UserProducer {
  private static Logger logger = LogManager.getLogger(UserProducer.class);

  private AmqpTemplate rabbitTemplate;

}
