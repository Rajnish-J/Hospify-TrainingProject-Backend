-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: appointment
-- ------------------------------------------------------
-- Server version	8.0.38

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
-- Table structure for table `appointments`
--

DROP TABLE IF EXISTS `appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointments` (
  `appointment_id` bigint NOT NULL AUTO_INCREMENT,
  `appointment_date` date NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `doctor_id` bigint NOT NULL,
  `reason` varchar(30) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `patient_id` bigint NOT NULL,
  `status_id` int NOT NULL,
  PRIMARY KEY (`appointment_id`),
  KEY `FK8exap5wmg8kmb1g1rx3by21yt` (`patient_id`),
  CONSTRAINT `FK8exap5wmg8kmb1g1rx3by21yt` FOREIGN KEY (`patient_id`) REFERENCES `patients` (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointments`
--

LOCK TABLES `appointments` WRITE;
/*!40000 ALTER TABLE `appointments` DISABLE KEYS */;
INSERT INTO `appointments` VALUES (1,'2025-05-14','2024-10-16 15:54:31.119711',1,'Cured','2024-10-17 19:32:27.786124',2,1),(2,'2023-02-02','2023-01-02 00:00:00.000000',2,'Cured','2024-10-17 19:32:20.911573',2,2),(5,'2024-12-01','2023-01-05 00:00:00.000000',5,'consultation','2024-12-04 18:46:26.927374',5,5),(10,'2025-01-16','2024-10-17 19:34:38.433752',1,'general','2024-10-17 19:34:38.433752',27,5),(13,'2024-10-20','2024-10-17 20:02:15.868373',1,'visa check','2024-10-17 20:02:15.868373',23,3),(82,'2024-12-09','2024-11-29 20:34:45.334304',3,'check','2024-11-29 20:34:45.334304',23,71),(86,'2024-12-09','2024-11-30 10:24:01.279536',1,'cold','2024-11-30 10:25:05.781320',96,75),(87,'2024-12-07','2024-11-30 10:24:46.092815',2,'cough','2024-11-30 10:24:46.092815',96,76),(89,'2024-12-21','2024-11-30 11:12:33.016215',2,'check','2024-11-30 11:12:33.016215',98,78),(146,'2024-12-20','2024-12-08 10:20:54.838646',1,'eye examination','2024-12-08 10:20:54.838646',137,135);
/*!40000 ALTER TABLE `appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appointmentstatus`
--

DROP TABLE IF EXISTS `appointmentstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointmentstatus` (
  `status_id` int NOT NULL AUTO_INCREMENT,
  `status_name` varchar(30) NOT NULL,
  PRIMARY KEY (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointmentstatus`
--

LOCK TABLES `appointmentstatus` WRITE;
/*!40000 ALTER TABLE `appointmentstatus` DISABLE KEYS */;
INSERT INTO `appointmentstatus` VALUES (1,'Scheduled'),(2,'Pending'),(3,'Cancelled'),(4,'Pending'),(5,'Confirmed'),(12,'Pending'),(13,'Pending'),(14,'Pending'),(15,'Pending'),(19,'Pending'),(20,'Pending'),(21,'Pending'),(22,'Pending'),(23,'Pending'),(24,'Pending'),(28,'Pending'),(29,'Pending'),(30,'Pending'),(31,'Pending'),(32,'Pending'),(33,'Pending'),(34,'Pending'),(35,'Pending'),(36,'Pending'),(37,'Pending'),(38,'Pending'),(39,'Pending'),(40,'Pending'),(41,'Pending'),(43,'Pending'),(44,'Pending'),(45,'Pending'),(46,'Pending'),(47,'Pending'),(48,'Pending'),(49,'Pending'),(50,'Pending'),(51,'Pending'),(52,'Pending'),(53,'Pending'),(54,'Pending'),(55,'Pending'),(56,'Pending'),(57,'Pending'),(58,'Pending'),(59,'Pending'),(62,'Pending'),(63,'Pending'),(64,'Pending'),(65,'Pending'),(70,'Pending'),(71,'Pending'),(72,'Pending'),(73,'Pending'),(74,'Pending'),(75,'Pending'),(76,'Pending'),(77,'Pending'),(78,'Pending'),(81,'Pending'),(82,'Pending'),(83,'Pending'),(84,'Pending'),(85,'Pending'),(86,'Pending'),(87,'Pending'),(89,'Pending'),(91,'Pending'),(92,'Pending'),(95,'Pending'),(96,'Pending'),(98,'Pending'),(99,'Pending'),(100,'Pending'),(101,'Pending'),(102,'Pending'),(103,'Pending'),(104,'Pending'),(106,'Pending'),(108,'Pending'),(109,'Pending'),(110,'Pending'),(111,'Pending'),(112,'Pending'),(115,'Pending'),(117,'Pending'),(119,'Pending'),(120,'Pending'),(122,'Pending'),(123,'Pending'),(124,'Pending'),(125,'Pending'),(126,'Pending'),(127,'Pending'),(129,'Pending'),(131,'Pending'),(132,'Pending'),(134,'Pending'),(135,'Pending'),(136,'Pending'),(139,'Pending'),(140,'Pending'),(141,'Pending'),(142,'Pending'),(143,'Pending');
/*!40000 ALTER TABLE `appointmentstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `city` (
  `city_id` int NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  PRIMARY KEY (`city_id`),
  UNIQUE KEY `UK9s1n56j4o997fpcp1do8juel1` (`city`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'Chennai'),(3,'Chicago'),(4,'Houston'),(2,'Madurai'),(5,'Phoenix');
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `country_id` int NOT NULL AUTO_INCREMENT,
  `country` varchar(255) NOT NULL,
  PRIMARY KEY (`country_id`),
  UNIQUE KEY `UK3s51q344kj9jse05r86moo9ka` (`country`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'India'),(2,'UK');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor` (
  `doctor_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `doc_email` varchar(255) NOT NULL,
  `doc_password` varchar(255) NOT NULL,
  `doc_phone` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `hospital_id` bigint NOT NULL,
  `specialization_id` int NOT NULL,
  PRIMARY KEY (`doctor_id`),
  UNIQUE KEY `UKm3qpaxdxsoksy0f5w7mhsfpsr` (`doc_email`),
  UNIQUE KEY `UKtcadkdtg5veemjc4pxdrkmknb` (`doc_password`),
  UNIQUE KEY `UKfq3v7ex241ytrro3k9n4dgv3m` (`doc_phone`),
  KEY `FKds7ws3yyj4c5wj35fpefpeny0` (`hospital_id`),
  KEY `FKq23vqgpxphxpr1wwn10fxifhh` (`specialization_id`),
  CONSTRAINT `FKds7ws3yyj4c5wj35fpefpeny0` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`hospital_id`),
  CONSTRAINT `FKq23vqgpxphxpr1wwn10fxifhh` FOREIGN KEY (`specialization_id`) REFERENCES `specialization` (`specialization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (1,'2023-01-03 00:00:00.000000','raghu@gmail.com','raguu@147','8811555401','Dr. P.',' Raghu','2023-01-03 00:00:00.000000',1,1),(2,'2023-01-03 00:00:00.000000','sharma@gmail.com','sharma963','9627439734','Dr. R. K. K. ','Sharma','2023-01-03 00:00:00.000000',1,2),(3,'2023-01-03 00:00:00.000000','cherian@gmail.com','cherian@258','8139710306','Dr. K. M. ','Cherian','2023-01-03 00:00:00.000000',1,3),(4,'2023-01-03 00:00:00.000000','murthy@gmail.com','murthy@159','9690776875','Dr. M. S. V. K. ','Murthy','2023-01-03 00:00:00.000000',1,4),(5,'2023-01-03 00:00:00.000000','ramachandar@gmail.com','rama@483','7786565025 ','Dr. T. R. ','Ramachandran','2023-01-03 00:00:00.000000',1,5);
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospital`
--

DROP TABLE IF EXISTS `hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hospital` (
  `hospital_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `hospital_email` varchar(255) NOT NULL,
  `hospital_name` varchar(255) NOT NULL,
  `hospital_phone` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `city_id` int NOT NULL,
  `country_id` int NOT NULL,
  `state_id` int NOT NULL,
  PRIMARY KEY (`hospital_id`),
  UNIQUE KEY `UKk7d8jdv7xv6rnpgqh9ge0jslb` (`hospital_email`),
  UNIQUE KEY `UKq1oy3bctai10nq1h4ppn42g5v` (`hospital_phone`),
  KEY `FKtdmqkgrqa2694xrw7pw9josgi` (`city_id`),
  KEY `FKi6itl5yp63c5s6i9u6dfcb5uv` (`country_id`),
  KEY `FK4yi5nbx2uyovhmlt468n8826n` (`state_id`),
  CONSTRAINT `FK4yi5nbx2uyovhmlt468n8826n` FOREIGN KEY (`state_id`) REFERENCES `state` (`state_id`),
  CONSTRAINT `FKi6itl5yp63c5s6i9u6dfcb5uv` FOREIGN KEY (`country_id`) REFERENCES `country` (`country_id`),
  CONSTRAINT `FKtdmqkgrqa2694xrw7pw9josgi` FOREIGN KEY (`city_id`) REFERENCES `city` (`city_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospital`
--

LOCK TABLES `hospital` WRITE;
/*!40000 ALTER TABLE `hospital` DISABLE KEYS */;
INSERT INTO `hospital` VALUES (1,'2024-10-16 15:54:31.119711','appolo@gmail.com','Appolo','9008007554','2024-10-16 15:54:31.119711',1,1,1),(2,'2023-01-02 00:00:00.000000','contact@metromedical.com','Metro Medical Center','9876543211','2023-01-02 00:00:00.000000',2,1,2),(3,'2023-01-03 00:00:00.000000','hello@sunriseclinic.com','Sunrise Clinic','9876543212','2023-01-03 00:00:00.000000',3,1,3),(4,'2023-01-04 00:00:00.000000','support@globalhealth.com','Global Health Care','9876543213','2023-01-04 00:00:00.000000',1,2,1),(5,'2023-01-05 00:00:00.000000','info@carehospital.com','Care Hospital','9876543214','2023-01-05 00:00:00.000000',2,2,2);
/*!40000 ALTER TABLE `hospital` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `patient_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `dob` date NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `patient_email` varchar(255) NOT NULL,
  `patient_password` varchar(255) NOT NULL,
  `patient_phone` varchar(255) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `gender` varchar(10) NOT NULL,
  PRIMARY KEY (`patient_id`),
  UNIQUE KEY `UKrp7dtdql56dgru73meoqq2qdt` (`patient_email`),
  UNIQUE KEY `UKnescschbuvf1tkxflibqp0xol` (`patient_phone`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (2,'2024-10-16 15:54:31.079416','2002-10-14','sabari','ramesh','sabari@gmail.com','Sabari@123','8975642130','2024-10-16 15:54:31.079416','male'),(5,'2024-10-16 16:21:21.258061','1990-05-20','Ab','Villieres','ab.villieres@example.com','Strong@1234','9876544410','2024-10-16 16:21:21.258061','male'),(23,'2024-10-17 19:23:19.179496','1982-11-12','leena','kumari','leejai@gmail.com','Leejai@2910','9171130111','2024-11-29 21:36:46.280198','Female'),(27,'2024-10-17 19:34:38.341449','2002-06-14','sam','jerome','sam@gmail.com','SamJe@123','8769251430','2024-10-17 19:34:38.341449','male'),(80,'2024-11-22 20:56:44.669983','1994-03-13','Siraj','Ahmed','Siraj@gmail.com','Siraj@123','6213549877','2024-11-22 20:56:44.669983','male'),(85,'2024-11-28 12:39:25.505021','1987-12-18','dhoni','MS','dhoni@gmail.com','Dhoni@1475','6541473695','2024-11-28 12:39:25.505021','Male'),(87,'2024-11-29 10:09:46.134821','2002-11-15','raghul','raja','ragul@gmail.com','Raghul@1235','6428791355','2024-11-29 10:09:46.134821','Male'),(96,'2024-11-30 10:14:13.055747','2002-01-01','Resika','arunachalam','Resika@gmail.com','Resi@159','9631234567','2024-11-30 10:23:38.268814','Female'),(98,'2024-11-30 11:10:50.884018','2004-02-02','lohit','suresh','lohit@gmail.com','Lohit@357','8549674358','2024-11-30 11:10:50.884018','Male'),(100,'2024-12-01 08:59:08.394423','1978-06-14','ravi','sundharam','Ravi@gmail.com','Ravi@789','7446335895','2024-12-01 08:59:08.394423','Male'),(109,'2024-12-01 11:12:27.720113','2003-05-21','hemasri','jaisa','Hema@gmail.com','Hema@1598','7200310933','2024-12-01 11:12:27.720113','Male'),(113,'2024-12-01 17:40:48.140998','2001-02-02','Prabanjan','ravi','prabha@gmail.com','Praba@147','9847890600','2024-12-01 17:40:48.140998','Male'),(136,'2024-12-06 11:53:27.763667','1985-02-05','Ronaldo','Santos','ronaldo12@gmail.com','Ronaldo@07','9184554321','2024-12-06 11:53:27.763667','male'),(137,'2024-12-06 19:06:15.511703','2002-11-15','Srimathi','Srinivasan','srima@gmail.ocm','Srima@1511','9962577559','2024-12-06 19:06:15.511703','Female');
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialization`
--

DROP TABLE IF EXISTS `specialization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specialization` (
  `specialization_id` int NOT NULL AUTO_INCREMENT,
  `specialization` varchar(255) NOT NULL,
  PRIMARY KEY (`specialization_id`),
  UNIQUE KEY `UK2c30b1a6u0uy5g19hbush0bxo` (`specialization`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialization`
--

LOCK TABLES `specialization` WRITE;
/*!40000 ALTER TABLE `specialization` DISABLE KEYS */;
INSERT INTO `specialization` VALUES (3,'Cardio'),(2,'Dentist'),(1,'General'),(4,'Neurology'),(5,'Pediatrics');
/*!40000 ALTER TABLE `specialization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `state` (
  `state_id` int NOT NULL AUTO_INCREMENT,
  `state` varchar(255) NOT NULL,
  PRIMARY KEY (`state_id`),
  UNIQUE KEY `UKripdjupjrduxaforlqnyge42c` (`state`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` VALUES (5,'Arizona'),(3,'Illinois'),(2,'Kerala'),(1,'Tamil Nadu'),(4,'Texas');
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-13 16:14:20
