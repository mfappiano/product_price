package com.between.product_price.infrastructure.web;

import com.between.product_price.application.ProductPriceService;
import com.between.product_price.domain.exception.NotFoundException;
import com.between.product_price.infrastructure.web.dto.PriceDTO;
import com.between.product_price.infrastructure.web.mapper.PriceCustomMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductPriceService productPriceService;
    private final PriceCustomMapper mapper;

    @GetMapping("/prices")
    public ResponseEntity<?> fetchPriceInfo(@RequestParam("applicationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
                                            @RequestParam("productId") Long productId,
                                            @RequestParam("brandId") Long brandId) {
        PriceDTO priceDTO;
        try {
            priceDTO = mapper.mapBtoA(productPriceService.fetchPriceDetails(applicationDate, productId, brandId));
        } catch (NotFoundException e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", errorMessage));
        }

        return ResponseEntity.ok(priceDTO);
    }
}
