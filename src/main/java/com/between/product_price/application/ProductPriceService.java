package com.between.product_price.application;

import com.between.product_price.domain.model.Price;

import java.time.LocalDateTime;

public interface ProductPriceService {

    Price fetchPriceDetails(LocalDateTime applicationDate, Long productId, Long brandId);
}
