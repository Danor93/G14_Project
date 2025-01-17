-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: bitemedb
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
-- Table structure for table `buss_client`
--

DROP TABLE IF EXISTS `buss_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buss_client` (
  `ID` varchar(45) NOT NULL,
  `companyName` varchar(45) NOT NULL,
  `budget` varchar(45) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `id_fk1_idx` (`ID`),
  CONSTRAINT `id_fk1` FOREIGN KEY (`ID`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buss_client`
--

LOCK TABLES `buss_client` WRITE;
/*!40000 ALTER TABLE `buss_client` DISABLE KEYS */;
INSERT INTO `buss_client` VALUES ('3115467','Microsoft','20','Approved'),('5465478','Microsoft','80','Approved'),('6547898','Intel','0.0','Approved');
/*!40000 ALTER TABLE `buss_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `client_id` varchar(45) NOT NULL,
  `w4c_private` varchar(45) NOT NULL,
  `status` varchar(45) NOT NULL,
  `CreditCardNumber` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`client_id`),
  KEY `userId` (`client_id`),
  CONSTRAINT `userId_fk` FOREIGN KEY (`client_id`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES ('3115467','P193','Active','546547'),('3115478','P593','Active','5654567'),('32266447','P157','Active','65656478'),('3564578','P61','Active','5546546'),('5465478','P765','Active','532312154'),('6547898','P706','Freeze','987787'),('7894511','P555','Active','545647');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `company` (
  `w4cBusiness` varchar(45) NOT NULL,
  `companyName` varchar(45) NOT NULL,
  `companyStatus` varchar(45) NOT NULL,
  PRIMARY KEY (`w4cBusiness`,`companyName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES ('B11','Microsoft','Approved'),('B22','Amazon','Waiting'),('B34','Elbit','Approved'),('B54','Google','Approved'),('B58','Facebook','Waiting'),('B66','Refael','Approved'),('B74','Nivdia','Not approved'),('B76','Amdocs','Not Approved'),('B87','Intel','Approved');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery`
--

DROP TABLE IF EXISTS `delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery` (
  `orderNum` int NOT NULL,
  `deliveryType` varchar(45) DEFAULT NULL,
  `participantsNum` varchar(45) NOT NULL DEFAULT '1',
  `address` varchar(45) DEFAULT NULL,
  `phone` varchar(45) NOT NULL,
  `recipient` varchar(45) NOT NULL,
  `deliPrice` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orderNum`),
  CONSTRAINT `orderN_fk` FOREIGN KEY (`orderNum`) REFERENCES `order` (`orderNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery`
--

LOCK TABLES `delivery` WRITE;
/*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
INSERT INTO `delivery` VALUES (27,'Regular','1','haborsh','0502660865','Danor','40.0'),(28,'Regular','1','haifa','0502660865','danor','25.0'),(29,'Regular','1','habrosh 13','0528969700','noy','25.0'),(30,'Regular','1','haborsh','0502660865','danor','25.0'),(31,'Regular','1','haborsh','0502660865','danor','25.0'),(33,'Regular','2','ben zvi','0506647856','daniel','40.0'),(35,'Regular','2','a','0502664789','daniel','40.0'),(42,'Shared','3','a','0504773435','as','45.0'),(43,'Shared','3','a','0506774545','a','45.0'),(44,'Regular','1','a','0504553434','a','25.0'),(47,'Regular','1','a','0503706494','a','25.0'),(72,'Regular','1','habrosh','0543261022','Talia','25.0'),(74,'Regular','1','habrosh','0543261022','Talia','25.0'),(76,'Regular','1','habrosh','0543261022','Talia','25.0'),(78,'Regular','1','habrosh','0543261022','Talia','25.0'),(80,'Regular','1','habrosh','0543261022','Talia','25.0'),(82,'Regular','1','habrosh','0543261022','Talia','25.0'),(84,'Regular','1','habrosh','0543261022','Talia','25.0'),(87,'Regular','1','habrosh','0543261022','Talia','25.0'),(90,'Regular','1','habrosh','0543261022','Talia','25.0'),(93,'Regular','1','habrosh','0543261022','Talia','25.0'),(95,'Regular','1','habrosh','0543261022','Talia','25.0'),(98,'Regular','1','habrosh','0543261022','Talia','25.0'),(101,'Regular','1','habrosh','0543261022','Talia','25.0'),(104,'Regular','1','habrosh','0543261022','Talia','25.0');
/*!40000 ALTER TABLE `delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dishes`
--

DROP TABLE IF EXISTS `dishes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dishes` (
  `dishName` varchar(45) NOT NULL,
  `dishType` varchar(45) NOT NULL,
  `restId1` varchar(45) NOT NULL,
  `supplierName` varchar(45) NOT NULL,
  `price` float NOT NULL,
  `choiceFactor` varchar(45) DEFAULT NULL,
  `choiceDetails` varchar(45) DEFAULT NULL,
  `ingredients` varchar(200) DEFAULT NULL,
  `extra` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`dishName`,`restId1`),
  KEY `restId` (`restId1`),
  CONSTRAINT `restID_fkk` FOREIGN KEY (`restId1`) REFERENCES `supplier` (`restId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dishes`
--

LOCK TABLES `dishes` WRITE;
/*!40000 ALTER TABLE `dishes` DISABLE KEYS */;
INSERT INTO `dishes` VALUES ('','Main dish','200564879','mcdonalds',49.99,'Size','M/L','cucumber,lettuce,pickles','no cucumber/no lettuce/no pickles'),('cheese cake','Dessert','3115645','vivino',6,'with','Strawberries/Blueberries','cheese,cramble','no cramble'),('coca cola','Drink','200564879','mcdonalds',9.99,'type','Zero/Diet/Regular','coca cola',''),('crispy salad','Salad','200564879','mcdonalds',39.99,'Size','S/M/L','Crispy Chicken,Cucamber,Tomato,Onion,','no Onion/extra Crispy Chicken/'),('greek salad','Salad','3115645','vivino',7.5,'',NULL,NULL,NULL),('home salad','Salad','3115645','vivino',6,'','','cucumber,tomato,olives,cheese',''),('ice cream','Dessert','200564879','mcdonalds',4.99,'Favor','Chooclate/Vanil','ice cream',''),('Mc Royal','Main dish','200564879','mcdonalds',59.99,'size','s/m','tomato,lectuce,cucmber,','chesse'),('nuggets','Starter','200564879','mcdonalds',29.99,'Size','s/m/l','nuggets','royal sauce/barbecue sauce'),('ravioli','Main dish','3115645','vivino',14.99,'Size','S/M/L','tomato,cheese,sauce',''),('shnizel','Main dish','200564879','mcdonalds',39.99,'','','potatos,','no tomatos/extra cheese'),('sushi','Main dish','2056478','japanika',69.99,'','','check','');
/*!40000 ALTER TABLE `dishes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dishesinorder`
--

DROP TABLE IF EXISTS `dishesinorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dishesinorder` (
  `dishId` int NOT NULL AUTO_INCREMENT,
  `orderNum` int NOT NULL,
  `dishName` varchar(45) NOT NULL,
  `restID` varchar(45) NOT NULL,
  `dishType` varchar(45) NOT NULL,
  `choiceFactor` varchar(45) DEFAULT NULL,
  `choiceDetails` varchar(45) DEFAULT NULL,
  `extra` varchar(45) DEFAULT NULL,
  `quentity` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`dishId`,`orderNum`,`dishName`),
  KEY `orderNumber` (`orderNum`),
  CONSTRAINT `orderNumber_fk` FOREIGN KEY (`orderNum`) REFERENCES `order` (`orderNumber`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dishesinorder`
--

LOCK TABLES `dishesinorder` WRITE;
/*!40000 ALTER TABLE `dishesinorder` DISABLE KEYS */;
INSERT INTO `dishesinorder` VALUES (1,15,'cheese cake','1','Dessert',NULL,NULL,NULL,3),(2,16,'home salad','1','Salad',NULL,NULL,NULL,1),(3,17,'greek salad','1','Salad',NULL,NULL,NULL,1),(8,15,'Shnitzel','2','Main dish',NULL,NULL,NULL,1),(9,19,'greek salad','1','Salad','size','M','',2),(10,20,'greek salad','1','Salad','size','M','',2),(11,21,'greek salad','1','Dessert','size','M','',2),(12,22,'greek salad','1','Main dish','size','M','',2),(13,23,'greek salad','1','Salad','size','M','',2),(14,24,'greek salad','1','Main dish','size','M','',2),(15,25,'greek salad','1','Dessert','size','M','',2),(16,26,'greek salad','1','Main dish','size','M','',2),(18,27,'greek salad','1','Salad','size','S','',1),(19,28,'crispy chicken','200564879','Main dish','Size','M','no cucumber ',1),(20,28,'coca cola','200564879','Drink','type','Zero','',1),(21,29,'Mc Royal','200564879','Main dish','size','m','',1),(22,29,'coca cola','200564879','Drink','type','Zero','',1),(23,29,'ice cream','200564879','Dessert','Favor','Vanil','',1),(24,30,'crispy chicken','200564879','Main dish','Size','M','',1),(25,31,'crispy chicken','200564879','Main dish','Size','M','no cucumber no pickles ',1),(26,31,'nuggets','200564879','Starter','Size','l','royal sauce ',1),(27,31,'ice cream','200564879','Dessert','Favor','Chooclate','',1),(28,32,'Mc Royal','200564879','Main dish','size','s','chesse ',1),(29,33,'Mc Royal','200564879','Main dish','size','s','chesse ',1),(30,34,'ice cream','200564879','Dessert','Favor','Chooclate','',1),(31,35,'crispy chicken','200564879','Main dish','Size','M','',1),(32,36,'ice cream','200564879','Dessert','Favor','Chooclate','',1),(33,37,'crispy salad','200564879','Salad','Size','M','',1),(34,37,'nuggets','200564879','Starter','Size','l','royal sauce barbecue sauce ',1),(35,38,'Mc Royal','200564879','Main dish','size','s','',1),(36,39,'shnizel','200564879','Main dish','',NULL,'',1),(37,40,'Mc Royal','200564879','Main dish','size','m','',1),(38,41,'ravioli','3115645','Main dish','Size','M','',1),(39,42,'ravioli','3115645','Main dish','Size','L','',1),(40,43,'ravioli','3115645','Main dish','Size','M','',1),(41,44,'crispy salad','200564879','Salad','Size','L','',1),(42,45,'shnizel','200564879','Main dish','',NULL,'no tomatos ',1),(43,46,'Mc Royal','200564879','Main dish','size','m','chesse ',1),(44,46,'nuggets','200564879','Starter','Size','m','royal sauce ',1),(45,47,'crispy chicken','200564879','Main dish','Size','L','',1),(46,47,'ice cream','200564879','Dessert','Favor','Chooclate','',1),(47,48,'crispy chicken','200564879','Main dish','Size','M','no cucumber ',1),(48,48,'nuggets','200564879','Starter','Size','s','barbecue sauce ',1),(49,49,'crispy chicken','200564879','Main dish','Size','M','no cucumber ',1),(50,50,'shnizel','200564879','Main dish','',NULL,'no tomatos ',1),(51,51,'Mc Royal','200564879','Main dish','size','m','',1);
/*!40000 ALTER TABLE `dishesinorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `import_users`
--

DROP TABLE IF EXISTS `import_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `import_users` (
  `userName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `id` varchar(45) NOT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`,`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `import_users`
--

LOCK TABLES `import_users` WRITE;
/*!40000 ALTER TABLE `import_users` DISABLE KEYS */;
INSERT INTO `import_users` VALUES ('h','h','Avi','Sofer','1211','avi@intel.com','052645879','HR-Intel'),('mc1','mc1','Ahmed','Hamodi','200564879','Ahmed@mcdonalds.com','050264782','Certified-mcdonalds'),('mc2','mc2','Mosa','Srour','20445879','Mosa@mcdonalds.com','054878664','Approved-mcdonalds'),('jp1','jp1','Lital','Smaja','2056478','lital@japanika.com','050264456','Certified-japanika'),('jp2','jp2','Itizik','Regev','2056699','itizik@japanika.com','052554678','Approved-japanika'),('rs','rs','Rina','Sinai','3111554','rina@refael.com','050266086',NULL),('r7','r7','Cristiano','Ronaldo','31122334','cristiano@gmail.com','052669977',NULL),('g1','g1','Ilya','Zeldner','31145678','ilya@greg.com','052647897','Certified-greg'),('b','b','Talia','Blum','3115467','talia@gmail.com','052648974',NULL),('dani','dani','Dani','Moyal','3115478','dani@gmail.com','052647897',NULL),('viv1','viv1','Ron','Abu','3115645','Ron@vivino.com','052645789','Certified-vivino'),('a','a','Adi','Sasson','3122478','adi@gmail.com','052648765',NULL),('ns','ns','Noy','Sinai','32124564','noy@gmail.com','0528969700',NULL),('k7','k7','Kiliyan','Mbappe','32266447','kiliyan@google.com','052447889',NULL),('ref2','ref2','Egal','Sinai','3245678','egal@refaelo.com','054678923','Certified-refaelo'),('m10','m10','Leo','Messi','3564578','messi@gmail.com','052669879',NULL),('g2','g2','Ilana','Klieman','36478941','ilana@greg.com','054655782','Approved-greg'),('ref1','ref1','Moshe','Moalem','45789','moshe@refaelo.com','056487923','Approved-refaelo'),('check','check','check','check','5465478','check@microsoft.com','0502660865',NULL),('tal','tal','Tal','Levy','5647789','tal@Elbit.com','052478968',NULL),('lior','lior','Lior','Refalov','5647889','lior@microsoft.com','050478964',NULL),('daniel','daniel','Daniel','Peretz','6547898','daniel@intel.com','050145647',NULL),('viv2','viv2','Adi','Blum','655489','adi@vivino.com','052469112','Approved-vivino'),('e','e','Lior','Shauli','689','lior@biteme.com','054693141','CEO'),('matan','matan','Matan','Cohen','7894562','matan@gmail.com','050647123',NULL);
/*!40000 ALTER TABLE `import_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `orderNumber` int NOT NULL AUTO_INCREMENT,
  `orderType` varchar(45) DEFAULT NULL,
  `restName` varchar(45) DEFAULT NULL,
  `rstID` varchar(45) NOT NULL,
  `totalPrice` varchar(45) DEFAULT NULL,
  `timeOfOrder` varchar(45) DEFAULT NULL,
  `dateOfOrder` varchar(45) DEFAULT NULL,
  `orderStatus` varchar(45) DEFAULT NULL,
  `costumerID` varchar(45) NOT NULL,
  `usedRefund` varchar(45) NOT NULL DEFAULT '0',
  `usedBudget` int NOT NULL DEFAULT '0',
  `EarlyOrder` varchar(45) NOT NULL,
  `timeApproved` varchar(45) DEFAULT NULL,
  `punctuality` int DEFAULT NULL,
  `timeSended` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orderNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (15,'Take Away','vivino','1','18','10:35','2021-12-23','Sended','134','0',0,'no','19:47:06.739913',NULL,'19:47:43.040882900'),(16,'Take Away','vivino','1','7.5','11:00','2021-12-23','Sended','134','0',0,'no','19:47:11.259438100',NULL,'19:47:46.249478500'),(17,'Take Away','vivino','1','3.5','11:01','2021-12-24','Sended','134','0',0,'no','19:47:13.353014100',NULL,'19:47:49.354182900'),(18,'Take Away ','refaelo','2','25.3','10:30','2021-12-25','Waiting','134','0',0,'no',NULL,NULL,NULL),(19,'Regular','vivino','1','32.0','19:20','2021-12-27','Sended','134','0',0,'no','19:47:15.683945500',NULL,'19:47:56.641788'),(20,'Regular','vivino','1','32.0','19:20','2021-12-27','Sended','134','0',0,'no','19:47:17.549499100',NULL,'19:47:36.327314500'),(21,'Regular','vivino','1','32.0','19:20','2021-12-27','Waiting','134','0',0,'no',NULL,NULL,NULL),(22,'Regular','vivino','1','32.0','19:20','2021-12-27','Waiting','134','0',0,'no',NULL,NULL,NULL),(23,'Regular','vivino','1','32.0','19:20','2021-12-27','Waiting','134','0',0,'no',NULL,NULL,NULL),(24,'Regular','vivino','1','32.0','19:20','2021-12-27','Waiting','134','0',0,'no',NULL,NULL,NULL),(25,'Regular','vivino','1','32.0','19:20','2021-12-27','Waiting','134','0',0,'no',NULL,NULL,NULL),(26,'Regular','vivino','1','32.0','19:20','2021-12-27','Waiting','134','0',0,'no',NULL,NULL,NULL),(27,'Regular','vivino','1','-26.5','17:00','2022-01-02','Approved','134','0',0,'yes','19:50',0,NULL),(28,'Regular','mcdonalds','200564879','76.482','12:00','2022-01-10','Approved','3564578','0',0,'yes','12:45:05.418126300',0,NULL),(29,'Regular','mcdonalds','200564879','99.97','10:00','2022-01-03','Sended','32124564','0',0,'no','12:49:26.447381300',0,'12:49:36.357620400'),(30,'Regular','mcdonalds','200564879','67.491005','15:00','2022-01-07','Waiting','3564578','0',0,'yes',NULL,0,NULL),(31,'Regular','mcdonalds','200564879','98.973','15:00','2022-01-06','Waiting','3115467','0',0,'yes',NULL,0,NULL),(32,'Take Away','mcdonalds','200564879','53.991','15:00','2022-01-05','Waiting','6547898','0',0,'yes',NULL,0,NULL),(33,'Regular','mcdonalds','200564879','71.491005','10:30','2022-01-18','Approved','6547898','0',1,'yes','12:45:24.829800500',0,NULL),(34,'Shared-33','mcdonalds','200564879','22.491','10:30','2022-01-18','Approved','6547898','0',0,'yes','14:46:25.564230600',0,NULL),(35,'Regular','mcdonalds','200564879','69.990005','12:55','2022-01-04','Done','6547898','0',0,'no','12:52:09.237095100',0,'12:54:12.607518800'),(36,'Shared-35','mcdonalds','200564879','24.99','12:55','2022-01-04','Done','6547898','0',0,'no','12:54:01.156734300',0,'12:54:20.062078'),(37,'Take Away','mcdonalds','200564879','69.98','15:00','2022-01-04','Done','3122478','0',0,'no','14:44:04.512568900',0,'14:44:22.668258300'),(38,'Take Away','mcdonalds','200564879','59.99','15:00','2022-01-04','Sended','3122478','0',0,'no','14:47:33.961767300',0,'14:47:38.829899400'),(39,'Take Away','mcdonalds','200564879','39.99','15:00','2022-01-04','Done','c','0',0,'no','14:52:07.579541200',1,'14:52:11.313666700'),(40,'Take Away','mcdonalds','200564879','59.99','15:30','2022-01-04','Done','c','0',0,'no','15:02:23.554041900',1,'15:02:54.366565800'),(41,'Take Away','vivino','3115645','14.99','15:30','2022-01-04','Approved','c','0',0,'no','15:28:30.095597300',1,'15:18:08.340410500'),(42,'Take Away','vivino','3115645','14.99','15:40','2022-01-04','Waiting','c','0',0,'no','15:24:50.100107600',0,'15:25:52.742306300'),(43,'Take Away','vivino','3115645','14.99','15:40','2022-01-04','Done','c','0',0,'no','15:31:34.567887500',0,'15:31:37.012007900'),(44,'Regular','mcdonalds','200564879','39.99','19:00','2022-01-04','Waiting','c','0',0,'no',NULL,0,NULL),(45,'Take Away','vivino','3115645','13.491','12:00','2022-01-06','Waiting','3115467','0',0,'yes',NULL,0,NULL),(46,'Take Away','vivino','3115645','21.6','12:00','2022-01-06','Waiting','3115467','0',0,'yes',NULL,0,NULL),(47,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(48,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(49,'Take Away','vivino','3115645','10.8','12:00','2022-01-20','Waiting','3115467','0',0,'yes',NULL,0,NULL),(50,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(51,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(52,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(53,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(54,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(55,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(56,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(57,'Take Away','vivino','3115645','5.4','12:00','2022-01-13','Waiting','3115467','0',0,'yes',NULL,0,NULL),(58,'Take Away','vivino','3115645','5.4','12:00','2022-01-14','Waiting','3115467','0',0,'yes',NULL,0,NULL),(59,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(60,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(61,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(62,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(63,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(64,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(65,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(66,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(67,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(68,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(69,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(70,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(71,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(72,'Regular','vivino','3115645','33.3','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(73,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(74,'Regular','vivino','3115645','33.3','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(75,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(76,'Regular','vivino','3115645','33.3','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(77,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(78,'Regular','vivino','3115645','33.3','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(79,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(80,'Regular','vivino','3115645','33.3','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(81,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(82,'Regular','vivino','3115645','33.3','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(83,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(84,'Regular','vivino','3115645','33.3','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(85,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(86,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(87,'Regular','vivino','3115645','33.3','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(88,'Shared-27','vivino','3115645','23.8','17:00','2022-01-02','Waiting','3115467','0',0,'yes',NULL,0,NULL),(89,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',1,'yes',NULL,0,NULL),(90,'Regular','vivino','3115645','33.3','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(91,'Shared-27','vivino','3115645','24.3','17:00','2022-01-02','Waiting','3115467','0',0,'yes',NULL,0,NULL),(92,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',1,'yes',NULL,0,NULL),(93,'Regular','vivino','3115645','33.3','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(94,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',1,'yes',NULL,0,NULL),(95,'Regular','vivino','3115645','33.3','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(96,'Shared-27','vivino','3115645','24.3','17:00','2022-01-02','Waiting','3115467','0',0,'yes',NULL,0,NULL),(97,'Take Away','vivino','3115645','10.8','12:00','2022-03-18','Waiting','3115467','0',1,'yes',NULL,0,NULL),(98,'Regular','vivino','3115645','33.3','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(99,'Shared-27','vivino','3115645','28.8','17:00','2022-01-02','Waiting','3115467','0',0,'yes',NULL,0,NULL),(100,'Take Away','vivino','3115645','12.0','12:00','2022-03-18','Waiting','3115467','0',1,'yes',NULL,0,NULL),(101,'Regular','vivino','3115645','12.0','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(102,'Shared-27','vivino','3115645','24.8','17:00','2022-01-02','Waiting','3115467','4',0,'yes',NULL,0,NULL),(103,'Take Away','vivino','3115645','12.0','12:00','2022-03-18','Waiting','3115467','0',1,'yes',NULL,0,NULL),(104,'Regular','vivino','3115645','12.0','12:00','2022-03-18','Waiting','3115467','0',0,'yes',NULL,0,NULL),(105,'Shared-27','vivino','3115645','24.8','17:00','2022-01-02','Waiting','3115467','4',0,'yes',NULL,0,NULL);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_report`
--

DROP TABLE IF EXISTS `orders_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_report` (
  `month` varchar(45) NOT NULL,
  `year` varchar(45) NOT NULL,
  `ResName` varchar(45) NOT NULL,
  `DishType` varchar(45) NOT NULL,
  `branch` varchar(45) NOT NULL,
  `Quantity` int NOT NULL,
  PRIMARY KEY (`month`,`year`,`ResName`,`DishType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_report`
--

LOCK TABLES `orders_report` WRITE;
/*!40000 ALTER TABLE `orders_report` DISABLE KEYS */;
INSERT INTO `orders_report` VALUES ('01','2022','mcdonalds','Dessert','north',1),('01','2022','mcdonalds','Main dish','north',4),('01','2022','vivino','Main dish','north',3),('12','2021','vivino','Dessert','north',7),('12','2021','vivino','Main dish','north',7),('12','2021','vivino','Salad','north',8);
/*!40000 ALTER TABLE `orders_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performance_reports`
--

DROP TABLE IF EXISTS `performance_reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `performance_reports` (
  `restaurant` varchar(45) NOT NULL,
  `year` varchar(45) NOT NULL,
  `month` varchar(45) NOT NULL,
  `branch` varchar(45) DEFAULT NULL,
  `total_orders` int DEFAULT '0',
  `onTime` int DEFAULT '0',
  `areLate` int DEFAULT '0',
  PRIMARY KEY (`restaurant`,`year`,`month`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performance_reports`
--

LOCK TABLES `performance_reports` WRITE;
/*!40000 ALTER TABLE `performance_reports` DISABLE KEYS */;
INSERT INTO `performance_reports` VALUES ('mcdonalds','2022','01','north',9,7,2),('vivino','2021','12','north',3,2,1),('vivino','2022','01','North',1,0,1);
/*!40000 ALTER TABLE `performance_reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refund`
--

DROP TABLE IF EXISTS `refund`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refund` (
  `ID` varchar(45) NOT NULL,
  `ammount` varchar(45) DEFAULT NULL,
  `restId` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`,`restId`),
  CONSTRAINT `id_fk` FOREIGN KEY (`ID`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refund`
--

LOCK TABLES `refund` WRITE;
/*!40000 ALTER TABLE `refund` DISABLE KEYS */;
INSERT INTO `refund` VALUES ('3115467','4','3115645'),('c','200','200564879'),('c','7.495','3115645');
/*!40000 ALTER TABLE `refund` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reports` (
  `quarter` varchar(50) NOT NULL,
  `year` varchar(45) NOT NULL,
  `date_added` varchar(45) NOT NULL,
  `file_name` varchar(50) NOT NULL,
  `upload_file` longblob NOT NULL,
  `homebranch` varchar(45) NOT NULL,
  PRIMARY KEY (`quarter`,`date_added`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revenue_reports`
--

DROP TABLE IF EXISTS `revenue_reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revenue_reports` (
  `branch` varchar(45) NOT NULL,
  `month` varchar(45) NOT NULL,
  `year` varchar(45) NOT NULL,
  `resturant` varchar(45) NOT NULL,
  `orders_amount` int unsigned DEFAULT NULL,
  `Income` float unsigned DEFAULT NULL,
  `Quarterly` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`month`,`year`,`resturant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revenue_reports`
--

LOCK TABLES `revenue_reports` WRITE;
/*!40000 ALTER TABLE `revenue_reports` DISABLE KEYS */;
INSERT INTO `revenue_reports` VALUES ('north','01','2022','mcdonalds',4,247.451,'1'),('north','01','2022','vivino',1,14.99,'1'),('north','12','2021','vivino',11,285,'4');
/*!40000 ALTER TABLE `revenue_reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `restId` varchar(45) NOT NULL,
  `supplierName` varchar(45) NOT NULL,
  `openingTime` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `supplierStatus` varchar(45) DEFAULT NULL,
  `homeBranch` varchar(45) DEFAULT NULL,
  `Confirm_Employee` varchar(45) NOT NULL,
  PRIMARY KEY (`restId`,`supplierName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES ('200564879','mcdonalds','10:00-23:00','kiryat haim','kiryat haim','Approved','North','20445879'),('2056478','japanika','12:00-23:00','kiryat ata','kiryat ata','Approved','North','2056699'),('3115645','vivino','12:00-22:00','haifa','haifa','Approved','North','655489'),('3245678','refaelo','12:00-22:00','haifa','haifa','Approved','North','45789');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userName` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `Role` varchar(45) DEFAULT NULL,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  `ID` varchar(45) NOT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `isLoggedIn` int DEFAULT '0',
  `homeBranch` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `homeBranch` (`homeBranch`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('adi','a1','Customer','Tali','Or','111','Tali@gmail.com','050344',0,'north'),('h','h','HR-Intel','Avi','Sofer','1211','avi@intel.coml','052121',0,'north'),('ceo3','ceo3','CEO','Zlatan','Ibrahimović','1278946','zlatan@biteme.com','05256431',1,'south'),('mc1','mc1','Supplier-Certified-mcdonalds','Ahmed','Hamodi','200564879','Ahmed@mcdonalds.com','0502647822',0,'North'),('mc2','mc2','Supplier-Approved-mcdonalds','Mosa','Srour','20445879','Mosa@mcdonalds.com','054878664',0,'North'),('jp1','jp1','Supplier-Certified-japanika','Lital','Smaja','2056478','lital@japanika.com','050264456',0,'North'),('jp2','jp2','Supplier-Approved-japanika','Itizik','Regev','2056699','itizik@japanika.com','052554678',0,'North'),('f','f','BranchManager','Danor','Sinai','31115566','danor@gmail.com','050264789',0,'center'),('b','b','Customer','Talia','Blum','3115467','talia@gmail.com','05264897',0,'Center'),('dani','dani','Customer','Dani','Moyal','3115478','dani@gmail.com','052647897',0,'North'),('viv1','viv1','Supplier-Certified-vivino','Ron','Abu','3115645','Ron@vivino.com','052645789',0,'North'),('ns','ns','Customer','Noy','Sinai','32124564','noy@gmail.com','0528969700',0,'north'),('ref2','ref2','Supplier-Certified-refaelo','Egal','Sinai','3245678','egal@refaelo.com','0546789',0,'North'),('m10','m10','Customer','Leo','Messi','3564578','messi@gmail.com','05266987',0,'North'),('c','c','BranchManager','Sahar','Oz','456','sahar@biteme.com','054678',0,'north'),('s','s','BranchManager','Tiran','Hesawi','456789','tiran@gmail.com','05678978',0,'south'),('ref1','ref1','Supplier-Approved-refaelo','Moshe','Moalem','45789','moshe@refaelo.com','0564879',0,'North'),('h2','h2','HR-Microsoft','Dudu','Aharon','5612378','dudu@microsoft.com','0556789',0,'north'),('daniel','daniel','Customer','Daniel','Peretz','6547898','daniel@intel.com','050145647',0,'North'),('viv2','viv2','Supplier-Approved-vivino','Adi','Blum','655489','adi@vivino.com','052469112',0,'North'),('e','e','CEO','Lior','Shauli','689','lior@biteme.coml','054789',0,'north'),('ns','ns','Customer','Noy','Sinai','7894511','noy@gmail.com','0526989',0,'north'),('ceo2','ceo2','CEO','Ruslana','Rodina','856479','ruslana@bitme.com','05089371',0,'center'),('h1','h1','HR-Elbit','Gal','Levy','98754612','gal@elbit.com','05246237',0,'north'),('a','a','Customer','Adi','Sasson','c','adi@gmail.com','0526487',0,'north');
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

-- Dump completed on 2022-01-06 20:44:53
