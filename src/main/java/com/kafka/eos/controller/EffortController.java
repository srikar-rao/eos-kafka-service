package com.kafka.eos.controller;

import com.kafka.eos.avro.TransactionEvent;
import com.kafka.eos.producer.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EffortController {

    private final ProducerService producerService;

    @PostMapping("/send")
    public void triggerSend(@RequestParam(required = false, defaultValue = "0") Integer min, @RequestParam(required = false, defaultValue = "10") Integer max) {
        IntStream.range(min, max)
                .mapToObj(value -> new TransactionEvent(
                        Integer.valueOf(value).toString(),
                        Instant.now().toString(),
                        UUID.randomUUID().toString(),
                        new Random().nextDouble(),
                        "PENDING"
                ))
                .peek(event -> log.info("Sending event :: {}", event))
                .forEach(producerService::sendEffortMessage);
    }
}
