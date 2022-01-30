package com.mono.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Date;

@Data
@SuperBuilder
public class FlightsResponse {
    private String where;
    //куда рейс
    private String whereTo;
    private Date date;
    private BigDecimal price;
    private Integer amountTicket;
}
