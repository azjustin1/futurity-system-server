package com.system.futurity.users;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UserDTO implements Serializable {
  private static final long serialVersionUID = 112398129387L;

  @JsonProperty("id")
  private Long id;

  @JsonProperty("userName")
  private String userName;

  @JsonProperty("fullName")
  private String fullName;

  @JsonProperty("dateOfBirth")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private Instant dateOfBirth;

  @JsonProperty("phone")
  private String phone;

  public UserDTO() {
  }

  public UserDTO(String userName, String fullName, Instant dateOfBirth) {
    this.userName = userName;
    this.fullName = fullName;
    this.dateOfBirth = dateOfBirth;
  }
}
