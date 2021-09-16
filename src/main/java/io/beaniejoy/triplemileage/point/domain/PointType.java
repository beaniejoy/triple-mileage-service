package io.beaniejoy.triplemileage.point.domain;

import lombok.Getter;

@Getter
public enum PointType {
    CONTENT("1자 이상 게시글 포인트"),
    PHOTO("1장 이상 사진첨부 포인트"),
    FIRST("첫번째 리뷰 게시 포인트");

    private final String detail;

    PointType(String detail) {
        this.detail = detail;
    }
}
