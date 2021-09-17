package io.beaniejoy.triplemileage.point.exception;

import java.util.UUID;

public class PlaceReviewCountNotFoundException extends RuntimeException{
    public PlaceReviewCountNotFoundException(UUID placeId) {
        super("[" + placeId + "] 장소에 대한 리뷰 개수 데이터가 존재하지 않습니다.");
    }
}
