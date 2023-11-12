package com.system.futurity.products;

import com.system.futurity.messaging.RequestMessage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteProductMessage extends RequestMessage<ProductDTO> {

  private static final long serialVersionUID = 195834985734987L;
  private Long productId;

}
