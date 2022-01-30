package com.mono.repository;

import com.mono.entity.PaymentStatus;
import com.mono.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByStatus(PaymentStatus status);
    Ticket findByTicketIdentifier(String ticket);
}
