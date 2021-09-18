package io.beaniejoy.triplemileage.point.listener;

import io.beaniejoy.triplemileage.event.exception.ActionNotValidException;
import io.beaniejoy.triplemileage.event.message.PointEvent;
import io.beaniejoy.triplemileage.event.type.ActionType;
import io.beaniejoy.triplemileage.point.service.PointCalculateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class PointEventConsumer {

    private final PointCalculateService pointCalculateService;

    @KafkaListener(topics = "point_history", groupId = "point")
    public void consumerFromTopic(PointEvent pointEvent) {
        log.info("Point calculate Review Action: " + pointEvent.getActionType());
        ActionType action = pointEvent.getActionType();

        UUID userId = UUID.fromString(pointEvent.getUserId());
        UUID placeId = UUID.fromString(pointEvent.getPlaceId());

        switch (action) {
            case ADD:
                pointCalculateService.addReviewPoint(userId, placeId, pointEvent);
                break;
            case MOD:
                pointCalculateService.modifyReviewPoint(userId, placeId, pointEvent);
                break;
            case DELETE:
                pointCalculateService.deleteReviewPoint(userId, placeId, pointEvent);
                break;
            default:
                throw new ActionNotValidException(String.valueOf(action));
        }
    }
}
