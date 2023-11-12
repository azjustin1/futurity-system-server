package com.system.futurity.api.modules.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.futurity.users.UserDTO;
import com.system.futurity.users.UserEntity;

@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public List<UserEntity> getAllUsers() {
    return userService.getAll();
  }

  @PostMapping
  public UserEntity createUser(@RequestBody UserDTO userDTO) {
    return userService.create(userDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserEntity> editUser(@PathVariable("id") Long userId, @RequestBody() UserDTO userDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(userService.update(userId, userDTO));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<UserEntity> deleteUser(@PathVariable("id") Long userId, @RequestBody() UserDTO userDTO) {
    userService.delete(userId);
    return ResponseEntity.status(HttpStatus.OK).body(null);
  }
}
