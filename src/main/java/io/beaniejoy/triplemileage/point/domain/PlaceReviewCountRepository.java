package io.beaniejoy.triplemileage.point.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.Optional;
import java.util.UUID;

public interface PlaceReviewCountRepository extends JpaRepository<PlaceReviewCount, Long> {
    // WRITE LOCK 부여
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<PlaceReviewCount> findByPlaceId(UUID placeId);
}