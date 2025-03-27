package com.kafka.eos.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProducerService {

    private final KafkaTemplate<String, String> kafkaTxTemplate;

    public void sendEffortMessage(String message) {
        log.info("Sending message");
        kafkaTxTemplate.executeInTransaction(tx -> tx.send("dct.effort", message, message));
    }

}