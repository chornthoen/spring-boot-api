package com.thoen.demoapi.utils;

import com.thoen.demoapi.dto.BrandDTO;
import com.thoen.demoapi.models.Brand;

public class Mapper {

    public static Brand brandDTOToBrand(BrandDTO brandDTO) {
        Brand brand = new Brand();
        brand.setName(brandDTO.getName());
        brand.setModel(brandDTO.getModel());
        return brand;
    }

    public static BrandDTO brandToBrandDTO(Brand brand) {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setName(brand.getName());
        brandDTO.setModel(brand.getModel());
        return brandDTO;
    }
}
