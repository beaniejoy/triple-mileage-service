package io.beaniejoy.triplemileage.point.service;

import io.beaniejoy.triplemileage.common.redis.CacheKey;
import io.beaniejoy.triplemileage.point.domain.PointRemainRepository;
import io.beaniejoy.triplemileage.point.domain.PointTotal;
import io.beaniejoy.triplemileage.point.domain.PointTotalRepository;
import io.beaniejoy.triplemileage.point.dto.PointTotalResponseDto;
import io.beaniejoy.triplemileage.point.exception.PointTotalNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PointTotalService {

    private final PointTotalRepository pointTotalRepository;

    private final PointRemainRepository pointRemainRepository;

    /**
     * 특정 사용자의 유효 포인트 총점 조회<br>
     * redis에 cache가 없는 경우 DB 조회 후 redis에 저장
     * @param userId 조회하는 특정 사용자의 ID
     * @return 유효포인트 총점
     */
    @Cacheable(cacheNames = CacheKey.POINT, key = "#userId")
    @Transactional(readOnly = true)
    public PointTotalResponseDto findTotalPoint(UUID userId) {

        PointTotal pointTotal = pointTotalRepository.findByUserId(userId)
                .orElseThrow(() -> new PointTotalNotFoundException(userId));

        return pointTotal.toResponse();
    }

    /**
     * REVIEW Action(ADD, MOD, DELETE) 후 포인트 총점 DB update<br>
     * redis cache 내용은 삭제 (조회시 DB에서 cache update 이루어질 것임)<br>
     * <br>
     * - 사용자 포인트 총점 데이터 저장<br>
     * <ul>
     * <li>PointTotal 조회시 데이터 있는 경우: 해당 데이터에 갱신된 포인트 총점 update</li>
     * <li>PointTotal 조회시 null 인경우: 새로 PointTotal Entity 저장</li>
     * </ul>
     * @param userId 총점 update 하려는 사용자 ID
     */
    @CacheEvict(cacheNames = CacheKey.POINT, key = "#userId")
    @Transactional
    public void updateActivePoints(UUID userId) {
        Integer currentUserPoint = pointRemainRepository.sumPointByUserId(userId);
        log.info("갱신된 user[" + userId + "]의 유효포인트 총점: " + currentUserPoint);

        Optional<PointTotal> selectedPointTotal = pointTotalRepository.findByUserId(userId);
        selectedPointTotal.ifPresentOrElse(
                pointTotal -> pointTotal.updateTotalPoint(currentUserPoint),
                () -> pointTotalRepository.save(PointTotal.builder()
                        .totalRemainPoint(currentUserPoint)
                        .userId(userId)
                        .build())
        );
    }
}