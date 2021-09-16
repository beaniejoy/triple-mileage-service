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
@Table(name = "point_remain")
public class PointRemain extends BaseTimeEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID remainId;

    private Byte point;

    @Enumerated(EnumType.STRING)
    private PointType pointType;

    @Column(columnDefinition = "BINARY(16)")
    private UUID userId;

    @Column(columnDefinition = "BINARY(16)")
    private UUID placeId;

    @Column(columnDefinition = "BINARY(16)")
    private UUID historyId;

    private LocalDateTime expiredDate;

}
