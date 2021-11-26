-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: covid_project
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `contagiousinstructions`
--

DROP TABLE IF EXISTS `contagiousinstructions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contagiousinstructions` (
  `instruction` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contagiousinstructions`
--

LOCK TABLES `contagiousinstructions` WRITE;
/*!40000 ALTER TABLE `contagiousinstructions` DISABLE KEYS */;
INSERT INTO `contagiousinstructions` VALUES ('Wear a mask properly.'),('Stay at home.'),('Take care of yourself.'),('Stay in touch with your doctor.'),('Avoid public transportation.'),('As much as possible, stay in a specific room');
/*!40000 ALTER TABLE `contagiousinstructions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `safeinstructions`
--

DROP TABLE IF EXISTS `safeinstructions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `safeinstructions` (
  `instruction` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `safeinstructions`
--

LOCK TABLES `safeinstructions` WRITE;
/*!40000 ALTER TABLE `safeinstructions` DISABLE KEYS */;
INSERT INTO `safeinstructions` VALUES ('Always keep your mask on!'),('Wear a mask properly.'),('Make your environment safer!'),('Keep good hygiene!'),('Keep social distance!'),('Go outside peek hour!'),('Keep your sanitizer with you!'),('Keep your shopping time short and make a list!');
/*!40000 ALTER TABLE `safeinstructions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `photo` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(320) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `password` varbinary(255) DEFAULT NULL,
  `vaccinated` tinyint(1) NOT NULL,
  `vaccination_card` varbinary(8000) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('010','Ali','Jad','jad@gmail.com','ali_jad',_binary '12345678',0,_binary '0101',NULL),('000','Hasan','Mshawrab','hasan@gmail.com','hasan_mshawrab',_binary '123123',1,_binary '0000',NULL),('','Adam','Smith','adam@gmail.com','will_smith',_binary '\Ôy|Å-˚dñ\›]?åv#åú=S,\…\\^◊®ò¶O',0,_binary '0101',NULL),('C:\\Users\\lenovo\\Downloads\\2021.10.10-12.27.42.jpg','yara','chahine','yara@gmail.com','yara_chahine',_binary '\Ôy|Å-˚dñ\›]?åv#åú=S,\…\\^◊®ò¶O',0,_binary 'null','safe');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-26 19:08:30
