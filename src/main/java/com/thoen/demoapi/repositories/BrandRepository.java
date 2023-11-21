package com.thoen.demoapi.repositories;


import com.thoen.demoapi.models.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

}