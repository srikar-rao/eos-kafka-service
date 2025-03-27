package com.kafka.eos.controller;

import com.kafka.eos.producer.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor
public class EffortController {

    private final ProducerService producerService;

    @PostMapping("/send")
    public void triggerSend(@RequestParam(required = false, defaultValue = "0") Integer min, @RequestParam(required = false, defaultValue = "10") Integer max) {
        IntStream.range(min, max)
                .forEach(value -> producerService.sendEffortMessage(String.valueOf(value)));
    }
}
