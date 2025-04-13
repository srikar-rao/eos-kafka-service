package com.kafka.eos.service;

import com.kafka.eos.avro.TransactionEvent;
import com.kafka.eos.util.AppUtil;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Timed
public class EventProcessService {

    @Timed(value = "process.kafka.event")
    public void processEvent(TransactionEvent event){

        if (AppUtil.isPrime(Integer.parseInt(event.get("eventId").toString()))) {
            log.error("Received prime number :: {}", Integer.parseInt(event.getEventId().toString()));
            throw new RuntimeException("Received prime number.");
        }
    }
}
