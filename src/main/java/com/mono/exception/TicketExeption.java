package com.mono.exception;

public class TicketExeption extends RuntimeException {
    public TicketExeption() {
        super();
    }

    public TicketExeption(String message) {
        super(message);
    }
}
