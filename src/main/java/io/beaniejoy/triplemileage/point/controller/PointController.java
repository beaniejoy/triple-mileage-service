package io.beaniejoy.triplemileage.point.controller;

import io.beaniejoy.triplemileage.point.dto.PointTotalResponseDto;
import io.beaniejoy.triplemileage.point.service.PointTotalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PointController {

    private final PointTotalService pointTotalService;

    @GetMapping("/api/point/{userId}")
    public ResponseEntity<PointTotalResponseDto> showTotalPoint(@PathVariable("userId") UUID userId) {

        return ResponseEntity.ok(pointTotalService.findTotalPoint(userId));
    }
}
