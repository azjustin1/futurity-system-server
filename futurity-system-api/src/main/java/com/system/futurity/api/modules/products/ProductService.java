package com.system.futurity.api.modules.products;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.futurity.api.modules.users.UserService;
import com.system.futurity.enums.Command;
import com.system.futurity.messaging.FuturityMessage;
import com.system.futurity.messaging.RequestMessage;
import com.system.futurity.products.DeleteProductMessage;
import com.system.futurity.products.ProductDTO;
import com.system.futurity.products.ProductEntity;
import com.system.futurity.products.ReadProductMessage;
import com.system.futurity.products.UpdateProductMessage;

@Service
public class ProductService {
  private ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private AmqpTemplate rabbitTemplate;
  private static Logger logger = LogManager.getLogger(UserService.class.toString());

  @Value("${rabbitmq.exchange}")
  private String futurityExchange;

  @Value("${queues.product}")
  private String productQueue;

  public List<ProductEntity> getAll() {
    ReadProductMessage message = new ReadProductMessage();
    List<ProductEntity> res = rabbitTemplate.convertSendAndReceiveAsType(futurityExchange, productQueue, message,
        new ParameterizedTypeReference<List<ProductEntity>>() {
        });
    if (res != null) {
      return res;
    }
    return null;
  }

  public ProductEntity create(ProductDTO productDTO) {
    RequestMessage<ProductDTO> message = new RequestMessage<>();
    message.setPayload(productDTO);
    ProductEntity response = rabbitTemplate.convertSendAndReceiveAsType(futurityExchange, productQueue, productDTO,
        new ParameterizedTypeReference<ProductEntity>() {
        });

    if (response != null) {
      return response;
    }
    return null;
  }

  public ProductEntity update(Long productId, ProductDTO productDTO) {
    UpdateProductMessage message = new UpdateProductMessage();
    message.setProductId(productId);
    message.setPayload(productDTO);
    ProductEntity response = rabbitTemplate.convertSendAndReceiveAsType(futurityExchange, productQueue, message,
        new ParameterizedTypeReference<ProductEntity>() {
        });

    if (response != null) {
      return response;
    }
    return null;
  }

  public Boolean delete(Long productId) {
    DeleteProductMessage message = new DeleteProductMessage();
    message.setProductId(productId);

    Boolean response = rabbitTemplate.convertSendAndReceiveAsType(futurityExchange, productQueue, message,
        new ParameterizedTypeReference<Boolean>() {
        });

    if (response != null) {
      return response;
    }
    return null;
  }
}
