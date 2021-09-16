package io.beaniejoy.triplemileage.event.service;

import io.beaniejoy.triplemileage.event.dto.EventRequestDto;
import io.beaniejoy.triplemileage.event.exception.ActionNotValidException;
import io.beaniejoy.triplemileage.event.exception.EventTypeNotValidException;
import io.beaniejoy.triplemileage.event.message.PointEventProducer;
import io.beaniejoy.triplemileage.event.type.ActionType;
import io.beaniejoy.triplemileage.event.type.EventType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventPublishService {

    private final PointEventProducer pointEventProducer;

    public void publishPointEvent(EventRequestDto resource) {

        isValidEventType(resource.getType(), resource.getAction());

        pointEventProducer.send(resource.toPointEvent());
    }

    /**
     * Event type, action 유효성 검토
     * @param type 이벤트 종류 (REVIEW)
     * @param action 이벤트 작업 종류 (ADD, MOD, DELETE)
     */
    private void isValidEventType(String type, String action) {
        Optional.ofNullable(EnumUtils.getEnum(EventType.class, type))
                .orElseThrow(() -> new EventTypeNotValidException(type));

        Optional.ofNullable(EnumUtils.getEnum(ActionType.class, action))
                .orElseThrow(() -> new ActionNotValidException(action));
    }
}
