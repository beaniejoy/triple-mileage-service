package io.beaniejoy.triplemileage.point.domain;

import io.beaniejoy.triplemileage.common.entity.BaseTimeEntity;
import io.beaniejoy.triplemileage.point.dto.PointTotalResponseDto;
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
@Table(name = "point_total")
public class PointTotal extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long totalId;

    private Integer totalRemainPoint;

    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    public void updateTotalPoint(Integer totalRemainPoint) {
        this.totalRemainPoint = totalRemainPoint;
    }

    public PointTotalResponseDto toResponse() {
        return PointTotalResponseDto.builder()
                .totalRemainPoint(this.totalRemainPoint)
                .build();
    }
}
