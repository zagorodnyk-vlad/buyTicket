package com.mono.entity;

import lombok.Data;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Data
@Indexed
@Table(schema = "public")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //откуда рейс
    @Field(name = "whence", store = Store.YES, index = Index.YES)
    private String whence;
    //куда рейс
    private String whereTo;
    private Date date;
    private BigDecimal price;
    private Integer amountTicket;
}
