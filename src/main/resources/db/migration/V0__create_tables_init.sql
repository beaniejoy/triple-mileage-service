CREATE TABLE `point_history` (
    `history_id` binary(16) NOT NULL,
    `point_type` varchar(20) NOT NULL,
    `point` tinyint NOT NULL,
    `user_id` binary(16) NOT NULL,
    `place_id` binary(16) NOT NULL,
    `expired_date` datetime,
    `registered_date` datetime,
    `updated_date` datetime,
    PRIMARY KEY (`history_id`),
INDEX `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `point_remain` (
    `remain_id` binary(16) NOT NULL,
    `point_type` varchar(20) NOT NULL,
    `point` tinyint NOT NULL,
    `user_id` binary(16) NOT NULL,
    `place_id` binary(16) NOT NULL,
    `history_id` binary(16) NOT NULL,
    `expired_date` datetime,
    `registered_date` datetime,
    `updated_date` datetime,
    PRIMARY KEY (`remain_id`),
    INDEX `idx_search_remain` (`user_id`, `place_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `point_total` (
    `total_id` binary(16) NOT NULL,
    `total_remain_point` int NOT NULL,
    `user_id` binary(16) NOT NULL,
    `registered_date` datetime,
    `updated_date` datetime,
    PRIMARY KEY (`total_id`),
    INDEX idx_user_id (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `place_review_count` (
    `review_count_id` int NOT NULL AUTO_INCREMENT,
    `place_id` binary(16) NOT NULL,
    `review_count` int NOT NULL,
    `registered_date` datetime,
    `updated_date` datetime,
    PRIMARY KEY (`review_count_id`),
    INDEX idx_place_id (`place_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;