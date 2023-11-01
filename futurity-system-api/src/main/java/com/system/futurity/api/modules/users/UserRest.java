package com.system.futurity.api.modules.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.futurity.data.modules.users.UserDTO;
import com.system.futurity.data.modules.users.UserEntity;

@RestController
@RequestMapping("rest/users")
public class UserRest {

  @Autowired
  private UserService userService;

  @GetMapping
  public List<UserEntity> getUser() {
    return userService.getUsers();
  }

  @PostMapping
  public ResponseEntity<UserEntity> createNewUser(@RequestBody() UserDTO userDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.create(userDTO));
  }

  @PutMapping
  public ResponseEntity<UserEntity> editUser(@RequestBody() UserDTO userDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.update(userDTO));
  }

  @DeleteMapping
  public ResponseEntity<UserEntity> deleteUser(@RequestBody() UserDTO userDTO) {
    userService.delete(userDTO);
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
