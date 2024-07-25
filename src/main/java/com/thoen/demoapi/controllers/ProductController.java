package com.thoen.demoapi.controllers;

import com.thoen.demoapi.exception.ApiException;
import com.thoen.demoapi.models.Product;
import com.thoen.demoapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    private final Map<String, Product> productMap = new HashMap<>();


    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProducts() {
        List<Product> products = productService.getProductAll();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Products retrieved successfully");
        response.put("status", HttpStatus.OK.value());
        response.put("data", products);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Product retrieved successfully");
        response.put("status", HttpStatus.OK.value());
        response.put("data", product);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody Product product) {
        productService.createProduct(product);
        productMap.put(String.valueOf(product.getId()), product);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Product created successfully");
        response.put("status", HttpStatus.OK.value());
        response.put("data", product);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
 
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<Object, Object>> deleteProduct(@PathVariable Long id) {
        if (productService.getProductById(id).isPresent()) {
            productService.deleteProduct(id);
            HashMap<Object, Object> response = new HashMap<>();
            response.put("message", "Brand deleted successfully");
            response.put("status", Optional.of(HttpStatus.OK.value()));
            return ResponseEntity.ok(response);
        } else {
            throw new ApiException(HttpStatus.NOT_FOUND, "Brand not found");
        }
    }
}