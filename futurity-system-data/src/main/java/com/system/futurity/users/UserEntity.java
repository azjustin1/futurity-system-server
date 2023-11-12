package com.system.futurity.users;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UserEntity implements Serializable {
  private static final long serialVersionUID = 11237129837213L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  private String fullName;

  @Column
  private String userName;

  @Column
  private Date dateOfBirth;

  @Column
  private String phone;

  @Column
  @JsonIgnore
  private String password;
}
