package com.kafka.eos.service;

import com.kafka.eos.avro.TransactionEvent;
import com.kafka.eos.util.AppUtil;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Metrics;
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

        int delayMs = Character.getNumericValue(event.getEventId().charAt(0)) * 1000;
        try {
            Thread.sleep(delayMs * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (AppUtil.isPrime(Integer.parseInt(event.get("eventId").toString()))) {
            log.error("Received prime number :: {}", Integer.parseInt(event.getEventId().toString()));
            throw new RuntimeException("Received prime number.");
        }
    }
}
