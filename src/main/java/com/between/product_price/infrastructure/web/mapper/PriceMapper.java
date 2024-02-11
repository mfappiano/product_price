package com.between.product_price.infrastructure.web.mapper;

import com.between.product_price.domain.model.Price;
import com.between.product_price.infrastructure.web.dto.PriceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    @Mapping(source = "brand", target = "brandDTO")
    PriceDTO mapToPriceDTO(Price price);
}
