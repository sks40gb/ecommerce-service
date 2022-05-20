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
