package com.mono.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mono.entity.Ticket;
import com.mono.request.BuyTicketRequest;
import com.mono.response.FlightsResponse;
import com.mono.service.TicketManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class TicketManagementController {
    @Autowired
    private TicketManagementService ticketManagementService;

    @GetMapping(value = "/getFlights")
    public List<FlightsResponse> flights() {
        return ticketManagementService.flights();
    }

    @PostMapping(value = "/buyTicket")
    public String buyTicket(@RequestBody BuyTicketRequest buyTicketRequest) throws JsonProcessingException {
       return ticketManagementService.buyTicket(buyTicketRequest);
    }

    @GetMapping(value = "/checkTicket")
    public Ticket checkTicket(@RequestParam String ticket) {
        return ticketManagementService.checkTicket(ticket);
    }
}
