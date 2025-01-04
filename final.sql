CREATE DATABASE  IF NOT EXISTS `doan` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_bin */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `doan`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: doan
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `date_of_birth` date DEFAULT NULL,
  `customerid` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `gender` enum('man','other','woman') DEFAULT NULL,
  PRIMARY KEY (`customerid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (NULL,1,NULL,NULL,'admin@gmail.com',NULL,NULL,'$2a$10$rD7nYxnC/41TNVbUUNUin.AWzPSbCWHl.LlppnUqiW/of9LReUa3G',NULL,NULL,NULL),('1990-01-01',2,'Hà Nội','Ba Đình','khach1@gmail.com','Nguyễn','Văn A','123456','0987654321','Phố Cổ','man'),('1991-02-02',3,'Hồ Chí Minh','Quận 1','khach2@gmail.com','Trần','Thị B','123456','0976543210','Nguyễn Huệ','woman'),('1992-03-03',4,'Đà Nẵng','Hải Châu','khach3@gmail.com','Lê','Văn C','123456','0965432109','Trần Phú','man'),('1993-04-04',5,'Cần Thơ','Ninh Kiều','khach4@gmail.com','Phạm','Thị D','123456','0954321098','Hòa Bình','woman'),('1994-05-05',6,'Hải Phòng','Ngô Quyền','khach5@gmail.com','Hoàng','Văn E','123456','0943210987','Lạch Tray','man'),('1995-06-06',7,'Huế','Thừa Thiên','khach6@gmail.com','Võ','Thị F','123456','0932109876','Hùng Vương','woman'),('1996-07-07',8,'Bình Dương','Thủ Dầu Một','khach7@gmail.com','Đặng','Văn G','123456','0921098765','Đại Lộ Bình Dương','man'),('1997-08-08',9,'Nha Trang','Nha Trang','khach8@gmail.com','Bùi','Thị H','123456','0910987654','Đường Trần Phú','woman'),('1998-09-09',10,'Vũng Tàu','Bà Rịa','khach9@gmail.com','Phan','Văn K','123456','0909876543','Bãi Sau','man'),('1999-10-10',11,'Quảng Ninh','Hạ Long','khach10@gmail.com','Ngô','Thị L','123456','0898765432','Đường Bãi Cháy','woman'),('1990-05-15',12,'New York','Brooklyn','sanj0@gmail.com','Võ Quang','Bình','$2a$10$nDmJiY2dH4ZRn7scCRtDa.Ynx6mhaVrWxxJas0tPydB32.5a9CFPu','121231212431','123 Main Street','man'),('2004-02-03',13,'Quảng Bình','Quảng Ninh','thang.vokhmt04k22@hcmut.edu.vn','Võ Quang','Thắng','$2a$10$ejCiXLoXV6VMkwLiPrkAseBIN.dzyaTuUBVYszjj67o4wym5VLpU.','0399728845','Hoành Vinh, An Ninh','man'),('2001-12-02',14,'Quảng Bình','Quảng Ninh','zoro0@gmail.com','Võ Quang','Đức','$2a$10$KG4RbnRdnnQRlqVxioTrHuRwsdKdDw1Bu/H8P55SI/MXG8oINigLC','0354673327','Hoành Vinh - An Ninh','man');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_roles`
--

DROP TABLE IF EXISTS `customer_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_roles` (
  `customer_customerid` bigint NOT NULL,
  `roles` varchar(255) DEFAULT NULL,
  KEY `FKnnl2uwgecu1km2g5vjgemvxpj` (`customer_customerid`),
  CONSTRAINT `FKnnl2uwgecu1km2g5vjgemvxpj` FOREIGN KEY (`customer_customerid`) REFERENCES `customer` (`customerid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_roles`
--

LOCK TABLES `customer_roles` WRITE;
/*!40000 ALTER TABLE `customer_roles` DISABLE KEYS */;
INSERT INTO `customer_roles` VALUES (1,'ADMIN'),(1,'CUSTOMER'),(2,'CUSTOMER'),(3,'CUSTOMER'),(4,'CUSTOMER'),(5,'CUSTOMER'),(6,'CUSTOMER'),(7,'CUSTOMER'),(8,'CUSTOMER'),(9,'CUSTOMER'),(10,'CUSTOMER'),(12,'CUSTOMER'),(13,'CUSTOMER'),(14,'CUSTOMER');
/*!40000 ALTER TABLE `customer_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `date_of_birth` date DEFAULT NULL,
  `employeeid` bigint NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `gender` enum('man','other','woman') DEFAULT NULL,
  PRIMARY KEY (`employeeid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('1985-01-01',1,'Hà Nội','Hoàn Kiếm','nhanvien1@gmail.com','Lê','Thị Y','0887654321','Phố Huế','woman'),('1986-02-02',2,'Hồ Chí Minh','Bình Thạnh','nhanvien2@gmail.com','Nguyễn','Văn T','0876543210','Điện Biên Phủ','man'),('1987-03-03',3,'Đà Nẵng','Liên Chiểu','nhanvien3@gmail.com','Trần','Thị P','0865432109','Hoàng Văn Thụ','woman'),('1988-04-04',4,'Cần Thơ','Ô Môn','nhanvien4@gmail.com','Phạm','Văn M','0854321098','Quốc lộ 91','man'),('1989-05-05',5,'Hải Phòng','Kiến An','nhanvien5@gmail.com','Hoàng','Thị V','0843210987','Đường Trần Thành Ngọ','woman'),('1990-06-06',6,'Huế','Phú Vang','nhanvien6@gmail.com','Võ','Văn H','0832109876','Tố Hữu','man'),('1991-07-07',7,'Bình Dương','Dĩ An','nhanvien7@gmail.com','Đặng','Thị S','0821098765','Đường Mỹ Phước','woman'),('1992-08-08',8,'Nha Trang','Diên Khánh','nhanvien8@gmail.com','Bùi','Văn C','0810987654','Đường Võ Nguyên Giáp','man'),('1993-09-09',9,'Vũng Tàu','Đất Đỏ','nhanvien9@gmail.com','Phan','Thị X','0809876543','Đường 30/4','woman'),('1994-10-10',10,'Quảng Ninh','Cẩm Phả','nhanvien10@gmail.com','Ngô','Văn Z','0798765432','Đường Hoàng Quốc Việt','man');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `include`
--

DROP TABLE IF EXISTS `include`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `include` (
  `orderid` bigint NOT NULL,
  `productid` bigint NOT NULL,
  `quantity` bigint DEFAULT NULL,
  PRIMARY KEY (`orderid`,`productid`),
  KEY `FKtouwr4r07tu8wkebc1mcgduaq` (`productid`),
  CONSTRAINT `FKo7ne09wloatr4uc7kjrc7669i` FOREIGN KEY (`orderid`) REFERENCES `orders` (`orderid`),
  CONSTRAINT `FKtouwr4r07tu8wkebc1mcgduaq` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `include`
--

LOCK TABLES `include` WRITE;
/*!40000 ALTER TABLE `include` DISABLE KEYS */;
INSERT INTO `include` VALUES (1,1,2),(2,2,3),(3,3,1),(4,4,5),(5,5,2),(6,1,4),(7,2,6),(8,3,1),(9,4,3),(10,5,2),(11,2,2),(12,1,2),(12,4,1),(13,1,3),(13,3,6),(13,5,2);
/*!40000 ALTER TABLE `include` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `date` date DEFAULT NULL,
  `total_price` double NOT NULL,
  `customerid` bigint DEFAULT NULL,
  `employeeid` bigint DEFAULT NULL,
  `orderid` bigint NOT NULL AUTO_INCREMENT,
  `shipping_address` varchar(255) DEFAULT NULL,
  `status` enum('cancelled','completed','pending') DEFAULT NULL,
  PRIMARY KEY (`orderid`),
  KEY `FKbhieamq65ke02r8ijfso5tivn` (`customerid`),
  KEY `FKnxab0h2xgfm2yjg7skcnvvxja` (`employeeid`),
  CONSTRAINT `FKbhieamq65ke02r8ijfso5tivn` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`),
  CONSTRAINT `FKnxab0h2xgfm2yjg7skcnvvxja` FOREIGN KEY (`employeeid`) REFERENCES `employee` (`employeeid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('2025-01-01',400000,1,1,1,'Phố Huế, Hà Nội','pending'),('2025-01-02',750000,2,2,2,'Nguyễn Huệ, Hồ Chí Minh','completed'),('2025-01-03',300000,3,3,3,'Trần Phú, Đà Nẵng','cancelled'),('2025-01-04',2000000,4,4,4,'Hòa Bình, Cần Thơ','completed'),('2025-01-05',320000,5,5,5,'Lạch Tray, Hải Phòng','pending'),('2025-01-06',800000,6,6,6,'Hùng Vương, Huế','completed'),('2025-01-07',1500000,7,7,7,'Đại Lộ Bình Dương','cancelled'),('2025-01-08',300000,8,8,8,'Đường Trần Phú, Nha Trang','pending'),('2025-01-09',1200000,9,9,9,'Bãi Sau, Vũng Tàu','completed'),('2025-01-10',320000,10,10,10,'Bãi Cháy, Quảng Ninh','pending'),('2025-01-01',500000,13,4,11,NULL,'completed'),('2025-01-01',800000,13,4,12,NULL,'completed'),('2025-01-01',2720000,13,8,13,NULL,'pending');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `price_import` double NOT NULL,
  `price_selling` double NOT NULL,
  `quantity_import` int NOT NULL,
  `quantity_sold` int NOT NULL,
  `quantity_stock` int NOT NULL,
  `employeeid` bigint NOT NULL,
  `productid` bigint NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `gender` enum('man','other','woman') DEFAULT NULL,
  `status` enum('in_stock','out_of_stock') DEFAULT NULL,
  PRIMARY KEY (`productid`),
  KEY `FK1d8n12cvcc1kn48r5a19pbbq4` (`employeeid`),
  CONSTRAINT `FK1d8n12cvcc1kn48r5a19pbbq4` FOREIGN KEY (`employeeid`) REFERENCES `employee` (`employeeid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (100000,200000,100,50,50,1,1,'Adidas','Sản phẩm chất lượng cao','Vải Cotton','Áo Nỉ Dáng Boxy','Thời Trang','woman','in_stock'),(120000,250000,120,70,50,2,2,'Hàng Việt','Thời trang trẻ trung','Vải Thun','Miracle Air Áo Blazer','Thời Trang','man','in_stock'),(150000,300000,150,100,50,3,3,'Hàng Việt','Hàng cao cấp','Da Thật','Ví Nam','Phụ Kiện','man','in_stock'),(200000,400000,200,150,50,4,4,'Hàng Việt','Thiết kế hiện đại','Nhựa Cao Cấp','Balo Du Lịch','Phụ Kiện','woman','in_stock'),(80000,160000,80,40,40,5,5,'Hàng Việt','Thoải mái, dễ sử dụng','Vải Polyester','Tất Nam','Phụ Kiện','man','in_stock');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_image`
--

DROP TABLE IF EXISTS `product_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_image` (
  `productid` bigint NOT NULL,
  `imageurl` varchar(255) NOT NULL,
  PRIMARY KEY (`productid`,`imageurl`),
  CONSTRAINT `FKpndx7xbitglkk8g7dr3quhlxk` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_image`
--

LOCK TABLES `product_image` WRITE;
/*!40000 ALTER TABLE `product_image` DISABLE KEYS */;
INSERT INTO `product_image` VALUES (1,'https://image.uniqlo.com/UQ/ST3/AsianCommon/imagesgoods/476170/sub/goods_476170_sub13_3x4.jpg?width=423'),(1,'https://image.uniqlo.com/UQ/ST3/AsianCommon/imagesgoods/476170/sub/goods_476170_sub14_3x4.jpg?width=423'),(1,'https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/476170/item/vngoods_01_476170_3x4.jpg?width=423'),(1,'https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/476170/sub/vngoods_476170_sub7_3x4.jpg?width=423'),(2,'https://image.uniqlo.com/UQ/ST3/AsianCommon/imagesgoods/477704/sub/goods_477704_sub14_3x4.jpg?width=423'),(2,'https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/477704/item/vngoods_69_477704_3x4.jpg?width=423'),(2,'https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/477704/sub/vngoods_477704_sub1_3x4.jpg?width=423'),(2,'https://www.uniqlo.com/vn/vi/products/E477704-000?colorCode=COL69&sizeCode=SMA003'),(3,'https://example.com/images/product3-2.jpg'),(3,'https://example.com/images/product3.jpg'),(4,'https://example.com/images/product4-2.jpg'),(4,'https://example.com/images/product4.jpg'),(5,'https://example.com/images/product5-2.jpg'),(5,'https://example.com/images/product5.jpg');
/*!40000 ALTER TABLE `product_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rating` (
  `date` date DEFAULT NULL,
  `star` int NOT NULL,
  `customerid` bigint DEFAULT NULL,
  `productid` bigint DEFAULT NULL,
  `ratingid` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ratingid`),
  KEY `FK9gtm3krfx1pf8c8x9x9duliil` (`customerid`),
  KEY `FKc6yriy3gqdxubpodsc191n18q` (`productid`),
  CONSTRAINT `FK9gtm3krfx1pf8c8x9x9duliil` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`),
  CONSTRAINT `FKc6yriy3gqdxubpodsc191n18q` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` VALUES ('2025-01-01',5,1,1,1,'Sản phẩm rất tốt, tôi rất hài lòng!'),('2025-01-02',4,2,2,2,'Chất lượng ổn nhưng giao hàng hơi chậm.'),('2025-01-03',3,3,3,3,'Hàng đúng như mô tả, nhưng cần cải thiện đóng gói.'),('2025-01-04',5,4,4,4,'Tuyệt vời, sẽ ủng hộ lần sau!'),('2025-01-05',2,5,5,5,'Sản phẩm không như mong đợi.'),('2025-01-06',4,6,1,6,'Ổn áp, chất lượng phù hợp giá tiền.'),('2025-01-07',5,7,2,7,'Quá tốt, giao nhanh.'),('2025-01-08',3,8,3,8,'Tạm được, không có gì đặc biệt.'),('2025-01-09',1,9,4,9,'Thất vọng, sản phẩm bị lỗi.'),('2025-01-10',5,10,5,10,'Sản phẩm quá tuyệt vời, giao nhanh.');
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-02  9:47:55
