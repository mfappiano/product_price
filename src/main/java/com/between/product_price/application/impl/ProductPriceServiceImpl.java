package com.between.product_price.application.impl;

import com.between.product_price.application.ProductPriceService;
import com.between.product_price.domain.exception.NotFoundException;
import com.between.product_price.domain.model.Price;
import com.between.product_price.domain.repository.PriceRepository;
import com.between.product_price.domain.specification.PriceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public ProductPriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price fetchPriceDetails(LocalDateTime applicationDate, Long productId, Long brandId) {
        PriceSpecification spec = new PriceSpecification(applicationDate, productId, brandId);
        List<Price> prices = priceRepository.findAll(spec);

        Optional<Price> selectedPrice = prices.stream().max(Comparator.comparingInt(Price::getPriority));

        return selectedPrice.orElseThrow(() ->
                new NotFoundException("Price not found for the provided criteria."));
    }
}
