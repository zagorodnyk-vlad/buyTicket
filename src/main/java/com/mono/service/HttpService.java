package com.mono.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mono.entity.PaymentStatus;
import com.mono.exception.PaymentExeption;
import com.mono.request.BuyTicketRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class HttpService {

    private ObjectMapper objectMapper = new ObjectMapper();

    public String buyTicket(BuyTicketRequest buyTicketRequest) throws JsonProcessingException {
        String body = objectMapper.writeValueAsString(buyTicketRequest);
        try {
            HttpResponse resp = Request.Post("http://localhost:8080/paymentid")
                    .addHeader("sign", "12345")
                    .bodyString(body, ContentType.APPLICATION_JSON)
                    .execute()
                    .returnResponse();
            if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                log.error(EntityUtils.toString(resp.getEntity(), "UTF-8"));
                throw new PaymentExeption("Error payment");
            }
            return EntityUtils.toString(resp.getEntity(), "UTF-8");
        } catch (Exception e) {
            throw new PaymentExeption("HttpPayment send error");
        }
    }

    public String getStatus(String ticket) {
        try {
            HttpResponse resp = Request.Get("http://localhost:8080/randomstatus?ticket="+ticket)
                    .execute()
                    .returnResponse();
            //разбираем ответ
            if (resp.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new PaymentExeption("Error get status");
            }
            return EntityUtils.toString(resp.getEntity(), "UTF-8");
           } catch (Exception e) {
            log.info("HttpPayment send error", e);
            throw new PaymentExeption("HttpPayment send error");
        }
    }
}


