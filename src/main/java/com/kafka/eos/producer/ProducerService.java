package com.kafka.eos.producer;

import com.kafka.eos.avro.TransactionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProducerService {

    private final KafkaTemplate<String, TransactionEvent> kafkaTxTemplate;

    public void sendEffortMessage(TransactionEvent message) {
        kafkaTxTemplate.executeInTransaction(tx -> tx.send(
                "tx.event",
                message.getEventId().toString(),
                message));
    }

}