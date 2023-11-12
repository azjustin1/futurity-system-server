package com.system.futurity.consumer;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.futurity.common.IFuturityConsumer;
import com.system.futurity.products.UpdateUserMessage;
import com.system.futurity.services.UserService;
import com.system.futurity.users.CreateUserMessage;
import com.system.futurity.users.DeleteUserMessage;
import com.system.futurity.users.ReadUserMessage;
import com.system.futurity.users.UserEntity;

@Component
@RabbitListener(queues = "${rabbitmq.queue}")
public class UserConsumer
    implements IFuturityConsumer<UserEntity, ReadUserMessage, CreateUserMessage, UpdateUserMessage, DeleteUserMessage> {

  @Autowired
  private UserService userService;

  @RabbitHandler
  public String test(String request) {
    return "Hello from User"; 
  }
  
  @RabbitHandler
  public List<UserEntity> read(ReadUserMessage request) {
    return userService.getAll();
  }

  @RabbitHandler
  public UserEntity create(CreateUserMessage message) {
    return userService.create(message.getPayload());
  }

  @RabbitHandler
  public UserEntity update(UpdateUserMessage request) {
    return userService.update(request.getUserId(), request.getPayload());
  }

  @RabbitHandler
  public Boolean delete(DeleteUserMessage request) {
    return userService.delete(request.getUserId());
  }
}
