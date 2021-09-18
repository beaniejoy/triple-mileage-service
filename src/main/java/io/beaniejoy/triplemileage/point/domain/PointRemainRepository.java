package io.beaniejoy.triplemileage.point.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PointRemainRepository extends JpaRepository<PointRemain, Long> {

    List<PointRemain> findAllByUserIdAndPlaceId(UUID userId, UUID placeId);

    Optional<PointRemain> findByUserIdAndPlaceIdAndPointType(UUID userId, UUID placeId, PointType pointType);

    @Query(value = "SELECT IFNULL(sum(point), 0) FROM point_remain WHERE user_id = :userId", nativeQuery = true)
    Integer sumPointByUserId(UUID userId);

}
