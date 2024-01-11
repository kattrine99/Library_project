-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: users
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `book_id` int(11) NOT NULL,
  `book_name` varchar(45) NOT NULL,
  `book_description` varchar(45) NOT NULL,
  `book_quantity` int(11) NOT NULL,
  `book_fee` double NOT NULL,
  PRIMARY KEY (`book_id`),
  UNIQUE KEY `book_id_UNIQUE` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (100,'Calculus','High math or how you become introvert',9,2.5),(101,'Discreate math','Ultra -high math or how you become extravert',5,5),(102,'Empire','How to build your Empire',1,100),(103,'English','Some great description',2,1.22);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fee_info`
--

DROP TABLE IF EXISTS `fee_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fee_info` (
  `user_id` varchar(10) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `book_id` varchar(10) NOT NULL,
  `book_name` varchar(45) NOT NULL,
  `initial_date` date NOT NULL,
  `final_date` date NOT NULL,
  `fee_value` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fee_info`
--

LOCK TABLES `fee_info` WRITE;
/*!40000 ALTER TABLE `fee_info` DISABLE KEYS */;
INSERT INTO `fee_info` VALUES ('1810010','Sam','102','Empire','2019-12-06','2019-11-30',100),('1810011','Steven','101','Java2','2016-02-15','2017-02-15',55),('1810017','Sarvar','100','Calculus','2019-12-03','2019-12-19',2.5),('1810010','Sam','101','Discreate math','2019-11-30','2019-12-01',5),('12345','kattrine','100','Calculus','2019-12-08','2019-12-10',2.5),('1810010','Sam','100','Calculus','2019-11-29','2019-11-30',2.5),('1810010','Sam','102','Empire','2019-12-12','2021-12-10',100),('1810010','Sam','102','Empire','2019-12-12','2021-12-11',100),('1810050','Zarnigor','100','Calculus','2019-12-12','2019-12-27',2.5);
/*!40000 ALTER TABLE `fee_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_users`
--

DROP TABLE IF EXISTS `login_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_users` (
  `id` int(11) NOT NULL,
  `user_name` varchar(45) NOT NULL,
  `user_password` varchar(45) NOT NULL,
  `user_status` varchar(45) NOT NULL DEFAULT 'Student',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_users`
--

LOCK TABLES `login_users` WRITE;
/*!40000 ALTER TABLE `login_users` DISABLE KEYS */;
INSERT INTO `login_users` VALUES (1,'Putin','0000001','Student'),(10,'Spy12','12345','Student'),(1000,'Spy','1111','Student'),(12345,'kattrine','99999','Student'),(121212,'Obama','123452s','Student'),(1010010,'OverLord','123456789','Admin'),(1111111,'Annastasiya','nastya','Student'),(1450012,'Dmitriy','9874','Student'),(1710356,'Elizabeth II','Eli1997','Librarian'),(1777771,'Shepard','N7','Student'),(1810002,'Ulugbek','1998','Student'),(1810010,'Sam','1234','Student'),(1810012,'Stiv','321','Student'),(1810014,'Ekaterina','456','Admin'),(1810017,'Sarvar','123','Admin'),(1810050,'Zarnigor','789','Student'),(1810056,'Yuriy','Yuriy1963','Student'),(1810063,'Anna','asd','Student'),(1810123,'David','987aas','Student'),(1810178,'Ivan','9173','Librarian'),(1910012,'Peter','peter1999','Librarian'),(18177133,'Jasur','1810013','Student');
/*!40000 ALTER TABLE `login_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newtest`
--

DROP TABLE IF EXISTS `newtest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `newtest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `userId` varchar(8) NOT NULL,
  `password` varchar(45) NOT NULL,
  `email` varchar(64) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Password_UNIQUE` (`password`),
  UNIQUE KEY `UserId_UNIQUE` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newtest`
--

LOCK TABLES `newtest` WRITE;
/*!40000 ALTER TABLE `newtest` DISABLE KEYS */;
INSERT INTO `newtest` VALUES (1,'Sarvar','U1810017','U1810017','arifdjanovsarvar@gmail.com');
/*!40000 ALTER TABLE `newtest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `age` int(3) DEFAULT NULL,
  `mail` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES (2,'Sarvar',20,'arifdjanovsarvar@gmail.com'),(3,'Obama',64,'Hey@gmail.com'),(4,'Putin',99,'Hey@gmail.com'),(5,'Ahmed',18,'\"something@gmail.com\"'),(6,'ssar',20,'\"abc@gmail.com\"');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_info`
--

DROP TABLE IF EXISTS `users_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user name` varchar(45) NOT NULL,
  `user id` int(11) NOT NULL,
  `user status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_info`
--

LOCK TABLES `users_info` WRITE;
/*!40000 ALTER TABLE `users_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-17  9:28:02
