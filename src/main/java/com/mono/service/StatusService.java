package com.mono.service;

import com.mono.entity.Flight;
import com.mono.entity.PaymentStatus;
import com.mono.entity.Ticket;
import com.mono.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private HttpService httpService;

    @Async
    @Scheduled(fixedDelayString = "10")
    public void paymentManagement() {
        List<Ticket> tickets = ticketRepository.findByStatus(PaymentStatus.NEW);
        for(Ticket ticket:tickets){
         String status =   httpService.getStatus(ticket.getTicketIdentifier());
         if(PaymentStatus.DONE.equals(PaymentStatus.valueOf(status))){
             ticket.setStatus(PaymentStatus.DONE);
             Flight flight = ticket.getFlight();
             flight.setAmountTicket(flight.getAmountTicket() - 1);
             ticketRepository.save(ticket);
         }
        }
    }
}
