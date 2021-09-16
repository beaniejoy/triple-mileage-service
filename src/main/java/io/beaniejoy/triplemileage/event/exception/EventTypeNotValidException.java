package io.beaniejoy.triplemileage.event.exception;

public class EventTypeNotValidException extends RuntimeException{
    public EventTypeNotValidException(String eventType) {
        super("[" + eventType + "] 은 지원하지 않는 event 종류입니다.");
    }
}
