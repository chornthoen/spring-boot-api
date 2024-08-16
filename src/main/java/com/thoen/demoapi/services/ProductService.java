package com.thoen.demoapi.services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.thoen.demoapi.models.Product;
import com.thoen.demoapi.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //get all product
    // public List<Product> getProductAll() {
    //     return productRepository.findAll();
    // }
        public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    //get by id
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    //create product
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    //update product
    public Product updateProduct(Long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setPrice(product.getPrice());
            return productRepository.save(existingProduct);
        } else {
            throw new EntityNotFoundException("Product not found with id " + id);
        }
    }

    //delete product
    public void deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
        } else {
            throw new EntityNotFoundException("Product not found with id " + id);
        }
    }


}