package com.between.product_price.domain.specification;

import com.between.product_price.domain.model.Price;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PriceSpecification implements Specification<Price> {

    private final LocalDateTime applicationDate;
    private final Long productId;
    private final Long brandId;

    public PriceSpecification(LocalDateTime applicationDate, Long productId, Long brandId) {
        this.applicationDate = applicationDate;
        this.productId = productId;
        this.brandId = brandId;
    }

    @Override
    public Predicate toPredicate(Root<Price> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Timestamp timestamp = Timestamp.valueOf(applicationDate);
        return criteriaBuilder.and(
                criteriaBuilder.lessThanOrEqualTo(root.get("startDate"), timestamp),
                criteriaBuilder.greaterThanOrEqualTo(root.get("endDate"), timestamp),
                criteriaBuilder.equal(root.get("productId"), productId),
                criteriaBuilder.equal(root.get("brand").get("id"), brandId)
        );
    }
}
