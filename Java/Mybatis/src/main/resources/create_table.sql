SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address` (
                             `id` BIGINT NOT NULL AUTO_INCREMENT,
                             `street` VARCHAR(255) DEFAULT NULL,
                             `city` VARCHAR(255) DEFAULT NULL,
                             `country` VARCHAR(255) DEFAULT NULL,
                             `customer_id` BIGINT DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
                              `id` BIGINT NOT NULL AUTO_INCREMENT,
                              `name` VARCHAR(255) NOT NULL,
                              `phone` VARCHAR(255) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
                           `id` BIGINT NOT NULL AUTO_INCREMENT,
                           `address_id` BIGINT DEFAULT NULL,
                           `customer_id` BIGINT DEFAULT NULL,
                           `create_time` BIGINT DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
                                `id` BIGINT NOT NULL AUTO_INCREMENT,
                                `amount` INT DEFAULT NULL,
                                `order_id` BIGINT DEFAULT NULL,
                                `product_id` BIGINT DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
                             `id` BIGINT NOT NULL AUTO_INCREMENT,
                             `name` VARCHAR(255) DEFAULT NULL,
                             `description` VARCHAR(255) DEFAULT NULL,
                             `price` BIGINT DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

SET FOREIGN_KEY_CHECKS = 1;