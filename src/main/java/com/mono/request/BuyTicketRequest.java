package com.mono.request;

import lombok.Data;

@Data
public class BuyTicketRequest {
    private String fio;
    private Long flightId;
}
