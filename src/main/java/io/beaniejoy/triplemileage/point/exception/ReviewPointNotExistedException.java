package io.beaniejoy.triplemileage.point.exception;

import java.util.UUID;

public class ReviewPointNotExistedException extends RuntimeException{
    public ReviewPointNotExistedException(UUID userId, UUID placeId) {
        super("해당 사용자[" + userId + "]는 해당 장소[" + placeId + "]에 대한 유효한 리뷰 포인트가 없습니다.");
    }
}
