CREATE TABLE `point_history` (
    `history_id` bigint NOT NULL AUTO_INCREMENT,
    `point_type` varchar(20) NOT NULL,
    `point` tinyint NOT NULL,
    `user_id` bigint NOT NULL,
    `place_id` bigint NOT NULL,
    `reg_date` datetime,
    `updt_date` datetime,
    PRIMARY KEY (`history_id`),
    INDEX `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `point_remain` (
    `remain_id` bigint NOT NULL AUTO_INCREMENT,
    `point_type` varchar(20) NOT NULL,
    `user_id` bigint NOT NULL,
    `place_id` bigint NOT NULL,
    `reg_date` datetime,
    `updt_date` datetime,
    PRIMARY KEY (`remain_id`),
    INDEX `idx_search_remain` (`user_id`, `place_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `point_total` (
    `total_id` bigint NOT NULL AUTO_INCREMENT,
    `total_remain_point` int NOT NULL,
    `user_id` bigint NOT NULL,
    PRIMARY KEY (`total_id`),
    INDEX idx_user_id (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;