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
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PointCalculateService {

    private final PointHistoryRepository pointHistoryRepository;

    private final PointRemainRepository pointRemainRepository;

    private final PointTotalRepository pointTotalRepository;

    private final PointHistoryService pointHistoryService;

    @Transactional
    public void addReviewPoint(PointEvent pointEvent) {
        log.info("Point calculate Review Action: " + pointEvent.getActionType());

        UUID userId = UUID.fromString(pointEvent.getUserId());
        UUID placeId = UUID.fromString(pointEvent.getPlaceId());

        // 1. point_remain 내 기존 유효한 포인트 존재 여부 체크
        List<PointRemain> pointRemainList = pointRemainRepository.findAllByUserIdAndPlaceId(userId, placeId);
        if (!pointRemainList.isEmpty())
            throw new AlreadyReviewAddedException(userId, placeId);

        // 2. point_history 포인트 이력 저장 & point_remain 유효한 포인트 데이터 저장
        List<PointHistory> pointHistoryList = pointHistoryService.createPointHistoryListWhenAddReview(userId, placeId, pointEvent);
        saveAllPointHistoryAndRemainList(pointHistoryList);

        // 3. 갱신된 사용자 유효 포인트 총점 조회
        calculateUpdatedActivePoints(userId);

        log.info("[PointCalculateService - addReviewPoint] 리뷰 생성에 따른 포인트 이력 정산 완료");
    }

    @Transactional
    public void modifyReviewPoint(PointEvent pointEvent) {
        log.info("Point calculate Review Action: " + pointEvent.getActionType());

        UUID userId = UUID.fromString(pointEvent.getUserId());
        UUID placeId = UUID.fromString(pointEvent.getPlaceId());

        // 1. point_remain 내 기존 유효한 포인트 존재 여부 체크
        List<PointRemain> pointRemainList = pointRemainRepository.findAllByUserIdAndPlaceId(userId, placeId);
        if (pointRemainList.isEmpty())
            throw new ReviewPointNotExistedException(userId, placeId);

        // 2. point_history 포인트 이력 저장 & point_remain 유효한 포인트 데이터 저장
        List<PointHistory> pointHistoryList = pointHistoryService.createPointHistoryListWhenModifyReview(userId, placeId, pointEvent);
        saveAllPointHistoryAndRemainList(pointHistoryList);

        // 3. 갱신된 사용자 유효 포인트 총점 조회
        calculateUpdatedActivePoints(userId);

        log.info("[PointCalculateService - modifyReviewPoint] 리뷰 수정에 따른 포인트 이력 정산 완료");
    }

    @Transactional
    public void deleteReviewPoint(PointEvent pointEvent) {
        log.info("Point calculate Review Action: " + pointEvent.getActionType());
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
                .filter(pointHistory -> pointHistory.getPointType() != PointType.DEL_ALL_PHOTO)
                .map(PointHistory::toPointRemainEntity)
                .collect(Collectors.toList());
    }

    private void calculateUpdatedActivePoints(UUID userId) {
        Integer currentUserPoint = pointRemainRepository.sumPointByUserId(userId);
        log.info("갱신된 user[" + userId + "]의 유효포인트 총점: " + currentUserPoint);

        /*
        사용자 포인트 총점 데이터 저장
        1) PointTotal 조회시 데이터 있는 경우: 해당 데이터에 갱신된 포인트 총점 update
        2) PointTotal 조회시 null 인경우: 새로 PointTotal Entity 저장
         */
        Optional<PointTotal> selectedPointTotal = pointTotalRepository.findByUserId(userId);

        selectedPointTotal.ifPresentOrElse(
                pointTotal -> pointTotal.updateTotalPoint(currentUserPoint),
                () -> pointTotalRepository.save(PointTotal.builder()
                        .totalRemainPoint(currentUserPoint)
                        .userId(userId)
                        .build()));
    }

}
