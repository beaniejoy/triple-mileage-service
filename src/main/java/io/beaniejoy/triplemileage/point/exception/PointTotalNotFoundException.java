package io.beaniejoy.triplemileage.point.exception;

import java.util.UUID;

public class PointTotalNotFoundException extends RuntimeException{
    public PointTotalNotFoundException(UUID userId) {
        super("사용자 [" + userId + "] 에 대한 유효 포인트 총점 기록이 없습니다.");
    }
}
