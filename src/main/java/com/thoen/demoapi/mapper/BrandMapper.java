package com.thoen.demoapi.mapper;

import com.thoen.demoapi.dto.BrandDTO;
import com.thoen.demoapi.models.Brand;
import org.mapstruct.Mapper;

@Mapper
public interface BrandMapper {

    Brand brandDTOToBrand(BrandDTO brandDTO);


}
