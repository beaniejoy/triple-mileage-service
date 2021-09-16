package io.beaniejoy.triplemileage.point.listener;

import io.beaniejoy.triplemileage.event.exception.ActionNotValidException;
import io.beaniejoy.triplemileage.event.message.PointEvent;
import io.beaniejoy.triplemileage.event.type.ActionType;
import io.beaniejoy.triplemileage.point.service.PointCalculateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PointEventConsumer {

    private final PointCalculateService pointCalculateService;

    @KafkaListener(topics = "point_history", groupId = "point")
    public void consumerFromTopic(PointEvent pointEvent) {
        log.info("consumer message: " + pointEvent.toString());
        ActionType action = pointEvent.getActionType();

        switch (action) {
            case ADD:
                pointCalculateService.addReviewPoint(pointEvent);
                break;
            case MOD:
                pointCalculateService.modifyReviewPoint(pointEvent);
                break;
            case DELETE:
                pointCalculateService.deleteReviewPoint(pointEvent);
                break;
            default:
                throw new ActionNotValidException(String.valueOf(action));
        }
    }
}
