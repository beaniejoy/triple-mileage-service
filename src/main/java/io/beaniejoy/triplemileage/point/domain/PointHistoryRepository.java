package io.beaniejoy.triplemileage.point.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PointHistoryRepository extends JpaRepository<PointHistory, UUID> {
}
