package com.thoen.demoapi.controllers;

import com.thoen.demoapi.models.Brand;
import com.thoen.demoapi.services.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping
    public List<Brand> getAllBrand(){
        return brandService.getAllBrands();
    }


    @PostMapping
    public Brand createBrand(@RequestBody Brand brand){
        return  brandService.createBrand(brand);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Integer id){
        return brandService.getBrandById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Integer id,@RequestBody Brand brandUpdate){
        Brand updateBrand = brandService.updateBrand(id,brandUpdate);
        if(updateBrand != null){
            return  ResponseEntity.ok(updateBrand);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Void> deleteBrand(@PathVariable Integer id){
        brandService.deleteBrand(id);
        return ResponseEntity.ok().build();
    }


}
