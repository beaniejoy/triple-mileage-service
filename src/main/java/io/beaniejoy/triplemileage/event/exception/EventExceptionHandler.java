package io.beaniejoy.triplemileage.event.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EventExceptionHandler {

    @ExceptionHandler({EventTypeNotValidException.class, ActionNotValidException.class})
    public ResponseEntity<String> handleEventRequestNotValid(RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
