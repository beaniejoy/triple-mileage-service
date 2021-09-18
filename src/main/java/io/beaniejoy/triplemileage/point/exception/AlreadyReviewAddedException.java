package io.beaniejoy.triplemileage.point.exception;

import java.util.UUID;

public class AlreadyReviewAddedException extends RuntimeException{
    public AlreadyReviewAddedException(UUID userId, UUID placeId) {
        super("해당 사용자[" + userId + "]는 해당 장소[" + placeId + "]에 대한 유효한 리뷰 포인트가 이미 있습니다.");
    }
}
