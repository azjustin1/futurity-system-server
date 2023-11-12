package com.system.futurity.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.system.futurity.products.ProductDTO;
import com.system.futurity.products.ProductEntity;
import com.system.futurity.products.ProductRepository;

@Service
public class ProductService {
  private static Logger logger = LogManager.getLogger(ProductService.class.toString());

  @Autowired
  private ProductRepository productRepository;

  public List<ProductEntity> getAll() {
    return productRepository.findAll();
  }

  public ProductEntity create(ProductDTO productDTO) {
    ProductEntity newProduct = ProductEntity.builder().name(productDTO.getName())
        .description(productDTO.getDescription()).price(productDTO.getPrice()).quantity(productDTO.getQuantity())
        .build();
    return productRepository.save(newProduct);
  }

  public ProductEntity getById(Long id) {
    ProductEntity existedProduct = productRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    return existedProduct;
  }

  public ProductEntity update(Long productId, ProductDTO productDTO) {
    ProductEntity existedProduct = productRepository.findById(productId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    existedProduct.setName(productDTO.getName());
    existedProduct.setDescription(productDTO.getDescription());
    existedProduct.setPrice(productDTO.getPrice());
    existedProduct.setQuantity(productDTO.getQuantity());
    return productRepository.save(existedProduct);
  }

  public boolean delete(Long productId, ProductDTO productDTO) {
    ProductEntity existedProduct = productRepository.findById(productId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    try {
      productRepository.delete(existedProduct);
      return true;
    } catch (Exception e) {
      logger.error(e.getMessage());
      return false;
    }
  }
}
