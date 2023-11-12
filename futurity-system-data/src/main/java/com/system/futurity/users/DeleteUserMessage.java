package com.system.futurity.users;

import com.system.futurity.messaging.RequestMessage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteUserMessage extends RequestMessage<UserDTO> {

  private static final long serialVersionUID = 1934857123987L;
  private Long userId;

}
