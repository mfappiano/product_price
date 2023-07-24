package com.between.product_price.infrastructure.web.mapper;

import com.between.product_price.domain.model.Price;
import com.between.product_price.infrastructure.web.dto.BrandDTO;
import com.between.product_price.infrastructure.web.dto.PriceDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PriceCustomMapper {

    public PriceDTO mapBtoA(Price price) {
        Optional<PriceDTO> element = Optional
                .ofNullable(price)
                .map(domainObj -> PriceDTO.builder()
                        .id(domainObj.getId())
                        .priority(domainObj.getPriority())
                        .productId(domainObj.getProductId())
                        .priceList(domainObj.getPriceList())
                        .endDate(domainObj.getEndDate())
                        .startDate(domainObj.getStartDate())
                        .price(domainObj.getPrice())
                        .currency(domainObj.getCurrency())
                        .brandDTO(BrandDTO.builder()
                                .brandName(domainObj.getBrand().getBrandName())
                                .id(domainObj.getBrand().getId())
                                .build())
                        .build());
        return element.orElseGet(() -> PriceDTO.builder().build());
    }
}
