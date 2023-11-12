package com.system.futurity.api.modules.users;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.futurity.enums.Command;
import com.system.futurity.messaging.FuturityMessage;
import com.system.futurity.messaging.RequestMessage;
import com.system.futurity.messaging.ResponseMessage;
import com.system.futurity.users.UserDTO;
import com.system.futurity.users.UserEntity;

@Service
public class UserService {

  private ObjectMapper mapper;
  private static Logger logger = LogManager.getLogger(UserService.class.toString());

  @Autowired
  private AmqpTemplate rabbitTemplate;

  @Value("${rabbitmq.exchange}")
  private String futurityExchange;

  @Value("${queues.user}")
  private String userQueue;

  public UserService() {
    this.mapper = new ObjectMapper();
  }

  public List<UserDTO> getAllUsers() {
    ResponseMessage<List<UserDTO>> response = rabbitTemplate.convertSendAndReceiveAsType(futurityExchange, userQueue,
        new ParameterizedTypeReference<ResponseMessage<List<UserDTO>>>() {
        });
    logger.info("Sending Message to the Queue ");
    if (response != null) {
      return response.getPayload();
    }
    return null;
  }

  public UserEntity create(UserDTO userDTO) {

    RequestMessage<UserDTO> message = RequestMessage.<UserDTO>builder().command(Command.CREATE).payload(userDTO)
        .build();
    ResponseMessage<UserEntity> response = rabbitTemplate.convertSendAndReceiveAsType(futurityExchange, userQueue,
        message, new ParameterizedTypeReference<ResponseMessage<UserEntity>>() {
        });
    logger.info("Sending Message to the Queue ");
    if (response != null) {
      return response.getPayload();
    }
    return null;
  }

  public UserEntity updateUser(UserDTO userDTO) {
    String payload;
    try {
      payload = mapper.writeValueAsString(userDTO);

      FuturityMessage message = FuturityMessage.builder().command(Command.UPDATE).payload(payload).build();
      FuturityMessage response = (FuturityMessage) rabbitTemplate.convertSendAndReceive(futurityExchange, userQueue,
          message);
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
