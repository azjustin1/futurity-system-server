package com.system.futurity.api.modules.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.futurity.api.messaging.UserMessageProducer;
import com.system.futurity.users.UserDTO;
import com.system.futurity.users.UserEntity;

@RestController
@RequestMapping("/rest/users")
public class UserController {

  @Autowired
  private UserMessageProducer userMessageProducer;

  @GetMapping
  public List<UserEntity> getAllUsers() {
    return userMessageProducer.getAllUsers();
  }

  @PostMapping
  public UserEntity sendToUser(@RequestBody UserDTO userDTO) {
    return userMessageProducer.createNewUser(userDTO);
  }

  @PutMapping
  public ResponseEntity<UserEntity> editUser(@RequestBody() UserDTO userDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(userMessageProducer.updateUser(userDTO));
  }

//  @DeleteMapping
//  public ResponseEntity<UserEntity> deleteUser(@RequestBody() UserDTO userDTO) {
//    userService.delete(userDTO);
//    return ResponseEntity.status(HttpStatus.OK).body(null);
//  }
}
