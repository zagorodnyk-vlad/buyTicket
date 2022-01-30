package com.mono.entity;

import java.util.Random;

public enum  PaymentStatus {
    NEW,
    FAILED,
    DONE;

    public static PaymentStatus generateRandomStatus() {
        PaymentStatus[] values = PaymentStatus.values();
        int length = values.length;
        int randIndex = new Random().nextInt(length);
        return values[randIndex];
    }
}
