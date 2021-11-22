-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel_db
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `client_order`
--

DROP TABLE IF EXISTS `client_order`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_order`
(
    `client_order_room_id` int    NOT NULL,
    `client_id`            bigint NOT NULL,
    PRIMARY KEY (`client_order_room_id`),
    UNIQUE KEY `id_UNIQUE` (`client_order_room_id`),
    KEY `client_id_fk_idx` (`client_id`),
    CONSTRAINT `client_id_fk` FOREIGN KEY (`client_id`) REFERENCES `user` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_order`
--

LOCK TABLES `client_order` WRITE;
/*!40000 ALTER TABLE `client_order`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `client_order`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_order_room`
--

DROP TABLE IF EXISTS `client_order_room`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_order_room`
(
    `client_order_id` int         NOT NULL,
    `room_id`         int         NOT NULL,
    `persons_amount`  int         NOT NULL,
    `check_in_date`   date        NOT NULL,
    `check_out_date`  date        NOT NULL,
    `order_status`    varchar(20) NOT NULL,
    PRIMARY KEY (`room_id`),
    UNIQUE KEY `room_id_UNIQUE` (`room_id`),
    KEY `client_order_fk_id_idx` (`client_order_id`),
    CONSTRAINT `client_order_id_fk` FOREIGN KEY (`client_order_id`) REFERENCES `client_order` (`client_order_room_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `room_id_fk` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`) ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_order_room`
--

LOCK TABLES `client_order_room` WRITE;
/*!40000 ALTER TABLE `client_order_room`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `client_order_room`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room`
(
    `id`                int        NOT NULL AUTO_INCREMENT,
    `room_class_id`     int        NOT NULL,
    `room_cost`         double     NOT NULL,
    `persons_in_room`   int        NOT NULL,
    `room_availability` tinyint(1) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `room_class_id_idx` (`room_class_id`),
    CONSTRAINT `room_class_id_fk` FOREIGN KEY (`room_class_id`) REFERENCES `room_class` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8 COMMENT ='consist rooms infromation';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room`
    DISABLE KEYS */;
INSERT INTO `room`
VALUES (1, 1, 45.6, 1, 1),
       (2, 1, 45.6, 1, 1),
       (3, 1, 45.6, 1, 1),
       (4, 1, 45.6, 1, 1),
       (5, 1, 45.6, 1, 1),
       (6, 2, 87.9, 2, 1),
       (7, 2, 87.9, 2, 1),
       (8, 2, 87.9, 2, 1),
       (9, 2, 87.9, 2, 1),
       (10, 3, 123.5, 3, 1),
       (11, 3, 123.5, 3, 1),
       (12, 3, 123.5, 3, 1),
       (13, 4, 250, 4, 1),
       (14, 4, 250, 4, 1);
/*!40000 ALTER TABLE `room`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_class`
--

DROP TABLE IF EXISTS `room_class`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_class`
(
    `id`         int         NOT NULL AUTO_INCREMENT,
    `room_class` varchar(20) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `class_name_UNIQUE` (`room_class`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8 COMMENT ='consist different class of rooms';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_class`
--

LOCK TABLES `room_class` WRITE;
/*!40000 ALTER TABLE `room_class`
    DISABLE KEYS */;
INSERT INTO `room_class`
VALUES (4, 'deluxe'),
       (2, 'double'),
       (1, 'single'),
       (3, 'suite');
/*!40000 ALTER TABLE `room_class`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user`
(
    `id`         bigint      NOT NULL,
    `first_name` varchar(20) NOT NULL,
    `last_name`  varchar(20) NOT NULL,
    `user_type`  varchar(20) NOT NULL,
    `email`      varchar(20) DEFAULT NULL,
    `password`   varchar(24) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user`
    DISABLE KEYS */;
/*!40000 ALTER TABLE `user`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'hotel_db'
--
/*!50003 DROP PROCEDURE IF EXISTS `delete_record_from_client_order_room` */;
/*!50003 SET @saved_cs_client = @@character_set_client */;
/*!50003 SET @saved_cs_results = @@character_set_results */;
/*!50003 SET @saved_col_connection = @@collation_connection */;
/*!50003 SET character_set_client = utf8mb4 */;
/*!50003 SET character_set_results = utf8mb4 */;
/*!50003 SET collation_connection = utf8mb4_0900_ai_ci */;
/*!50003 SET @saved_sql_mode = @@sql_mode */;
/*!50003 SET sql_mode = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */;
DELIMITER ;;
CREATE
    DEFINER = `administrator`@`localhost` PROCEDURE `delete_record_from_client_order_room`(orderID int, roomID int)
BEGIN
    declare result int;

    SELECT COUNT(1)
    into result
    FROM client_order_room
    WHERE client_order_id = roomID;

    if result = 1 then
        DELETE
        FROM client_order
        where client_order_room_id = orderID;
    else
        DELETE
        FROM client_order_room
        WHERE room_id = roomID;
    end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode = @saved_sql_mode */;
/*!50003 SET character_set_client = @saved_cs_client */;
/*!50003 SET character_set_results = @saved_cs_results */;
/*!50003 SET collation_connection = @saved_col_connection */;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2021-11-21 12:05:13
