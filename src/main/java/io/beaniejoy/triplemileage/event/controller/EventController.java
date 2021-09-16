package io.beaniejoy.triplemileage.event.controller;

import io.beaniejoy.triplemileage.event.dto.EventRequestDto;
import io.beaniejoy.triplemileage.event.service.EventPublishService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventPublishService eventPublishService;

    @PostMapping("/events")
    public ResponseEntity<String> handleEvent(@RequestBody EventRequestDto resource) {

        eventPublishService.publishPointEvent(resource);

        return ResponseEntity.ok(resource.getType() + "에 대한 " + resource.getAction() + " action 처리가 완료되었습니다.");
    }
}
