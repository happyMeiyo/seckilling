CREATE DATABASE `miaosha` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;


-- miaosha.item definition

CREATE TABLE `item` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `title` varchar(64) NOT NULL DEFAULT '',
                        `price` decimal(10,2) NOT NULL DEFAULT '0.00',
                        `description` varchar(64) NOT NULL DEFAULT '',
                        `sales` int NOT NULL DEFAULT '0',
                        `img_url` varchar(100) NOT NULL DEFAULT '',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- miaosha.promo definition

CREATE TABLE `promo` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `promo_name` varchar(100) NOT NULL DEFAULT '',
                         `start_date` datetime NOT NULL,
                         `item_id` int NOT NULL DEFAULT '0',
                         `promo_item_price` decimal(10,2) NOT NULL DEFAULT '0.00',
                         `end_date` datetime DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `promo_FK` (`item_id`),
                         CONSTRAINT `promo_FK` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- miaosha.sequence_info definition

CREATE TABLE `sequence_info` (
                                 `name` varchar(100) NOT NULL DEFAULT '',
                                 `current_value` int NOT NULL DEFAULT '0',
                                 `step` int NOT NULL DEFAULT '0',
                                 PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- miaosha.user_info definition

CREATE TABLE `user_info` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
                             `gender` tinyint NOT NULL DEFAULT '0',
                             `age` int NOT NULL DEFAULT '0',
                             `telephone` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
                             `register_mode` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
                             `third_party_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `user_info_telephone_IDX` (`telephone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- miaosha.item_stock definition

CREATE TABLE `item_stock` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `stock` int NOT NULL DEFAULT '0',
                              `item_id` int NOT NULL,
                              PRIMARY KEY (`id`),
                              KEY `stock_FK` (`item_id`),
                              CONSTRAINT `stock_FK` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- miaosha.order_info definition

CREATE TABLE `order_info` (
                              `id` varchar(100) NOT NULL DEFAULT '',
                              `user_id` int NOT NULL DEFAULT '0',
                              `item_id` int NOT NULL DEFAULT '0',
                              `item_price` decimal(10,2) NOT NULL DEFAULT '0.00',
                              `amount` int NOT NULL DEFAULT '0',
                              `order_price` decimal(10,2) NOT NULL DEFAULT '0.00',
                              PRIMARY KEY (`id`),
                              KEY `order_info_FK` (`item_id`),
                              KEY `order_info_FK_1` (`user_id`),
                              CONSTRAINT `order_info_FK` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`),
                              CONSTRAINT `order_info_FK_1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- miaosha.user_password definition

CREATE TABLE `user_password` (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `encrpt_password` varchar(128) NOT NULL,
                                 `user_id` int NOT NULL DEFAULT '0',
                                 PRIMARY KEY (`id`),
                                 KEY `user_password_FK` (`user_id`),
                                 CONSTRAINT `user_password_FK` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;