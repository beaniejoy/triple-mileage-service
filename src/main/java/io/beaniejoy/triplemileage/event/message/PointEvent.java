package io.beaniejoy.triplemileage.event.message;

import io.beaniejoy.triplemileage.event.type.ActionType;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PointEvent {

    private ActionType actionType;

    private String reviewId;

    private Integer contentLength;

    private Integer photosCount;

    private String userId;

    private String placeId;

}
