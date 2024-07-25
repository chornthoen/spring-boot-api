package com.thoen.demoapi.repositories;

import com.thoen.demoapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}