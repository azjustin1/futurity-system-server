package com.system.futurity.products;

import com.system.futurity.messaging.RequestMessage;
import com.system.futurity.users.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserMessage extends RequestMessage<UserDTO>{
  
  private static final long serialVersionUID = 1298349238472L;
  private Long userId;
}
