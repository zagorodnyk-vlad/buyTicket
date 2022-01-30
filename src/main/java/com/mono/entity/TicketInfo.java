package com.mono.entity;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class TicketInfo {
    private Long id;
    private Ticket ticket;
    private Flight flight;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}
