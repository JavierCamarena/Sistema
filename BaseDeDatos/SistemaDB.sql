﻿CREATE DATABASE  IF NOT EXISTS `sistemamonitoreo` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sistemamonitoreo`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: sistemamonitoreo
-- ------------------------------------------------------
-- Server version	5.7.12-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `aspiranteresponsable`
--

DROP TABLE IF EXISTS `aspiranteresponsable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aspiranteresponsable` (
  `idaspirante` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Apellido` varchar(45) DEFAULT NULL,
  `Calle` varchar(45) DEFAULT NULL,
  `Numero` varchar(45) DEFAULT NULL,
  `Colonia` varchar(200) DEFAULT NULL,
  `Agencia` varchar(45) DEFAULT NULL,
  `Seccion` varchar(45) DEFAULT NULL,
  `SenasParticulares` varchar(100) DEFAULT NULL,
  `Telefono` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Barda` tinyint(1) DEFAULT NULL,
  `Lona` tinyint(1) DEFAULT NULL,
  `Banderin` tinyint(1) DEFAULT NULL,
  `Reunion` tinyint(1) DEFAULT NULL,
  `Gestion` tinyint(1) DEFAULT NULL,
  `PSocial` varchar(500) DEFAULT NULL,
  `PInfraestructura` varchar(500) DEFAULT NULL,
  `FechaReunion` datetime DEFAULT NULL,
  `Observaciones` varchar(500) DEFAULT NULL,
  `Clave` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idaspirante`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ciudadanos`
--

DROP TABLE IF EXISTS `ciudadanos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ciudadanos` (
  `idciudadanos` int(11) NOT NULL AUTO_INCREMENT,
  `Nombres` varchar(45) DEFAULT NULL,
  `Apellidos` varchar(45) DEFAULT NULL,
  `ClaveIne` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Telefono` varchar(45) DEFAULT NULL,
  `Voto` tinyint(1) DEFAULT NULL,
  `FolioPadron` varchar(45) DEFAULT NULL,
  `Seccion` varchar(45) DEFAULT NULL,
  `Colonia` varchar(200) DEFAULT NULL,
  `Casilla` int(11) DEFAULT NULL,	
  `idResponsable` int(11) DEFAULT NULL,
  PRIMARY KEY (`idciudadanos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `colonias`
--

DROP TABLE IF EXISTS `colonias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `colonias` (
  `idcolonias` int(11) NOT NULL AUTO_INCREMENT,
  `Tipo` varchar(45) DEFAULT NULL,
  `Nombre` varchar(45) DEFAULT NULL,
  `Clave` varchar(45) DEFAULT NULL,
  `ClaveCp` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcolonias`)
) ENGINE=InnoDB AUTO_INCREMENT=283 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `responsables`
--

DROP TABLE IF EXISTS `responsables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `responsables` (
  `idResponsables` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) DEFAULT NULL,
  `Apellido` varchar(45) DEFAULT NULL,
  `ClaveElectorIne` varchar(45) DEFAULT NULL,
  `NumTelefono` varchar(45) DEFAULT NULL,
  `Seccion` varchar(45) DEFAULT NULL,
  `Colonia` varchar(200) DEFAULT NULL,
  `idSuperior` int(11) DEFAULT NULL,
  `Cargo` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idResponsables`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `colonias` WRITE;
/*!40000 ALTER TABLE `colonias` DISABLE KEYS */;
INSERT INTO `colonias` VALUES (1,'COLONIA','ADOLFO LÓPEZ MATEOS','2006700010001','200670001000168030'),(2,'FRACCIONAMIENTO','LOS ÁLAMOS INFONAVIT','2006700010002','200670001000268143'),(3,'FRACCIONAMIENTO','LOS ÁLAMOS IVO','2006700010003','200670001000368143'),(4,'COLONIA','AMÉRICA NORTE','2006700010004','200670001000468050'),(5,'COLONIA','AMÉRICA SUR','2006700010005','200670001000568104'),(6,'COLONIA','ARBOLEDA','2006700010008','200670001000868090'),(7,'COLONIA','EL ARENAL MONTOYA','2006700010009','200670001000968126'),(8,'COLONIA','EL ARENAL 5 SEÑORES','2006700010010','200670001001068126'),(9,'FRACCIONAMIENTO','EL ARROYO','2006700010011','200670001001168145'),(10,'COLONIA','ARTÍCULO 123 ORIENTE','2006700010012','200670001001268090'),(11,'COLONIA','ARTÍCULO 123 PONIENTE','2006700010013','200670001001368103'),(12,'COLONIA','AURORA','2006700010014','200670001001468045'),(13,'FRACCIONAMIENTO','AURORA','2006700010015','200670001001568045'),(14,'COLONIA','LA AZUCENA','2006700010016','200670001001668003'),(15,'COLONIA','AZUCENAS','2006700010017','200670001001768070'),(16,'COLONIA','BENITO JUÁREZ','2006700010018','200670001001868103'),(17,'COLONIA','BUGAMBILIAS','2006700010019','200670001001968010'),(18,'FRACCIONAMIENTO','BUGAMBILIAS','2006700010020','200670001002068010'),(19,'COLONIA','CALIFORNIA','2006700010021','200670001002168150'),(20,'FRACCIONAMIENTO','LAS CAMPANAS','2006700010022','200670001002268157'),(21,'SIN TIPO','AGENCIA DE POLICÍA DE CANDIANI','2006700010023','200670001002368130'),(22,'COLONIA','CASA BLANCA','2006700010024','200670001002468140'),(23,'COLONIA','LA CASCADA','2006700010025','200670001002568040'),(24,'FRACCIONAMIENTO','LA CASCADA','2006700010026','200670001002668040'),(25,'FRACCIONAMIENTO','LOS CEDROS','2006700010027','200670001002768010'),(26,'COLONIA','EL CENTENARIO','2006700010028','200670001002868045'),(27,'SIN TIPO','CENTRAL DE ABASTO','2006700010029','200670001002968090'),(28,'SIN TIPO','CENTRO','2006700010030','200670001003068000'),(29,'SIN TIPO','CENTRO POR EL TECNOLOGICO','2006700010030','200670001003000000'),(30,'FRACCIONAMIENTO','EL CHOPO','2006700010031','200670001003168040'),(31,'COLONIA','CIENEGUITA','2006700010032','200670001003200000'),(32,'SIN TIPO','AGENCIA DE POLICÍA DE CINCO SEÑORES','2006700010033','200670001003368120'),(33,'FRACCIONAMIENTO','COLINAS DE LA SOLEDAD','2006700010034','200670001003468024'),(34,'UNIDAD HABITACIONAL','COLINAS DE MONTE ALBÁN','2006700010035','200670001003568159'),(35,'BARRIO','EL COQUITO','2006700010036','200670001003668153'),(36,'COLONIA','COSIJOEZA','2006700010037','200670001003768090'),(37,'COLONIA','CUAUHTÉMOC','2006700010038','200670001003868275'),(38,'BARRIO','LA CUEVITA','2006700010039','200670001003968153'),(39,'COLONIA','DAMNIFICADOS II','2006700010040','200670001004068020'),(40,'COLONIA','DÍAZ ORDAZ','2006700010041','200670001004168040'),(41,'SIN TIPO','AGENCIA DE POLICÍA DE DOLORES','2006700010043','200670001004368020'),(42,'COLONIA','DOLORES AMPLIACIÓN','2006700010044','200670001004468020'),(43,'SIN TIPO','AGENCIA MUNICIPAL DE DONAJÍ','2006700010045','200670001004568020'),(44,'COLONIA','EDUCACIÓN','2006700010046','200670001004668010'),(45,'COLONIA','EJIDAL','2006700010048','200670001004868140'),(46,'FRACCIONAMIENTO','ELSA','2006700010050','200670001005068010'),(47,'COLONIA','EMILIANO ZAPATA','2006700010051','200670001005168148'),(48,'COLONIA','ESTRELLA','2006700010052','200670001005268040'),(49,'COLONIA','EUCALIPTOS','2006700010053','200670001005368050'),(50,'COLONIA','EX-HACIENDA SANTA ROSA 1A. SECCIÓN','2006700010055','200670001005568274'),(51,'COLONIA','EX-HACIENDA SANTA ROSA 2A. SECCIÓN','2006700010056','200670001005668274'),(52,'BARRIO','DEL EX-MARQUESADO','2006700010057','200670001005768030'),(53,'UNIDAD HABITACIONAL','FOVISSSTE','2006700010058','200670001005868020'),(54,'FRACCIÓN','PONIENTE SAN MARTÍN MEXICAPAM','2006700010059','200670001005968140'),(55,'COLONIA','FRANCISCO I. MADERO','2006700010060','200670001006068060'),(56,'FRACCIONAMIENTO','LA FUNDICIÓN','2006700010061','200670001006168154'),(57,'COLONIA','GUADALUPE VICTORIA','2006700010064','200670001006468010'),(58,'COLONIA','GUELÁGUETZA','2006700010066','200670001006668015'),(59,'FRACCIONAMIENTO','HABITACIONAL POPULAR LA NORIA','2006700010067','200670001006768083'),(60,'FRACCIONAMIENTO','HACIENDA','2006700010068','200670001006868080'),(61,'SIN TIPO','CENTRO COMERCIAL PLAZA OAXACA','2006700010069','200670001006900000'),(62,'COLONIA','HELADIO RAMÍREZ LÓPEZ','2006700010070','200670001007068010'),(63,'PARAJE','LA HUMEDAD','2006700010073','200670001007300000'),(64,'FRACCIONAMIENTO','INDEPENDENCIA','2006700010075','200670001007500000'),(65,'FRACCIONAMIENTO','DEL ISSSTE','2006700010076','200670001007668040'),(66,'UNIDAD HABITACIONAL','ISSSTE','2006700010077','200670001007768040'),(67,'COLONIA','ITANDEHUI','2006700010078','200670001007868140'),(68,'FRACCIONAMIENTO','JACARANDAS','2006700010079','200670001007968010'),(69,'BARRIO','DE JALATLACO','2006700010080','200670001008000000'),(70,'COLONIA','JARDÍN','2006700010081','200670001008168020'),(71,'COLONIA','JARDINES','2006700010082','200670001008268154'),(72,'FRACCIONAMIENTO','JARDINES DE LA SIERRA','2006700010084','200670001008468154'),(73,'COLONIA','JOSÉ VASCONCELOS','2006700010085','200670001008568115'),(74,'COLONIA','LA JOYA PUEBLO NUEVO','2006700010086','200670001008668276'),(75,'COLONIA','LA JOYA MONTOYA','2006700010087','200670001008768140'),(76,'COLONIA','LÁZARO CÁRDENAS 1A. SECCIÓN','2006700010089','200670001008968140'),(77,'COLONIA','LÁZARO CÁRDENAS 2A. SECCIÓN','2006700010090','200670001009068140'),(78,'COLONIA','LIBERTAD','2006700010091','200670001009168090'),(79,'COLONIA','LINDA VISTA','2006700010092','200670001009268030'),(80,'FRACCIONAMIENTO','LA LOMA AMPLIACIÓN','2006700010093','200670001009368040'),(81,'COLONIA','LOMA BONITA','2006700010094','200670001009400000'),(82,'COLONIA','LOMA LINDA','2006700010095','200670001009568024'),(83,'FRACCIONAMIENTO','LA LOMA','2006700010096','200670001009668040'),(84,'FRACCIONAMIENTO','LOMAS DE ANTEQUERA','2006700010097','200670001009768060'),(85,'FRACCIONAMIENTO','LOMAS DE LA AZUCENA','2006700010098','200670001009868040'),(86,'FRACCIONAMIENTO','LOMAS DE LA CASCADA','2006700010099','200670001009968046'),(87,'COLONIA','LOMAS DE LOS PINOS','2006700010100','200670001010068154'),(88,'COLONIA','LOMAS DE MICROONDAS','2006700010101','200670001010168048'),(89,'COLONIA','LOMAS DE SAN JACINTO (DEL SECTOR 2 AL 8)','2006700010102','200670001010200000'),(90,'COLONIA','LOMAS DE SAN JACINTO SECTOR UNO','2006700010103','200670001010368015'),(91,'FRACCIONAMIENTO','LOMAS DE SAN JUAN','2006700010104','200670001010468150'),(92,'FRACCIONAMIENTO','LOMAS DE SANTA ROSA','2006700010105','200670001010568010'),(93,'COLONIA','LOMAS DEL CRESTÓN','2006700010106','200670001010668048'),(94,'COLONIA','LOMAS PANORÁMICAS','2006700010107','200670001010768010'),(95,'COLONIA','LUIS DONALDO COLOSIO','2006700010108','200670001010868140'),(96,'COLONIA','LUIS JIMÉNEZ FIGUEROA','2006700010109','200670001010968070'),(97,'FRACCIONAMIENTO','LA LUZ','2006700010110','200670001011068040'),(98,'COLONIA','DEL MAESTRO','2006700010111','200670001011168010'),(99,'COLONIA','EL MANANTIAL','2006700010112','200670001011268274'),(100,'FRACCIONAMIENTO','LOS MANGALES','2006700010113','200670001011368040'),(101,'COLONIA','MANUEL ÁVILA CAMACHO','2006700010114','200670001011468020'),(102,'COLONIA','MANUEL SABINO CRESPO','2006700010115','200670001011568040'),(103,'SIN TIPO','\"MANZANA 47 Y 48 \"\"A\"\"\"','2006700010116','200670001011668274'),(104,'SIN TIPO','\"MANZANA 48 \"\"B\"\"\"','2006700010117','200670001011768274'),(105,'COLONIA','9 DE MAYO','2006700010118','200670001011868274'),(106,'COLONIA','CONSTITUYENTES','2006700010119','200670001011968274'),(107,'COLONIA','MARGARITA MAZA 1A. SECCIÓN','2006700010120','200670001012068140'),(108,'COLONIA','MARGARITA MAZA 2A. SECCIÓN','2006700010121','200670001012168140'),(109,'FRACCIONAMIENTO','MARGARITAS','2006700010122','200670001012268030'),(110,'COLONIA','MÁRTIRES DE RÍO BLANCO','2006700010123','200670001012368040'),(111,'COLONIA','MICROONDAS','2006700010124','200670001012468048'),(112,'COLONIA','MIGUEL ALEMÁN VALDÉZ','2006700010125','200670001012568120'),(113,'COLONIA','MIGUEL HIDALGO','2006700010126','200670001012668140'),(114,'UNIDAD HABITACIONAL','MILITAR','2006700010127','200670001012768100'),(115,'COLONIA','MOCTEZUMA','2006700010128','200670001012868154'),(116,'UNIDAD HABITACIONAL','\"MODELO \"\"C\"\"\"','2006700010129','200670001012968100'),(117,'COLONIA','ESTADO DE OAXACA','2006700010130','200670001013068140'),(118,'COLONIA','MONTE ALBÁN','2006700010131','200670001013168154'),(119,'SIN TIPO','AGENCIA DE POLICÍA MONTOYA','2006700010132','200670001013268036'),(120,'FRACCIONAMIENTO','IVO MONTOYA','2006700010133','200670001013368036'),(121,'COLONIA','MORELOS','2006700010134','200670001013468110'),(122,'COLONIA','NETZAHUALCÓYOTL','2006700010135','200670001013568140'),(123,'COLONIA','NEZA CUBI','2006700010136','200670001013600000'),(124,'FRACCIONAMIENTO','NIÑOS HÉROES','2006700010137','200670001013768030'),(125,'BARRIO','DE LA NORIA','2006700010138','200670001013868083'),(126,'FRACCIONAMIENTO','NUESTRA SEÑORA TRINIDAD DE LAS HUERTAS','2006700010139','200670001013968120'),(127,'COLONIA','OLÍMPICA','2006700010140','200670001014068020'),(128,'FRACCIONAMIENTO','LAS PALMAS','2006700010142','200670001014268157'),(129,'COLONIA','FALDAS DEL FORTÍN','2006700010143','200670001014368030'),(130,'FRACCIONAMIENTO','EL PAPAYO','2006700010144','200670001014468043'),(131,'COLONIA','EL PARAÍSO','2006700010145','200670001014568164'),(132,'FRACCIONAMIENTO','PASAJUEGO','2006700010146','200670001014668103'),(133,'COLONIA','PATRIA NUEVA','2006700010147','200670001014768276'),(134,'FRACCIONAMIENTO','LA PAZ','2006700010148','200670001014868070'),(135,'COLONIA','LAS PEÑAS','2006700010149','200670001014968150'),(136,'BARRIO','DEL PEÑASCO','2006700010150','200670001015068075'),(137,'COLONIA','DEL PERIODISTA','2006700010151','200670001015168060'),(138,'FRACCIONAMIENTO','LOS PILARES','2006700010154','200670001015468036'),(139,'FRACCIONAMIENTO','LOS PINOS','2006700010156','200670001015668030'),(140,'COLONIA','PINTORES','2006700010157','200670001015768154'),(141,'FRACCIONAMIENTO','LOS PIRÚS','2006700010158','200670001015868080'),(142,'FRACCIONAMIENTO','POPULAR VICTORIA','2006700010159','200670001015968030'),(143,'COLONIA','POSTAL','2006700010160','200670001016068080'),(144,'FRACCIONAMIENTO','POZA DEL ÁNGEL','2006700010161','200670001016168045'),(145,'FRACCIONAMIENTO','POZAS ARCAS','2006700010162','200670001016268043'),(146,'FRACCIONAMIENTO','PRESA SAN FELIPE','2006700010163','200670001016368024'),(147,'COLONIA','PRESIDENTE JUÁREZ','2006700010164','200670001016468140'),(148,'COLONIA','PRESIDENTES DE MÉXICO','2006700010165','200670001016568276'),(149,'COLONIA','PRIMAVERA','2006700010166','200670001016668158'),(150,'BARRIO','EL PROGRESO PARTE ALTA','2006700010167','200670001016768150'),(151,'BARRIO','EL PROGRESO PARTE BAJA','2006700010168','200670001016868150'),(152,'COLONIA','PROVIDENCIA','2006700010169','200670001016968048'),(153,'SIN TIPO','AGENCIA MUNICIPAL DE PUEBLO NUEVO','2006700010170','200670001017068274'),(154,'FRACCIONAMIENTO','REAL DE CANDIANI','2006700010171','200670001017168130'),(155,'COLONIA','REFORESTACIÓN','2006700010172','200670001017268274'),(156,'COLONIA','REFORMA','2006700010173','200670001017368050'),(157,'FRACCIONAMIENTO','RESIDENCIAL SAN FELIPE','2006700010175','200670001017568020'),(158,'FRACCIONAMIENTO','EL RETIRO','2006700010176','200670001017668044'),(159,'COLONIA','REVOLUCIÓN','2006700010177','200670001017768010'),(160,'UNIDAD HABITACIONAL','RICARDO FLORES MAGÓN','2006700010178','200670001017868058'),(161,'FRACCIONAMIENTO','RINCÓN DEL ACUEDUCTO','2006700010179','200670001017968040'),(162,'FRACCIONAMIENTO','LOS RÍOS','2006700010180','200670001018068028'),(163,'BARRIO','EL ROSARIO PARTE ALTA','2006700010181','200670001018168164'),(164,'BARRIO','EL ROSARIO PARTE BAJA','2006700010182','200670001018268150'),(165,'COLONIA','DEL ROSARIO','2006700010183','200670001018368030'),(166,'PARAJE','SAN ANDRÉS','2006700010184','200670001018468276'),(167,'SIN TIPO','AGENCIA MUNICIPAL DE SAN FELIPE DEL AGUA','2006700010185','200670001018500000'),(168,'COLONIA','SAN FRANCISCO','2006700010186','200670001018668164'),(169,'COLONIA','SAN ISIDRO','2006700010187','200670001018768276'),(170,'FRACCIONAMIENTO','SAN JOSÉ LA NORIA','2006700010188','200670001018868120'),(171,'SIN TIPO','AGENCIA MUNICIPAL DE SAN JUAN CHAPULTEPEC','2006700010189','200670001018968153'),(172,'SIN TIPO','AGENCIA DE POLICÍA SAN LUIS BELTRÁN','2006700010190','200670001019068020'),(173,'COLONIA','SANTA ANITA PARTE ALTA','2006700010192','200670001019268150'),(174,'COLONIA','SANTA ANITA PARTE BAJA','2006700010193','200670001019368150'),(175,'COLONIA','SANTA ANITA PARTE MEDIA','2006700010194','200670001019468150'),(176,'COLONIA','SANTA MARÍA','2006700010196','200670001019668030'),(177,'SIN TIPO','AGENCIA MUNICIPAL SANTA ROSA PANZACOLA','2006700010198','200670001019868039'),(178,'COLONIA','SANTO TOMÁS','2006700010199','200670001019968043'),(179,'COLONIA','SATÉLITE','2006700010200','200670001020068125'),(180,'FRACCIONAMIENTO','SAUCES','2006700010201','200670001020168010'),(181,'COLONIA','7 REGIONES','2006700010202','200670001020268023'),(182,'COLONIA','7 REGIONES AMPLIACIÓN','2006700010203','200670001020368023'),(183,'BARRIO','LA SOLEDAD','2006700010205','200670001020568157'),(184,'COLONIA','LA SOLEDAD','2006700010206','200670001020600000'),(185,'COLONIA','SOLIDARIDAD','2006700010207','200670001020700000'),(186,'COLONIA','SURCOS LARGOS','2006700010208','200670001020868127'),(187,'BARRIO','TRINIDAD DE LAS HUERTAS','2006700010209','200670001020968120'),(188,'FRACCIONAMIENTO','TRINIDAD DE LAS HUERTAS','2006700010210','200670001021068120'),(189,'FRACCIONAMIENTO','TULIPANES','2006700010211','200670001021168010'),(190,'COLONIA','UNIÓN','2006700010212','200670001021268164'),(191,'COLONIA','UNIÓN Y PROGRESO','2006700010213','200670001021368050'),(192,'FRACCIONAMIENTO','VALLE ESMERALDA','2006700010214','200670001021468125'),(193,'COLONIA','DEL VALLE','2006700010215','200670001021568150'),(194,'COLONIA','VICENTE SUÁREZ','2006700010216','200670001021668030'),(195,'COLONIA','VICENTE SUÁREZ','2006700010217','200670001021768030'),(196,'FRACCIONAMIENTO','VILLA DEL MARQUÉZ','2006700010218','200670001021868030'),(197,'FRACCIONAMIENTO','VILLAS DE SAN LUIS','2006700010219','200670001021968059'),(198,'FRACCIONAMIENTO','VILLAS DE ANTEQUERA','2006700010220','200670001022068020'),(199,'FRACCIONAMIENTO','VILLAS DE MONTE ALBÁN','2006700010221','200670001022168145'),(200,'FRACCIONAMIENTO','VISTA HERMOSA','2006700010223','200670001022300000'),(201,'COLONIA','VOLCANES','2006700010224','200670001022468020'),(202,'BARRIO','DE XOCHIMILCO','2006700010225','200670001022568040'),(203,'SIN TIPO','1A. ETAPA INFONAVIT 1O. DE MAYO','2006700010227','200670001022768020'),(204,'SIN TIPO','2A. ETAPA INFONAVIT 1O. DE MAYO','2006700010228','200670001022868020'),(205,'SIN TIPO','3A. ETAPA INFONAVIT 1O. DE MAYO','2006700010229','200670001022968020'),(206,'SIN TIPO','4A. ETAPA INFONAVIT 1O. DE MAYO','2006700010230','200670001023068020'),(207,'SIN TIPO','5A. ETAPA INFONAVIT 1O. DE MAYO','2006700010231','200670001023168020'),(208,'SIN TIPO','6A. ETAPA INFONAVIT 1O. DE MAYO','2006700010232','200670001023268020'),(209,'SIN TIPO','7A. ETAPA INFONAVIT 1O. DE MAYO','2006700010233','200670001023368020'),(210,'SIN TIPO','7A. ETAPA INFONAVIT 1O. DE MAYO','2006700010233','200670001023368020'),(211,'SIN TIPO','8A. ETAPA INFONAVIT 1O. DE MAYO (CROC)','2006700010234','200670001023468020'),(212,'SIN TIPO','9A. ETAPA INFONAVIT 1O. DE MAYO','2006700010236','200670001023668020'),(213,'SIN TIPO','10A. ETAPA INFONAVIT 1O. DE MAYO','2006700010237','200670001023768020'),(214,'COLONIA','10 DE ABRIL','2006700010238','200670001023800000'),(215,'SIN TIPO','SIN NOMBRE DE COLONIA 1','2006700010241','200670001024100000'),(216,'SIN TIPO','SIN NOMBRE DE COLONIA 2','2006700010242','200670001024268140'),(217,'ZONA INDUSTRIAL','TRIPLAY','2006700010243','200670001024300000'),(218,'CIUDAD','UNIVERSITARIA UABJO','2006700010244','200670001024468125'),(219,'SIN TIPO','PARQUE VINICIO CASTILLA','2006700010246','200670001024668020'),(220,'SIN TIPO','ÁREA EDUCATIVA DE NIVEL MEDIO Y SUPERIOR','2006700010247','200670001024768020'),(221,'SIN TIPO','SIN NOMBRE DE COLONIA 3','2006700010248','200670001024800000'),(222,'SIN TIPO','SIN NOMBRE DE COLONIA 4','2006700010249','200670001024968048'),(223,'COLONIA','LOMAS DE XOCHIMILCO','2006700010250','200670001025068010'),(224,'SIN TIPO','UNIDAD DEPORTIVA (UABJO)','2006700010255','200670001025568110'),(225,'UNIDAD HABITACIONAL','BENITO JUÁREZ','2006700010257','200670001025768103'),(226,'FRACCIONAMIENTO','BOSQUE DE SAN FELIPE','2006700010258','200670001025868020'),(227,'BARRIO','LA CHIGULERA','2006700010260','200670001026068026'),(228,'FRACCIONAMIENTO','CONSUELO RUIZ','2006700010261','200670001026168020'),(229,'SIN TIPO','INSTITUTO TECNOLÓGICO DE OAXACA','2006700010270','200670001027068030'),(230,'FRACCIONAMIENTO','JARDINES DE LAS LOMAS','2006700010271','200670001027168143'),(231,'FRACCIONAMIENTO','LEONARDO RODRÍGUEZ ALCAINE 1','2006700010272','200670001027268153'),(232,'FRACCIONAMIENTO','LEONARDO RODRÍGUEZ ALCAINE 2','2006700010273','200670001027368150'),(233,'FRACCIONAMIENTO','LOMA OAXACA','2006700010274','200670001027468020'),(234,'FRACCIONAMIENTO','LOMA SAN FELIPE','2006700010275','200670001027568020'),(235,'UNIDAD HABITACIONAL','\"MODELO \"\"B\"\"\"','2006700010278','200670001027868100'),(236,'COLONIA','OBRERA','2006700010279','200670001027968115'),(237,'FRACCIONAMIENTO','LA PAZ SAN FELIPE','2006700010280','200670001028068020'),(238,'FRACCIONAMIENTO','RESIDENCIAL ÁLAMOS','2006700010282','200670001028268020'),(239,'COLONIA','RICARDO FLORES MAGÓN','2006700010283','200670001028368025'),(240,'FRACCIONAMIENTO','SAN CARLOS','2006700010284','200670001028468020'),(241,'COLONIA','SABINOS','2006700010285','200670001028568020'),(242,'FRACCIONAMIENTO','RINCONADA','2006700010286','200670001028668026'),(243,'PARAJE','LA TRINIDAD','2006700010288','200670001028868080'),(244,'FRACCIONAMIENTO','VALLE DE LOS LIRIOS','2006700010289','200670001028968125'),(245,'FRACCIONAMIENTO','VILLA FRONTU','2006700010290','200670001029068020'),(246,'FRACCIONAMIENTO','VILLAS DE MONTE ALBÁN (GEO)','2006700010291','200670001029168143'),(247,'SIN TIPO','SIN NOMBRE DE COLONIA 5','2006700010292','200670001029200000'),(248,'SIN TIPO','SIN NOMBRE DE COLONIA 6','2006700010293','200670001029368125'),(249,'SIN TIPO','SIN NOMBRE DE COLONIA 7','2006700010295','200670001029568143'),(250,'SIN TIPO','SIN NOMBRE DE COLONIA 8','2006700010296','200670001029668154'),(251,'COLONIA','ADOLFO LÓPEZ MATEOS ZONA NORESTE','2006700010301','200670001030100000'),(252,'COLONIA','AMPLIACIÓN 7 REGIONES PARTE NORTE','2006700010302','200670001030200000'),(253,'AMPLIACIÓN','JARDÍN','2006700010303','200670001030300000'),(254,'PARAJE','CAMPO CHICO','2006700010304','200670001030468125'),(255,'FRACCIONAMIENTO','LA COMPUERTA','2006700010305','200670001030500000'),(256,'FRACCIONAMIENTO','EL ENCINAL','2006700010306','200670001030600000'),(257,'RESIDENCIAL','EX-HACIENDA DE GUADALUPE','2006700010307','200670001030700000'),(258,'COLONIA','FLAVIO PÉREZ GASGA','2006700010308','200670001030800000'),(259,'COLONIA','INSURGENTES','2006700010309','200670001030900000'),(260,'FRACCIONAMIENTO','JARDÍNES DEL VALLE','2006700010310','200670001031000000'),(261,'BARRIO','LOMA RANCHO','2006700010311','200670001031100000'),(262,'COLONIA','OJITO DE AGUA','2006700010313','200670001031300000'),(263,'FRACCIONAMIENTO','ORQUIDEAS','2006700010314','200670001031400000'),(264,'FRACCIONAMIENTO','REAL CASA BLANCA','2006700010315','200670001031500000'),(265,'FRACCIONAMIENTO','REAL SAN MARTÍN','2006700010316','200670001031600000'),(266,'FRACCIONAMIENTO','RESIDENCIAL DEL RÍO SAN FELIPE','2006700010317','200670001031700000'),(267,'FRACCIONAMIENTO','RESIDENCIAL PUENTE DE PIEDRA','2006700010318','200670001031800000'),(268,'FRACCIONAMIENTO','RESIDENCIAL SAN MARTÍN','2006700010319','200670001031900000'),(269,'SIN TIPO','SIN NOMBRE DE COLONIA 11','2006700010320','200670001032000000'),(270,'SIN TIPO','SIN NOMBRE DE COLONIA 12','2006700010321','200670001032100000'),(271,'SIN TIPO','SIN NOMBRE DE COLONIA 13','2006700010322','200670001032200000'),(272,'SIN TIPO','SIN NOMBRE DE COLONIA 14','2006700010323','200670001032300000'),(273,'SIN TIPO','SIN NOMBRE DE COLONIA 15','2006700010324','200670001032468036'),(274,'FRACCIONAMIENTO','SINDICATO AUTÓNOMO','2006700010325','200670001032500000'),(275,'PARAJE','LA CANOA','2006700510001','200670051000100000'),(276,'PARAJE','LA CORTINA','2006700510002','200670051000200000'),(277,'PARAJE','LA LOMA','2006700510003','200670051000300000'),(278,'PARAJE','LA MAGUEYERA','2006700510004','200670051000400000'),(279,'PARAJE','LOS PRETILES','2006700510005','200670051000500000'),(280,'PARAJE','LA PUERTA','2006700510006','200670051000600000'),(281,'PARAJE','SAN PEDRO','2006700510007','200670051000700000'),(282,'SIN TIPO','AGENCIA MUNICIPAL DE VIGUERA','2006700510008','200670051000800000 	');
/*!40000 ALTER TABLE `colonias` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-14 16:28:16
