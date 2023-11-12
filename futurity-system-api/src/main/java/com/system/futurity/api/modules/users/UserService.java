package com.system.futurity.api.modules.users;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.system.futurity.products.UpdateUserMessage;
import com.system.futurity.users.CreateUserMessage;
import com.system.futurity.users.DeleteUserMessage;
import com.system.futurity.users.ReadUserMessage;
import com.system.futurity.users.UserDTO;
import com.system.futurity.users.UserEntity;

@Service
public class UserService {

  private static Logger logger = LogManager.getLogger(UserService.class.toString());

  @Autowired
  private AmqpTemplate rabbitTemplate;

  @Value("${rabbitmq.exchange}")
  private String futurityExchange;

  @Value("${queues.user}")
  private String userQueue;

  public List<UserEntity> getAll() {
    ReadUserMessage message = new ReadUserMessage();
    List<UserEntity> response = rabbitTemplate.convertSendAndReceiveAsType(futurityExchange, userQueue, message,
        new ParameterizedTypeReference<List<UserEntity>>() {
        });
    logger.info("Sending Message to the Queue ");
    if (response != null) {
      return response;
    }
    return null;
  }

  public UserEntity create(UserDTO userDTO) {
    CreateUserMessage message = new CreateUserMessage();
    message.setPayload(userDTO);
    UserEntity response = rabbitTemplate.convertSendAndReceiveAsType(futurityExchange, userQueue, message,
        new ParameterizedTypeReference<UserEntity>() {
        });
    logger.info("Sending Message to the Queue ");
    if (response != null) {
      return response;
    }
    return null;
  }

  public UserEntity update(Long userId, UserDTO userDTO) {
    UpdateUserMessage message = new UpdateUserMessage();
    message.setUserId(userId);
    message.setPayload(userDTO);
    UserEntity response = rabbitTemplate.convertSendAndReceiveAsType(futurityExchange, userQueue, message,
        new ParameterizedTypeReference<UserEntity>() {
        });

    if (response != null) {
      return response;
    }
    return null;
  }

  public Boolean delete(Long userId) {
    DeleteUserMessage message = new DeleteUserMessage();
    message.setUserId(userId);

    Boolean response = rabbitTemplate.convertSendAndReceiveAsType(futurityExchange, userQueue, message,
        new ParameterizedTypeReference<Boolean>() {
        });

    if (response != null) {
      return response;
    }
    return null;
  }
}
