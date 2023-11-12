package com.system.futurity.messaging;

import java.io.Serializable;

import com.system.futurity.enums.Command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestMessage<T> implements Serializable {
  private static final long serialVersionUID = 12348273982323432L;
  private Command command;
  private String filter;
  private T payload;
}
