package com.between.product_price.infrastructure.web.mapper;

import com.between.product_price.domain.model.Brand;
import com.between.product_price.infrastructure.web.dto.BrandDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BrandMapper {

    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    BrandDTO mapToBrandDTO(Brand brand);
}
