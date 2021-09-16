package io.beaniejoy.triplemileage.point.domain;

import io.beaniejoy.triplemileage.common.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID totalId;

    private Integer totalRemainPoint;

    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    public void updateTotalPoint(Integer totalRemainPoint) {
        this.totalRemainPoint = totalRemainPoint;
    }
}
