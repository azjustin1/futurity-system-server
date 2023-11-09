package com.system.futurity.api.messaging;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.futurity.enums.Command;
import com.system.futurity.messaging.FuturityMessage;
import com.system.futurity.users.UserDTO;
import com.system.futurity.users.UserEntity;

@Service
public class UserMessageProducer {

  private ObjectMapper mapper;

  @Autowired
  private AmqpTemplate rabbitTemplate;
  private static Logger logger = LogManager.getLogger(UserMessageProducer.class.toString());

  @Value("${rabbitmq.exchange}")
  private String futurityExchange;

  public UserMessageProducer() {
    this.mapper = new ObjectMapper();
  }

  public List<UserEntity> getAllUsers() {
    FuturityMessage message = FuturityMessage.builder().command(Command.READ).build();
    FuturityMessage response = (FuturityMessage) rabbitTemplate.convertSendAndReceive(futurityExchange,
        "create-user-request", message);
    logger.info("Sending Message to the Queue ");
    if (response != null) {
      try {
        return mapper.readValue(response.getPayload(), new TypeReference<List<UserEntity>>() {
        });
      } catch (JsonMappingException e) {
        logger.error(e.getMessage());
      } catch (JsonProcessingException e) {
        logger.error(e.getMessage());
      }
    }
    return null;
  }

  public UserEntity createNewUser(UserDTO userDTO) {
    String payload;
    try {
      payload = mapper.writeValueAsString(userDTO);

      FuturityMessage message = FuturityMessage.builder().command(Command.CREATE).payload(payload).build();
      FuturityMessage response = (FuturityMessage) rabbitTemplate.convertSendAndReceive(futurityExchange,
          "create-user-request", message);
      logger.info("Sending Message to the Queue ");
      if (response != null) {
        UserEntity createdUser = mapper.readValue(response.getPayload(), UserEntity.class);
        return createdUser;
      }
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
    }
    return null;
  }

  public UserEntity updateUser(UserDTO userDTO) {
    String payload;
    try {
      payload = mapper.writeValueAsString(userDTO);

      FuturityMessage message = FuturityMessage.builder().command(Command.UPDATE).payload(payload).build();
      FuturityMessage response = (FuturityMessage) rabbitTemplate.convertSendAndReceive(futurityExchange,
          "create-user-request", message);
      logger.info("Sending Message to the Queue ");
      if (response != null) {
        UserEntity updatedUser = mapper.readValue(response.getPayload(), UserEntity.class);
        return updatedUser;
      }
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
    }
    return null;
  }
}
