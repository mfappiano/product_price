package com.between.product_price.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceDTO {

    private Long id;

    @JsonProperty(value = "brand")
    private BrandDTO brandDTO;

    @JsonProperty("start_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Timestamp startDate;

    @JsonProperty("end_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Timestamp endDate;

    @JsonProperty(value = "price_list")
    private String priceList;

    @JsonProperty(value = "product_id")
    private Long productId;

    @JsonProperty(value = "priority")
    private Integer priority;

    @JsonProperty(value = "price")
    private BigDecimal price;

    @JsonProperty(value = "curr")
    private String currency;

}
