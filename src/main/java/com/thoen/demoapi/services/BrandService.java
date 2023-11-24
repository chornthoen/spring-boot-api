package com.thoen.demoapi.services;

import com.thoen.demoapi.models.Brand;
import com.thoen.demoapi.repositories.BrandRepository;
import com.thoen.demoapi.spec.BrandFilter;
import com.thoen.demoapi.spec.BrandSpec;
import com.thoen.demoapi.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

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

    public List<Brand> getBrandByName(String name){
        return brandRepository.findByName(name);
    }
    public List<Brand> getFilter(Map<String, String> params){
        BrandFilter brandFilter = new BrandFilter();
        if (params.containsKey("name")){
            brandFilter.setName(params.get("name"));
        }
        if (params.containsKey("id")){
            brandFilter.setId(Integer.parseInt(params.get("id")));
        }
        BrandSpec brandSpec = new BrandSpec(brandFilter);
        return brandRepository.findAll(brandSpec);
    }

    public Page<Brand> getBrandByPage(Integer page, Integer size){
        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        if (page != null){
            pageNumber = page;
        }
        if (size != null){
            pageLimit = size;
        }
        Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);
        return brandRepository.findAll(pageable);
    }

}



