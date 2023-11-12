package com.system.futurity.api.modules.products;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.system.futurity.api.modules.users.UserService;
import com.system.futurity.products.CreateProductMessage;
import com.system.futurity.products.DeleteProductMessage;
import com.system.futurity.products.ProductDTO;
import com.system.futurity.products.ProductEntity;
import com.system.futurity.products.ReadProductMessage;
import com.system.futurity.products.UpdateProductMessage;

@Service
public class ProductService {
  private static Logger logger = LogManager.getLogger(UserService.class);

  @Autowired
  private AmqpTemplate rabbitTemplate;

  @Value("${rabbitmq.exchange}")
  private String futurityExchange;

  @Value("${queues.product}")
  private String productQueue;

  public List<ProductEntity> getAll() {
    ReadProductMessage message = new ReadProductMessage();
    List<ProductEntity> res = rabbitTemplate.convertSendAndReceiveAsType(futurityExchange, productQueue, message,
        new ParameterizedTypeReference<List<ProductEntity>>() {
        });
    logger.info("Sending Message to the Queue ");
    if (res != null) {
      return res;
    }
    return null;
  }

  public ProductEntity create(ProductDTO productDTO) {
    CreateProductMessage message = new CreateProductMessage();
    message.setPayload(productDTO);
    ProductEntity response = rabbitTemplate.convertSendAndReceiveAsType(futurityExchange, productQueue, message,
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
