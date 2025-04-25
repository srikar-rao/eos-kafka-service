package com.kafka.eos.producer.config;

import com.kafka.eos.avro.TransactionEvent;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    // Transactional Producer Factory
    @Bean
    public ProducerFactory<String, TransactionEvent> txProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        configProps.put(ProducerConfig.ACKS_CONFIG, "all");
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        configProps.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);
        configProps.put(ProducerConfig.RETRIES_CONFIG, Integer.MAX_VALUE);
        configProps.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "effort-tx-id");

        configProps.put("schema.registry.url", "http://localhost:8081");
        configProps.put("auto.register.schemas", true);
        configProps.put("use.latest.version", true);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public ProducerFactory<String, TransactionEvent> txDltProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        configProps.put(ProducerConfig.ACKS_CONFIG, "all");
//        configProps.put(ProducerConfig.RETRIES_CONFIG, Integer.MAX_VALUE);

        configProps.put("schema.registry.url", "http://localhost:8081");
        configProps.put("auto.register.schemas", true);
        configProps.put("use.latest.version", true);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    //Non-Transactional Producer Factory
    @Bean
    public ProducerFactory<String, String> nonTxProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.ACKS_CONFIG, "all");
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, false); // explicitly false
        // No TRANSACTIONAL_ID_CONFIG

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    // KafkaTemplate with Transactions
    @Bean
    public KafkaTemplate<String, TransactionEvent> kafkaTxTemplate() {
        KafkaTemplate<String, TransactionEvent> txTemplate = new KafkaTemplate<>(txProducerFactory());
        txTemplate.setObservationEnabled(Boolean.TRUE);
        return txTemplate;
    }

    //KafkaTemplate without Transactions
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(nonTxProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, TransactionEvent> kafkaDLTTemplate() {
        return new KafkaTemplate<>(txDltProducerFactory());
    }

    //Transaction Manager for Kafka
    @Bean
    public KafkaTransactionManager<String, TransactionEvent> kafkaTxManager() {
        return new KafkaTransactionManager<>(txProducerFactory());
    }
}