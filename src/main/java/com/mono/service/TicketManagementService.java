package com.mono.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mono.entity.Flight;
import com.mono.entity.PaymentStatus;
import com.mono.entity.Ticket;
import com.mono.exception.TicketExeption;
import com.mono.repository.FlightsRepository;
import com.mono.repository.FlightsRepositoryJpa;
import com.mono.repository.TicketRepository;
import com.mono.request.BuyTicketRequest;
import com.mono.response.FlightsResponse;
import com.mono.util.FlightsConverter;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketManagementService {
    @Autowired
    private FlightsRepository flightsRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private HttpService httpService;

    public List<FlightsResponse> flights() {
        List<Flight> flights = flightsRepository.searchAll();
        List<FlightsResponse> respons = new ArrayList<>();
        for (Flight one : flights) {
            respons.add(FlightsConverter.toDto(one));
        }
        return respons;
    }

    public String buyTicket(BuyTicketRequest buyTicketRequest) throws JsonProcessingException {
        Flight flight = (Flight) flightsRepository.getById(buyTicketRequest.getFlightId());
        String idTicket = RandomStringUtils.random(15);
        ///получили индификатор платежа
        String ticketIdentifier = httpService.buyTicket(buyTicketRequest);
        ///сохранили с статусом NEW
        ticketRepository.save(new Ticket(ticketIdentifier, buyTicketRequest.getFio(), flight, PaymentStatus.NEW));
        return idTicket;
    }

    public Ticket checkTicket(String ticket) {
        Ticket existTicket = ticketRepository.findByTicketIdentifier(ticket);
        if (existTicket == null) {
            throw new TicketExeption(String.format("Ticket with id =%s not found", ticket));
        }
        return existTicket;
    }
}
