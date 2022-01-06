-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: clickbyte
-- ------------------------------------------------------
-- Server version	5.7.30-log

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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorias` (
  `IdCategoria` tinyint(4) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `Imagen` varchar(30) COLLATE utf8_spanish_ci DEFAULT 'default.jpg',
  PRIMARY KEY (`IdCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Placas Base','default.jpg'),(2,'Procesadores','default.jpg'),(3,'Discos Duros','default.jpg'),(4,'Intel Placas','default.jpg'),(5,'Amd Placas','default.jpg'),(7,'Memoria Ram','10905.jpg'),(9,'Gráficas','44415-tarjetasgraficas.jpg'),(11,'Sata','14259455771489321.jpg'),(13,'Ssd','14259455894536146.jpg'),(14,'Externos','default.jpg'),(18,'NVidia','14259458625293893.jpg'),(19,'Intel Socket 1150','14259444204055460.jpg'),(20,'Amd Socket AM3','14259449062732937.jpg'),(21,'Ddr3','default.jpg'),(22,'Ddr4','default.jpg'),(23,'Otros','default.jpg'),(24,'Cajas','default.jpg'),(25,'Fuentes','default.jpg'),(26,'Perifericos','default.jpg'),(27,'Servicios','default.jpg'),(28,'Sobremesa','default.jpg'),(29,'Amd','14259458772039704.jpg'),(30,'Portatiles','default.jpg');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-05 21:08:24
