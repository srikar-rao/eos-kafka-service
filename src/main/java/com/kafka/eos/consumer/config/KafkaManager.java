package com.kafka.eos.consumer.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaManager implements ApplicationListener<ApplicationReadyEvent> {

    private final KafkaListenerEndpointRegistry registry;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("STARTING KAFKA CONSUMER");
        registry.getListenerContainers().forEach(container -> {
            if (!container.isRunning()) {
                container.start();
            }
        });
    }
}
