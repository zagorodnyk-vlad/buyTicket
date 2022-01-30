package com.mono.util;

import com.mono.entity.Flight;
import com.mono.response.FlightsResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FlightsConverter {
    public FlightsResponse toDto(Flight u) {
        return FlightsResponse.builder()
                .amountTicket(u.getAmountTicket())
                .date(u.getDate())
                .price(u.getPrice())
                .where(u.getWhence())
                .whereTo(u.getWhereTo())
                .build();
    }
}
