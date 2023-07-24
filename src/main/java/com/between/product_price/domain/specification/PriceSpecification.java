package com.between.product_price.domain.specification;

import com.between.product_price.domain.model.Price;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
