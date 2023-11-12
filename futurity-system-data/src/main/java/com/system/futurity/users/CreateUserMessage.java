package com.system.futurity.users;

import com.system.futurity.messaging.RequestMessage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserMessage extends RequestMessage<UserDTO> {

  private static final long serialVersionUID = 1134534511233L;

}
