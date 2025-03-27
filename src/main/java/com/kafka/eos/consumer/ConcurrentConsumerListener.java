package com.kafka.eos.consumer;

import com.kafka.eos.util.AppUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@RequiredArgsConstructor
public class ConcurrentConsumerListener {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @RetryableTopic(
            attempts = "2",
//            backoff = @Backoff(delay = 1000, multiplier = 2.0, maxDelay = 10000),
            backoff = @Backoff(delay = 500),
            dltTopicSuffix = ".dlt",
            retryTopicSuffix = ".retry",
            autoCreateTopics = "true",
            kafkaTemplate = "kafkaTemplate"
    )
    @KafkaListener(topics = "dct.effort", groupId = "effort-consumer-group", containerFactory = "concurrentListenerFactory")
    public void consumeSingleRecord(ConsumerRecord<String, String> message, Acknowledgment acknowledgment) {
        if (AppUtil.isPrime(Integer.parseInt(message.value()))) {
            throw new RuntimeException("Received prime number.");
        }
        acknowledgment.acknowledge();
    }

//    @DltHandler
//    public void handleDlt(ConsumerRecord<String,String> message) {
//        System.out.println("Received from DLT: " + message);
//    }

}