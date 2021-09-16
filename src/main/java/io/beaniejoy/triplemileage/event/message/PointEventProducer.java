package io.beaniejoy.triplemileage.event.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class PointEventProducer {
    public static final String TOPIC = "point_history";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(PointEvent pointEvent) {
        log.info("kafka message: " + pointEvent);
        kafkaTemplate.send(TOPIC, pointEvent);
    }
}
