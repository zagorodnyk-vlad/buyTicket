package com.mono.exception;

public class PaymentExeption extends RuntimeException {
    public PaymentExeption() {
        super();
    }

    public PaymentExeption(String message) {
        super(message);
    }
}
