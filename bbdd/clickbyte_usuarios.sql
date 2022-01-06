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
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `IdUsuario` smallint(6) NOT NULL AUTO_INCREMENT,
  `Email` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `Password` varchar(100) COLLATE utf8_spanish_ci NOT NULL,
  `Nombre` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `Apellidos` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `NIF` char(9) COLLATE utf8_spanish_ci NOT NULL,
  `Telefono` char(9) COLLATE utf8_spanish_ci DEFAULT NULL,
  `Direccion` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `CodigoPostal` char(5) COLLATE utf8_spanish_ci DEFAULT NULL,
  `Localidad` varchar(40) COLLATE utf8_spanish_ci NOT NULL,
  `Provincia` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `UltimoAcceso` datetime DEFAULT NULL,
  `Avatar` varchar(30) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`IdUsuario`),
  UNIQUE KEY `uc_email` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'manuelguiga@gmail.com','1','Manuel','Guillén Gallardo','11111111S',NULL,'Calle Ya me queda menos','06200','Almendralejo','Badajoz',NULL,NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-05 21:08:23
