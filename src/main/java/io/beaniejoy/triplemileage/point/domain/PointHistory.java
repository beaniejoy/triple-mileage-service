package io.beaniejoy.triplemileage.point.domain;

import io.beaniejoy.triplemileage.common.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "point_history")
public class PointHistory extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID historyId;

    private Byte point;

    @Enumerated(EnumType.STRING)
    private PointType pointType;

    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(columnDefinition = "BINARY(16)")
    private UUID placeId;

    private LocalDateTime expiredDate;

    public PointRemain toPointRemainEntity() {
        return PointRemain.builder()
                .point(this.point)
                .pointType(this.pointType)
                .userId(this.userId)
                .placeId(this.placeId)
                .historyId(this.historyId)
                .expiredDate(this.expiredDate)
                .build();
    }
}
