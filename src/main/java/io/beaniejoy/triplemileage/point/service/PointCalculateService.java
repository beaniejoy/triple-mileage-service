package io.beaniejoy.triplemileage.point.service;

import io.beaniejoy.triplemileage.event.message.PointEvent;
import io.beaniejoy.triplemileage.point.domain.*;
import io.beaniejoy.triplemileage.point.exception.AlreadyReviewAddedException;
import io.beaniejoy.triplemileage.point.exception.ReviewPointNotExistedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PointCalculateService {

    private final PointHistoryRepository pointHistoryRepository;

    private final PointRemainRepository pointRemainRepository;

    private final PointHistoryService pointHistoryService;

    private final PointTotalService pointTotalService;

    @Transactional
    public void addReviewPoint(UUID userId, UUID placeId, PointEvent pointEvent) {

        // 1. point_remain 내 기존 유효한 포인트 존재 여부 체크
        List<PointRemain> pointRemainList = pointRemainRepository.findAllByUserIdAndPlaceId(userId, placeId);
        if (!pointRemainList.isEmpty())
            throw new AlreadyReviewAddedException(userId, placeId);

        // 2. point_history 포인트 이력 저장 & point_remain 유효한 포인트 데이터 저장
        List<PointHistory> pointHistoryList
                = pointHistoryService.createPointHistoryListWhenAddReview(userId, placeId, pointEvent);
        saveAllPointHistoryAndRemainList(pointHistoryList);

        // 3. 갱신된 사용자 유효 포인트 총점 조회
        pointTotalService.updateActivePoints(userId);

        log.info("[PointCalculateService - addReviewPoint] 리뷰 생성에 따른 포인트 이력 정산 완료");
    }

    @Transactional
    public void modifyReviewPoint(UUID userId, UUID placeId, PointEvent pointEvent) {

        // 1. point_remain 내 기존 유효한 포인트 존재 여부 체크
        List<PointRemain> pointRemainList = pointRemainRepository.findAllByUserIdAndPlaceId(userId, placeId);
        if (pointRemainList.isEmpty())
            throw new ReviewPointNotExistedException(userId, placeId);

        // 2. point_history 포인트 이력 저장 & point_remain 유효한 포인트 데이터 저장
        List<PointHistory> pointHistoryList
                = pointHistoryService.createPointHistoryListWhenModifyReview(userId, placeId, pointEvent);
        saveAllPointHistoryAndRemainList(pointHistoryList);

        // 3. 갱신된 사용자 유효 포인트 총점 조회 및 DB 저장
        pointTotalService.updateActivePoints(userId);

        log.info("[PointCalculateService - modifyReviewPoint] 리뷰 수정에 따른 포인트 이력 정산 완료");
    }

    @Transactional
    public void deleteReviewPoint(UUID userId, UUID placeId, PointEvent pointEvent) {

        // 1. point_remain 내 기존 유효한 포인트 존재 여부 체크
        List<PointRemain> pointRemainList = pointRemainRepository.findAllByUserIdAndPlaceId(userId, placeId);
        if (pointRemainList.isEmpty())
            throw new ReviewPointNotExistedException(userId, placeId);

        // 2. point_history 포인트 이력 저장 & point_remain 유효한 포인트 데이터 저장
        List<PointHistory> pointHistoryList
                = pointHistoryService.createPointHistoryListWhenDeleteReview(userId, placeId, pointRemainList);
        saveAllPointHistoryAndRemainList(pointHistoryList);

        // 3. 갱신된 사용자 유효 포인트 총점 조회 및 DB 저장
        pointTotalService.updateActivePoints(userId);
    }

    private void saveAllPointHistoryAndRemainList(List<PointHistory> pointHistoryList) {
        // point_history 포인트 이력 저장
        List<PointHistory> pointHistoriesSaved
                = pointHistoryRepository.saveAll(pointHistoryList);

        // point_remain 유효한 포인트 데이터 저장
        pointRemainRepository.saveAll(convertToPointRemainList(pointHistoriesSaved));
    }

    /**
     * 저장된 PointHistory entity 리스트 기준으로 PointRemain entity 리스트 생성
     */
    private List<PointRemain> convertToPointRemainList(List<PointHistory> pointHistoryList) {
        return pointHistoryList.stream()
                .filter(pointHistory
                        -> (pointHistory.getPointType() != PointType.DEL_ALL_PHOTO)
                        && (pointHistory.getPointType() != PointType.DEL_REVIEW))
                .map(PointHistory::toPointRemainEntity)
                .collect(Collectors.toList());
    }

}
