package com.system.futurity.services;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.system.futurity.users.UserDTO;
import com.system.futurity.users.UserEntity;
import com.system.futurity.users.UserRepository;

@Service
public class UserService {
  private Logger logger = LogManager.getLogger(UserService.class);

  @Autowired
  private UserRepository userRepository;

  public List<UserEntity> getAll() {
    return userRepository.findAll();
  }

  public UserEntity create(UserDTO userDTO) {
    Date dateOfBirth = Date.from(Instant.ofEpochMilli(userDTO.getDateOfBirth()));
    UserEntity newUser = UserEntity.builder().userName(userDTO.getUserName()).fullName(userDTO.getFullName())
        .dateOfBirth(dateOfBirth).phone(userDTO.getPhone()).build();
    return userRepository.save(newUser);
  }

  public UserEntity update(Long userId, UserDTO userDTO) {
    Date dateOfBirth = Date.from(Instant.ofEpochMilli(userDTO.getDateOfBirth()));
    UserEntity existedUser = userRepository.findById(userId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    existedUser.setUserName(userDTO.getUserName());
    existedUser.setFullName(userDTO.getFullName());
    existedUser.setDateOfBirth(dateOfBirth);
    existedUser.setPhone(userDTO.getPhone());
    return userRepository.save(existedUser);
  }

  public Boolean delete(Long userId) {
    UserEntity existedUser = userRepository.findById(userId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    try {
      userRepository.delete(existedUser);
      return true;
    } catch (Exception e) {
      logger.error(e.getMessage());
      return false;
    }
  }
}
