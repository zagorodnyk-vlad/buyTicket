package com.mono.controller;

import com.mono.entity.PaymentStatus;
import com.mono.exception.PaymentExeption;
import com.mono.request.PaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@RestController
@Slf4j
public class HttpController {
    private String signature = "12345";

    /*
    Имитируем запрос и колбек от банка
    На вход ФИО сумма. на выходе идентификатор платежа
    */
    @PostMapping("/paymentid")
    public String getPaymentId(@RequestBody PaymentRequest request, @RequestHeader String sign) {
        if (sign == null || !signature.equals(sign)) {
                  throw new PaymentExeption("signature not valid");
        }
        String idTicket = RandomStringUtils.randomNumeric(15);
        long milliSeconds = Timestamp.valueOf(LocalDateTime.now()).getTime();

        return idTicket + milliSeconds;
    }

    @GetMapping("/randomstatus")
    public String getRandomStatus(@RequestParam String ticket) {
        return PaymentStatus.generateRandomStatus().toString();
    }
}
