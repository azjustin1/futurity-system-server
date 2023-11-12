package com.system.futurity.api.modules.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.futurity.products.ProductDTO;
import com.system.futurity.products.ProductEntity;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public List<ProductEntity> getProducts() {
    return productService.getAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductEntity> getProductById(@PathVariable("id") Long id) {
    return new ResponseEntity<>(null, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductDTO productDTO) {
    ProductEntity createdProduct = productService.create(productDTO);
    return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductEntity> updateProduct(@PathVariable("id") Long productId,
      @RequestBody ProductDTO productDTO) {
    ProductEntity updatedProduct = productService.update(productId, productDTO);
    return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") Long productId) {
    Boolean result = productService.delete(productId);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }
}
