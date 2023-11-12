package com.system.futurity.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.futurity.enums.Command;
import com.system.futurity.messaging.FuturityMessage;
import com.system.futurity.products.ProductDTO;
import com.system.futurity.products.ProductEntity;
import com.system.futurity.users.UserDTO;

public class ProductService {
  private ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private AmqpTemplate rabbitTemplate;
  private static Logger logger = LogManager.getLogger(ProductService.class.toString());

  @Value("${rabbitmq.exchange}")
  private String futurityExchange;

  @Value("${queues.product}")
  private String productQueue;

  public List<ProductEntity> getAll() {
    FuturityMessage message = FuturityMessage.builder().command(Command.READ).build();
    FuturityMessage response = (FuturityMessage) rabbitTemplate.convertSendAndReceive(futurityExchange, productQueue,
        message);
    logger.info("Sending Message to the Queue ");
    if (response != null) {
      try {
        return mapper.readValue(response.getPayload(), new TypeReference<List<ProductEntity>>() {
        });
      } catch (JsonMappingException e) {
        logger.error(e.getMessage());
      } catch (JsonProcessingException e) {
        logger.error(e.getMessage());
      }
    }
    return null;
  }

  public ProductEntity getProductById(String id) {
    return null;
  }

  public ProductEntity create(ProductEntity productDTO) {
    String payload;
    try {
      payload = mapper.writeValueAsString(productDTO);

      FuturityMessage message = FuturityMessage.builder().command(Command.CREATE).payload(payload).build();
      FuturityMessage response = (FuturityMessage) rabbitTemplate.convertSendAndReceive(futurityExchange,
          "create-user-request", message);
      logger.info("Sending Message to the Queue ");
      if (response != null) {
        ProductEntity createdProduct = mapper.readValue(response.getPayload(), ProductEntity.class);
        return createdProduct;
      }
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
    }
    return null;
  }

  public ProductEntity update(ProductDTO productDTO) {
    String payload;
    try {
      payload = mapper.writeValueAsString(productDTO);

      FuturityMessage message = FuturityMessage.builder().command(Command.UPDATE).payload(payload).build();
      FuturityMessage response = (FuturityMessage) rabbitTemplate.convertSendAndReceive(futurityExchange, productQueue,
          message);
      logger.info("Sending Message to the Queue ");
      if (response != null) {
        ProductEntity updatedProduct = mapper.readValue(response.getPayload(), ProductEntity.class);
        return updatedProduct;
      }
    } catch (JsonProcessingException e) {
      logger.error(e.getMessage());
    }
    return null;
  }
}
