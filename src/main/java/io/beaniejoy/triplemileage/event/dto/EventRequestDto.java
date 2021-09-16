package io.beaniejoy.triplemileage.event.dto;

import io.beaniejoy.triplemileage.event.message.PointEvent;
import io.beaniejoy.triplemileage.event.type.ActionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.EnumUtils;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EventRequestDto {

    private String type;

    private String action;

    private String reviewId;

    private String content;

    private List<String> attachedPhotoIds;

    private String userId;

    private String placeId;

    public PointEvent toPointEvent() {
        return PointEvent.builder()
                .actionType(EnumUtils.getEnum(ActionType.class, this.action))
                .reviewId(this.reviewId)
                .contentLength(this.content.length())
                .photosCount(this.attachedPhotoIds.size())
                .userId(this.userId)
                .placeId(this.placeId)
                .build();
    }
}
