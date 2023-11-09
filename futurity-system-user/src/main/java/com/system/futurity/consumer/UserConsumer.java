package com.system.futurity.consumer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.futurity.messaging.FuturityMessage;
import com.system.futurity.users.UserDTO;
import com.system.futurity.users.UserEntity;
import com.system.futurity.users.UserRepository;

@Component

public class UserConsumer {
  private static Logger logger = LogManager.getLogger(UserConsumer.class.toString());

  @Autowired
  private UserRepository userRepository;

  private ObjectMapper mapper;

  public UserConsumer() {
    this.mapper = new ObjectMapper();
  }

  @RabbitListener(queues = "${rabbitmq.queue}")
  public FuturityMessage createUserConsumer(FuturityMessage message)
      throws JsonMappingException, JsonProcessingException {
    FuturityMessage response = new FuturityMessage();

    switch (message.getCommand()) {
    case CREATE:
      break;
    case READ:
      response = read();
      break;
    case UPDATE:
      response = update(message.getPayload());
      break;
    case DELETE:
      response = delete(message.getPayload());
      break;
    default:
      response = null;
    }
    return response;
  }

  private FuturityMessage read() throws JsonProcessingException {
    List<UserDTO> users = new ArrayList<>();
    for (UserEntity user : userRepository.findAll()) {
      UserDTO userDTO = UserDTO.builder().fullName(user.getFullName()).dateOfBirth(user.getDateOfBirth())
          .phone(user.getPhone()).build();
      users.add(userDTO);
    }
    FuturityMessage responseMessage = new FuturityMessage();
    responseMessage.setPayload(mapper.writeValueAsString(users));
    return responseMessage;
  }

  private FuturityMessage update(String payload) throws JsonMappingException, JsonProcessingException {
    UserDTO userDTO = mapper.readValue(payload, UserDTO.class);

    UserEntity existedUser = userRepository.findById(userDTO.getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    existedUser.setUserName(userDTO.getUserName());
    existedUser.setFullName(userDTO.getFullName());
    existedUser.setDateOfBirth(userDTO.getDateOfBirth());
    existedUser.setPhone(userDTO.getPhone());
    FuturityMessage responseMessage = FuturityMessage.builder().payload(mapper.writeValueAsString(existedUser)).build();
    return responseMessage;
  }

  private FuturityMessage delete(String payload) throws JsonMappingException, JsonProcessingException {
    UserDTO userDTO = mapper.readValue(payload, UserDTO.class);
    UserEntity existedUser = userRepository.findById(userDTO.getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    userRepository.delete(existedUser);
    FuturityMessage responseMessage = new FuturityMessage();
    return responseMessage;
  }
}
