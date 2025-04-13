package com.kafka.eos.consumer;

import com.kafka.eos.avro.TransactionEvent;
import com.kafka.eos.service.EventProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class ConcurrentConsumerListener {

    private final EventProcessService eventProcessService;

    @RetryableTopic(
            attempts = "2",
//            backoff = @Backoff(delay = 1000, multiplier = 2.0, maxDelay = 10000),
            backoff = @Backoff(delay = 500),
            dltTopicSuffix = ".dlt",
            retryTopicSuffix = ".retry",
            autoCreateTopics = "true",
            kafkaTemplate = "kafkaDLTTemplate"
    )
    @KafkaListener(
            topics = "tx.event",
            groupId = "tx-event-consumer-group",
            containerFactory = "concurrentListenerFactory"
    )
    public void consumeSingleRecord(TransactionEvent message,
                                    Acknowledgment acknowledgment) {

//        if (AppUtil.isPrime(Integer.parseInt(message.get("eventId").toString()))) {
//            log.error("Received prime number :: {}", Integer.parseInt(message.getEventId().toString()));
//            throw new RuntimeException("Received prime number.");
//        }

        eventProcessService.processEvent(message);

        acknowledgment.acknowledge();
    }

//    @DltHandler
//    public void handleDlt(ConsumerRecord<String,String> message) {
//        System.out.println("Received from DLT: " + message);
//    }

}