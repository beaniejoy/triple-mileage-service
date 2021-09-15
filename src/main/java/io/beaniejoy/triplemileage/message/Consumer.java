package io.beaniejoy.triplemileage.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Consumer {

    @KafkaListener(topics = {"mytopic"}, groupId = "mytopic")
    public void consumerFromTopic(String message) {
        log.info("consumer message: " + message);
    }
}
