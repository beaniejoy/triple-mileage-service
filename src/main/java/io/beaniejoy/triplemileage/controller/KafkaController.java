package io.beaniejoy.triplemileage.controller;

import io.beaniejoy.triplemileage.message.Producer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KafkaController {

    private final Producer producer;

    @PostMapping("/kafkaapp/post")
    public void sendMessage(@RequestParam("msg") String message) {
        producer.publishTopic(message);
    }
}
