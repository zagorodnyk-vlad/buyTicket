package com.mono.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Indexed
@Table(schema = "public")
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Field(name = "idTicket", store = Store.YES, index = Index.YES)
    private String ticketIdentifier;
    private String fio;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Flight flight;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public Ticket(String ticketIdentifier, String fio, Flight flight, PaymentStatus status) {
        this.ticketIdentifier = ticketIdentifier;
        this.fio = fio;
        this.flight = flight;
        this.status = status;
    }
}
