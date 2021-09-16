package io.beaniejoy.triplemileage.event.exception;

public class ActionNotValidException extends RuntimeException{
    public ActionNotValidException(String action) {
        super("[" + action + "] 은 지원하지 않는 action 입니다.");
    }
}
