 -- 20 May 2022 @Sujeet

 CREATE DATABASE /*!32312 IF NOT EXISTS*/`ecommerce` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
 USE `ecommerce`;

 CREATE TABLE `user` (
   `id` int NOT NULL AUTO_INCREMENT,
   `first_name` varchar(50) NOT NULL,
   `last_name` varchar(50) DEFAULT NULL,
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

 insert  into `user`(`id`,`first_name`,`last_name`) values (1,'Sujeer','singh'),(2,'Sujeer','singh'),(3,'nagendra','chandrakar'),(4,'yukti','laheja');

-- 22 May 2022 @Nagendra

CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `is_enable` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sub_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `category_id` int NOT NULL,
  `is_enable` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK__sub_category__category` (`category_id`),
  CONSTRAINT `FK__sub_category__category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `code` varchar(20) NOT NULL,
  `short_desc` varchar(30) NOT NULL,
  `long_desc` varchar(100) DEFAULT NULL,
  `unit_type` enum('ML','BOX','KG','GM','PIECE') NOT NULL,
  `unit_price` decimal(10,0) NOT NULL,
  `mrp` decimal(10,0) NOT NULL,
  `sub_category_id` int NOT NULL,
  `is_enable` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `FK__product__sub_category` (`sub_category_id`),
  CONSTRAINT `FK__product__sub_category` FOREIGN KEY (`sub_category_id`) REFERENCES `sub_category` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `product_details` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `name` varchar(20) NOT NULL,
  `type` enum('COLOR','SIZE') NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__product_details__product` (`product_id`),
  CONSTRAINT `FK__product_details__product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--24 may 2022 @Nagendra
ALTER TABLE `product`     CHANGE `name` `name` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,     CHANGE `short_desc` `short_desc` VARCHAR(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,     CHANGE `long_desc` `long_desc` VARCHAR(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL ;

--26 may 2022 @Nagendra
ALTER TABLE product ADD UNIQUE (`code`) ;

