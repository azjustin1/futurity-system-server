package com.system.futurity.data.modules.users;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class UserDTO {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("userName")
  private String userName;

  @JsonProperty("fullName")
  private String fullName;

  @JsonProperty("dateOfBirth")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate dateOfBirth;

  @JsonProperty("phone")
  private String phone;

  public UserDTO() {
  }

  public UserDTO(String userName, String fullName, LocalDate dateOfBirth) {
    this.userName = userName;
    this.fullName = fullName;
    this.dateOfBirth = dateOfBirth;
  }
}
