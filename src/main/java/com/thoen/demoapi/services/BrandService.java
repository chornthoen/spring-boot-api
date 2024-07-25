package com.thoen.demoapi.services;

import com.thoen.demoapi.models.Brand;
import com.thoen.demoapi.repositories.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    //get all brands
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    public Optional<Brand> getBrandById(Integer id){
        return brandRepository.findById(id);
    }

    public Brand createBrand(Brand brand){
        return  brandRepository.save(brand);
    }

    public Brand updateBrand(Integer id, Brand brandUpdate){
        return  brandRepository.findById(id).map(brand -> {
            brand.setName(brandUpdate.getName());
            brand.setModel(brandUpdate.getModel());
            return  brandRepository.save(brand);
        }).orElse(null);
    }

    public void deleteBrand(Integer id){
        brandRepository.findById(id).ifPresent(brandRepository::delete);
    }

}
