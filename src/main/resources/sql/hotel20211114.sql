CREATE DATABASE  IF NOT EXISTS `hotel_db` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hotel_db`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel_db
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `client_order`
--

DROP TABLE IF EXISTS `client_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_order` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `client_id_idx` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_order`
--

LOCK TABLES `client_order` WRITE;
/*!40000 ALTER TABLE `client_order` DISABLE KEYS */;
INSERT INTO `client_order` VALUES (1,2),(2,3),(3,4);
/*!40000 ALTER TABLE `client_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_order_room`
--

DROP TABLE IF EXISTS `client_order_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client_order_room` (
  `client_order_id` int NOT NULL,
  `room_id` int NOT NULL,
  `persons_amount` int NOT NULL,
  `start_date` date NOT NULL,
  `days_of_booking` int NOT NULL,
  `order_status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`room_id`),
  UNIQUE KEY `room_id_UNIQUE` (`room_id`),
  KEY `client_order_fk_id_idx` (`client_order_id`),
  KEY `order_status_fk_idx` (`order_status`),
  CONSTRAINT `client_order_fk_id` FOREIGN KEY (`client_order_id`) REFERENCES `client_order` (`id`) ON UPDATE RESTRICT,
  CONSTRAINT `order_status_fk` FOREIGN KEY (`order_status`) REFERENCES `order_status` (`order_status`),
  CONSTRAINT `room_fk_id` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_order_room`
--

LOCK TABLES `client_order_room` WRITE;
/*!40000 ALTER TABLE `client_order_room` DISABLE KEYS */;
INSERT INTO `client_order_room` VALUES (1,1,1,'2021-11-25',4,'approved and booked'),(2,2,2,'2021-11-30',3,'waiting for approval'),(2,3,2,'2021-12-08',7,'approved and booked'),(3,6,2,'2021-12-31',2,'waiting for approval');
/*!40000 ALTER TABLE `client_order_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_status`
--

DROP TABLE IF EXISTS `order_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_status` (
  `order_status` varchar(20) NOT NULL,
  UNIQUE KEY `status_name_UNIQUE` (`order_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='consist typies of order status';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
INSERT INTO `order_status` VALUES ('approved and booked'),('waiting for approval');
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` int NOT NULL,
  `room_class_id` int NOT NULL,
  `room_cost` double NOT NULL,
  `room_availability` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `room_class_id_idx` (`room_class_id`),
  CONSTRAINT `room_class_id` FOREIGN KEY (`room_class_id`) REFERENCES `room_class` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='consist rooms infromation';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,1,45.6,1),(2,1,45.6,1),(3,1,45.6,1),(4,1,45.6,1),(5,1,45.6,1),(6,2,87.9,1),(7,2,87.9,1),(8,2,87.9,1),(9,2,87.9,1),(10,3,123.5,1),(11,3,123.5,1),(12,3,123.5,1),(13,4,250,1),(14,4,250,1);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_class`
--

DROP TABLE IF EXISTS `room_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_class` (
  `id` int NOT NULL,
  `room_class` varchar(20) NOT NULL,
  `persons_in_room` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `class_name_UNIQUE` (`room_class`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='consist different class of rooms';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_class`
--

LOCK TABLES `room_class` WRITE;
/*!40000 ALTER TABLE `room_class` DISABLE KEYS */;
INSERT INTO `room_class` VALUES (1,'single',1),(2,'double',2),(3,'suite',4),(4,'deluxe',4);
/*!40000 ALTER TABLE `room_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `user_type` varchar(20) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `password` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Igor','Taren','admin',NULL,NULL),(2,'Ivan','Pupkin','client',NULL,NULL),(3,'Stepan','Vilkin','client',NULL,NULL),(4,'Svetlana','Otverkina','client',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-14 20:23:21
