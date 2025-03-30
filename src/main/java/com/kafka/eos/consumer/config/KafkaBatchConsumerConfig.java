package com.kafka.eos.consumer.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaBatchConsumerConfig {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ConsumerFactory<String, String> getConfigProps() {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "effort-batch-group");
        configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, Boolean.FALSE);
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configProps.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1000);
        configProps.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");

        configProps.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG,
                org.apache.kafka.clients.consumer.CooperativeStickyAssignor.class.getName());

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> concurrentBatchListenerFactory(KafkaTemplate<String, String> kafkaTemplate) {

        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(getConfigProps());
        factory.setBatchListener(true);

        //let spring handle the offset commit
        //factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

        //single consumer instance will consume messages from 3 partitions
        factory.setConcurrency(3);

        //10 secs time for graceful shutdown consumer,
        //later force close may cause loss of messages
        factory.getContainerProperties().setShutdownTimeout(10000);

        factory.setCommonErrorHandler(errorHandler());

        return factory;
    }

    public DefaultErrorHandler errorHandler() {

        DeadLetterPublishingRecoverer recover = new DeadLetterPublishingRecoverer(
                kafkaTemplate,
                (record, Exception) -> new TopicPartition(record.topic() + ".batch.dlt", record.partition())
        );

        // Add custom headers to the DLT message
        recover.setHeadersFunction((consumerRecord, exception) -> {
            Headers headers = new RecordHeaders();
            headers.add("x-reason", exception.getClass().getSimpleName().getBytes(StandardCharsets.UTF_8));
            headers.add("x-error-message", exception.getMessage().getBytes(StandardCharsets.UTF_8));
            headers.add("x-original-topic", consumerRecord.topic().getBytes(StandardCharsets.UTF_8));
            headers.add("x-failed-timestamp", String.valueOf(System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8));
            return headers;
        });
        // No retry attempts and backoff
        DefaultErrorHandler handler = new DefaultErrorHandler(recover, new FixedBackOff(0L, 0));
        // ensures failed records are reprocessed
        handler.setAckAfterHandle(false);
        handler.setCommitRecovered(true);
        return handler;
    }

}


