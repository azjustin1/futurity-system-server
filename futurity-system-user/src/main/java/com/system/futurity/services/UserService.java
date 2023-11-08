package com.system.futurity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.system.futurity.dtos.UserDTO;
import com.system.futurity.users.UserEntity;
import com.system.futurity.users.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public List<UserEntity> getUsers() {
    return userRepository.findAll();
  }

  public UserEntity create(UserDTO userDTO) {
    UserEntity newUser = UserEntity.builder().userName(userDTO.getUserName()).fullName(userDTO.getFullName())
        .dateOfBirth(userDTO.getDateOfBirth()).phone(userDTO.getPhone()).build();
    return userRepository.save(newUser);
  }

  public UserEntity update(UserDTO userDTO) {
    UserEntity existedUser = userRepository.findById(userDTO.getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    existedUser.setUserName(userDTO.getUserName());
    existedUser.setFullName(userDTO.getFullName());
    existedUser.setDateOfBirth(userDTO.getDateOfBirth());
    existedUser.setPhone(userDTO.getPhone());
    return userRepository.save(existedUser);
  }

  public void delete(UserDTO userDTO) {
    UserEntity existedUser = userRepository.findById(userDTO.getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    userRepository.delete(existedUser);
  }
}
