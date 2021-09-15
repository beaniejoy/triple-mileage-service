package io.beaniejoy.triplemileage.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class Producer {
    public static final String TOPIC = "mytopic";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publishTopic(String message) {
        log.info("kafka message: " + message);
        kafkaTemplate.send(TOPIC, message);
    }
}
