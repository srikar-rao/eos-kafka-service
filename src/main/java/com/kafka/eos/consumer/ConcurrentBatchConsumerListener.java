package com.kafka.eos.consumer;

import com.kafka.eos.util.AppUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.BatchListenerFailedException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class ConcurrentBatchConsumerListener {

    private final KafkaTemplate<String, String> kafkaTemplate;

    //Spring handling for each failed message
//    @KafkaListener(topics = "dct.effort", groupId = "effort-batch-group", containerFactory = "concurrentBatchListenerFactory")
//    public void consume(List<ConsumerRecord<String, String>> messages) {
//
//        for (ConsumerRecord<String, String> message : messages) {
//            if (AppUtil.isPrime(Integer.parseInt(message.value()))) {
//                throw new RuntimeException("Prime number!");
//            }
//        }
//    }


    //Manual Error Accumulation in Batch Processing
    @KafkaListener(topics = "dct.effort", groupId = "effort-batch-group", containerFactory = "concurrentBatchListenerFactory")
    public void consume(List<ConsumerRecord<String, String>> messages) {

        List<ConsumerRecord<String, String>> failedRecords = new ArrayList<>();
        List<Exception> failures = new ArrayList<>();

        for (ConsumerRecord<String, String> message : messages) {
            try {
                if (AppUtil.isPrime(Integer.parseInt(message.value()))) {
                    throw new RuntimeException("Prime number in record: " + message.value());
                }
                // Process successful records here
            } catch (RuntimeException e) {
                failedRecords.add(message);
                failures.add(e);
                log.error("Failed to process record {}: {}", message.value(), e.getMessage());
            }
        }

        if (!failures.isEmpty()) {
            // Throw for the first failure but track all failed records
            throw new BatchListenerFailedException(
                    "Failed " + failedRecords.size() + " records in batch",
                    failures.get(0),
                    failedRecords.get(0)  // The first failed record
            );
        }
    }

//    @KafkaListener(topics = "dct.effort.dlt", groupId = "effort-batch-group-dlt")
//    public void consumeDltManuallySent(ConsumerRecord<String, String> record) {
//        System.out.println("Manually sent to DLT: " + record.value());
//    }


}