package io.beaniejoy.triplemileage.point.domain;

import io.beaniejoy.triplemileage.common.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class PlaceReviewCount extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewCountId;

    @Column(columnDefinition = "BINARY(16)")
    private UUID placeId;

    private Integer reviewCount;

    public void addReviewCount() {
        this.reviewCount++;
    }

    public void minusReviewCount() {
        this.reviewCount--;
    }
}
