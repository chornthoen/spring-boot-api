package com.thoen.demoapi.controllers;

import com.thoen.demoapi.dto.BrandDTO;
import com.thoen.demoapi.exception.ApiException;
import com.thoen.demoapi.models.Brand;
import com.thoen.demoapi.services.BrandService;
import com.thoen.demoapi.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;
    

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllBrand() {
        try {
            List<Brand> brands = brandService.getAllBrands();
            Map<String, Object> response = new HashMap<>();
            if (brands.isEmpty()) {
                response.put("brands", brands);
                response.put("message", "No brands found");
                response.put("status", Optional.of(HttpStatus.OK.value()));
                return ResponseEntity.ok(response);
            } else {
                response.put("brands", brands);
                response.put("message", "Brands retrieved successfully");
                response.put("status", Optional.of(HttpStatus.OK.value()));
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Brands not found");
        }
    }

    @PostMapping
    public ResponseEntity<?> createBrand(@RequestBody BrandDTO brandDTO) {
        Brand brand = Mapper.brandDTOToBrand(brandDTO);
        Brand newBrand = brandService.createBrand(brand);
        BrandDTO brandDTOResponse = Mapper.brandToBrandDTO(newBrand);
        return ResponseEntity.ok(brandDTOResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getBrandById(@PathVariable Integer id) {
        return brandService.getBrandById(id)
            .map(brand -> {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "successfully");
                response.put("status", Optional.of(HttpStatus.OK.value()));
                response.put("brand", brand);
                return ResponseEntity.ok(response);
            })
            .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Brand not found"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Integer id, @RequestBody Brand brandUpdate) {
        Brand updateBrand = brandService.updateBrand(id, brandUpdate);
        if (updateBrand != null) {
            return ResponseEntity.ok(updateBrand);
        } else {
            throw new ApiException(HttpStatus.NOT_FOUND, "Brand not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteBrand(@PathVariable Integer id) {
        if (brandService.getBrandById(id).isPresent()) {
            brandService.deleteBrand(id);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Brand deleted successfully");
            response.put("status", Optional.of(HttpStatus.OK.value()));
            return ResponseEntity.ok(response);
        } else {
            throw new ApiException(HttpStatus.NOT_FOUND, "Brand not found");
        }
    }
}
