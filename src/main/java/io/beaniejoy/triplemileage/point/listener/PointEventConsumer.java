package io.beaniejoy.triplemileage.point.listener;

import io.beaniejoy.triplemileage.event.message.PointEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PointEventConsumer {

    @KafkaListener(topics = "point_history", groupId = "point")
    public void consumerFromTopic(PointEvent pointEvent) {
        log.info("consumer message: " + pointEvent.toString());
    }
}
