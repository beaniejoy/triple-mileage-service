package io.beaniejoy.triplemileage.point.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PointTotalRepository extends JpaRepository<PointTotal, UUID> {
    Optional<PointTotal> findByUserId(UUID userId);
}
