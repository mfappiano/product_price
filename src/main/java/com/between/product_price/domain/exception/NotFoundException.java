package com.between.product_price.domain.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String msn) {
        super(msn);
    }

    public NotFoundException(String msn, Throwable reason) {
        super(msn, reason);
    }
}
