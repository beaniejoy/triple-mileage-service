package io.beaniejoy.triplemileage.point.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PointRelatedExceptionHandler {

    @ExceptionHandler({
            PointTotalNotFoundException.class,
            AlreadyReviewAddedException.class,
            PlaceReviewCountNotFoundException.class,
            ReviewPointNotExistedException.class
    })
    public ResponseEntity<String> handlePointRelatedException(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
