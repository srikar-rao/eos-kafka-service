package com.kafka.eos.consumer.config;

import com.kafka.eos.avro.TransactionEvent;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

//@ConditionalOnProperty(name = "kafka.enabled", havingValue = "true", matchIfMissing = false)
@Configuration
public class KafkaConsumerConfig {

    public ConsumerFactory<String, TransactionEvent> getConfigProps() {

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "effort-consumer-group");
        configProps.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");

        configProps.put("schema.registry.url", "http://localhost:8081");
        configProps.put("auto.register.schemas", true);
        configProps.put("use.latest.version", true);
//        configProps.put("value.deserializer.specific.avro.reader", true);
        configProps.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, Boolean.TRUE);

        // 1. POLLING CONTROL
        configProps.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1000");
        // Default: 500
        // Maximum records returned per poll() call
        // Tradeoff: Higher values increase throughput but require longer processing windows

        // 2. MEMORY PROTECTION
        configProps.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, "4194304"); // 4MB
        // Default: 52428800 (50MB)
        // Maximum total data bytes fetched per request across all partitions
        // Critical for preventing OOM with large messages

//        configProps.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "1048576"); // 1MB
        // Default: 1048576 (1MB)
        // Maximum data bytes fetched per individual partition
        // Protects against partition skew (one partition with huge messages)

        // 3. LATENCY CONTROL
        configProps.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, "1");
        // Default: 1
        // Minimum bytes server should accumulate before responding
        // Set to 1 for lowest possible latency

//        configProps.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, "100");
        // Default: 500
        // Maximum time (ms) to block waiting for fetch.min.bytes
        // Lower values reduce latency but may increase empty polls

        // 4. RELIABILITY SETTINGS
        configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        // Default: true
        // Disables automatic offset commits
        // Required for exactly-once processing semantics

        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // Default: latest
        // Throws exception if no offset exists (no automatic reset)
        // Prevents accidental message skipping/duplication

        // 5. PERFORMANCE TUNING
//        configProps.put(ConsumerConfig.SEND_BUFFER_CONFIG, "131072"); // 128KB
        // Default: 131072 (128KB)
        // SO_SNDBUF size for network requests

//        configProps.put(ConsumerConfig.RECEIVE_BUFFER_CONFIG, "65536"); // 64KB
        // Default: 65536 (64KB)
        // SO_RCVBUF size for network requests

        // 6. TIMEOUT CONFIGURATIONS
//        configProps.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "300000"); // 5min
        // Default: 300000 (5 minutes)
        // Maximum delay between poll() calls before leaving group
        // Must exceed (max.poll.records × processing time)

//        configProps.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, "305000"); // 305s
        // Default: 30000 (30s)
        // Maximum time for broker to respond to fetch requests
        // Should exceed max.poll.interval.ms

        // ========================================================================
        // 7. REBALANCE PROTOCOL
        // ========================================================================
        configProps.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG,
                "org.apache.kafka.clients.consumer.CooperativeStickyAssignor");
        // Default: RangeAssignor (legacy)
        // Modern assignment strategy that minimizes stop-the-world pauses
        // Requires broker version 2.4+


        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TransactionEvent> concurrentListenerFactory(KafkaTemplate<String, String> kafkaTemplate) {

        ConcurrentKafkaListenerContainerFactory<String, TransactionEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(getConfigProps());

        factory.setBatchListener(false);

        factory.setConcurrency(3);

        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);

        factory.getContainerProperties().setShutdownTimeout(5000);

        // prevent auto start, need to manual start of consuming when application full started.
        factory.setAutoStartup(false);

        return factory;

    }

}