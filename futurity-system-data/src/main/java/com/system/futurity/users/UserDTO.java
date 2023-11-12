package com.system.futurity.users;

import java.io.Serializable;

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
@NoArgsConstructor
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
  private Long dateOfBirth;

  @JsonProperty("phone")
  private String phone;
}