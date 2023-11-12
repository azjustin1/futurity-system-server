package com.system.futurity.products;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.system.futurity.messaging.RequestMessage;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UpdateProductMessage extends RequestMessage<ProductDTO> {
  private static final long serialVersionUID = 118273981273L;
  private Long productId;
}
