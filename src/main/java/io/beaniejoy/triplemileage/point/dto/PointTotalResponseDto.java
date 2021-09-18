package io.beaniejoy.triplemileage.point.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointTotalResponseDto {

    private Integer totalRemainPoint;
}
