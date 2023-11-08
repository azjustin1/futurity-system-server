package com.system.futurity.api.modules.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.futurity.api.messaging.UserMessageProducer;

@RestController
@RequestMapping("/rest/users")
public class UserController {

  @Autowired
  private UserMessageProducer userMessageProducer;

  @GetMapping
  public void sendToUser() {
    userMessageProducer.send();;
  }
}
