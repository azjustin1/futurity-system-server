package com.system.futurity.consumer;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.futurity.common.IFuturityConsumer;
import com.system.futurity.products.CreateProductMessage;
import com.system.futurity.products.DeleteProductMessage;
import com.system.futurity.products.ProductEntity;
import com.system.futurity.products.ReadProductMessage;
import com.system.futurity.products.UpdateProductMessage;
import com.system.futurity.services.ProductService;

@Component
@RabbitListener(queues = "${rabbitmq.queue}")
public class ProductConsumer implements
    IFuturityConsumer<ProductEntity, ReadProductMessage, CreateProductMessage, UpdateProductMessage, DeleteProductMessage> {

  @Autowired
  private ProductService productService;

  @RabbitHandler
  public List<ProductEntity> read(ReadProductMessage request) {
    return productService.getAll();
  }

  @RabbitHandler
  public ProductEntity create(CreateProductMessage request) {
    return productService.create(request.getPayload());
  }

  @RabbitHandler
  public ProductEntity update(UpdateProductMessage request) {
    return productService.update(request.getProductId(), request.getPayload());
  }

  @RabbitHandler
  public Boolean delete(DeleteProductMessage request) {
    return productService.delete(request.getProductId());
  }
}
