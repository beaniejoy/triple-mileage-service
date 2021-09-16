package io.beaniejoy.triplemileage.point;

import io.beaniejoy.triplemileage.point.domain.PointHistory;
import io.beaniejoy.triplemileage.point.domain.PointHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PointController {

    private final PointHistoryRepository pointHistoryRepository;

    @GetMapping("/history")
    public ResponseEntity<List<PointHistory>> list() {
        return ResponseEntity.ok(pointHistoryRepository.findAll());
    }
}
