-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.22 - MySQL Community Server - GPL
-- Server OS:                    Linux
-- HeidiSQL Version:             11.1.0.6116
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table garage_api.action
CREATE TABLE IF NOT EXISTS `action` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table garage_api.action: ~1 rows (approximately)
/*!40000 ALTER TABLE `action` DISABLE KEYS */;
REPLACE INTO `action` (`id`, `title`, `content`, `price`) VALUES
	(2, 'Swapping the car engine 2', 'We need to swap the car engine by putting a carrot inside of it.', 10.55),
	(3, 'Swapping the car engine v2', 'We need to swap the car engine by putting a carrot inside of it.', 10.55);
/*!40000 ALTER TABLE `action` ENABLE KEYS */;

-- Dumping structure for table garage_api.car
CREATE TABLE IF NOT EXISTS `car` (
  `id` int NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `license_plate` varchar(255) DEFAULT NULL,
  `mot` date DEFAULT NULL COMMENT 'Same as APK keuring in dutch',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table garage_api.car: ~2 rows (approximately)
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
REPLACE INTO `car` (`id`, `brand`, `type`, `license_plate`, `mot`) VALUES
	(1, 'Opel', 'Corsa', 'NL-A3-AW-34', '2021-01-06');
/*!40000 ALTER TABLE `car` ENABLE KEYS */;

-- Dumping structure for table garage_api.car_files
CREATE TABLE IF NOT EXISTS `car_files` (
  `car_id` int DEFAULT NULL,
  `file_id` int DEFAULT NULL,
  UNIQUE KEY `UK_ahshpghk6hi2fxxaun9ye3ykj` (`file_id`),
  KEY `FKokko7dnq0uy778o5rso0mq19c` (`car_id`),
  CONSTRAINT `FKjb5042veof3jlqhop3th1akhn` FOREIGN KEY (`file_id`) REFERENCES `file` (`id`),
  CONSTRAINT `FKokko7dnq0uy778o5rso0mq19c` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table garage_api.car_files: ~0 rows (approximately)
/*!40000 ALTER TABLE `car_files` DISABLE KEYS */;
/*!40000 ALTER TABLE `car_files` ENABLE KEYS */;

-- Dumping structure for table garage_api.car_repair
CREATE TABLE IF NOT EXISTS `car_repair` (
  `car_id` int DEFAULT NULL,
  `repair_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table garage_api.car_repair: ~0 rows (approximately)
/*!40000 ALTER TABLE `car_repair` DISABLE KEYS */;
/*!40000 ALTER TABLE `car_repair` ENABLE KEYS */;

-- Dumping structure for table garage_api.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `zipcode` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table garage_api.customer: ~7 rows (approximately)
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
REPLACE INTO `customer` (`id`, `firstname`, `lastname`, `phonenumber`, `email`, `address`, `zipcode`) VALUES
	(1, 'Toni', 'Mijatovic', '0636594682', 'tonicroadev@gmail.com', 'Hendrik van Viandenstraat 13B', '3817');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumping structure for table garage_api.customer_car
CREATE TABLE IF NOT EXISTS `customer_car` (
  `customer_id` int DEFAULT NULL,
  `car_id` int DEFAULT NULL,
  KEY `FKcj5nomddolfruitwojnlxmcjw` (`car_id`),
  KEY `FKm1oxfddu8hykpgt7gyj3rcvwk` (`customer_id`),
  CONSTRAINT `FKcj5nomddolfruitwojnlxmcjw` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`),
  CONSTRAINT `FKm1oxfddu8hykpgt7gyj3rcvwk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table garage_api.customer_car: ~4 rows (approximately)
/*!40000 ALTER TABLE `customer_car` DISABLE KEYS */;
REPLACE INTO `customer_car` (`customer_id`, `car_id`) VALUES
	(1, 1);
/*!40000 ALTER TABLE `customer_car` ENABLE KEYS */;

-- Dumping structure for table garage_api.file
CREATE TABLE IF NOT EXISTS `file` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table garage_api.file: ~0 rows (approximately)
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
/*!40000 ALTER TABLE `file` ENABLE KEYS */;

-- Dumping structure for table garage_api.parts
CREATE TABLE IF NOT EXISTS `parts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table garage_api.parts: ~0 rows (approximately)
/*!40000 ALTER TABLE `parts` DISABLE KEYS */;
REPLACE INTO `parts` (`id`, `name`, `price`, `quantity`) VALUES
	(1, 'Car tires', 10.12, 2);
/*!40000 ALTER TABLE `parts` ENABLE KEYS */;

-- Dumping structure for table garage_api.privilege
CREATE TABLE IF NOT EXISTS `privilege` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table garage_api.privilege: ~2 rows (approximately)
/*!40000 ALTER TABLE `privilege` DISABLE KEYS */;
REPLACE INTO `privilege` (`id`, `name`) VALUES
	(1, 'READ_PRIVILEGE'),
	(2, 'WRITE_PRIVILEGE');
/*!40000 ALTER TABLE `privilege` ENABLE KEYS */;

-- Dumping structure for table garage_api.repair
CREATE TABLE IF NOT EXISTS `repair` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` text,
  `scheduled_at` timestamp NULL DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `car_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2d97exad9crdxhd5kgnyc29wj` (`car_id`),
  CONSTRAINT `FK2d97exad9crdxhd5kgnyc29wj` FOREIGN KEY (`car_id`) REFERENCES `car` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table garage_api.repair: ~27 rows (approximately)
/*!40000 ALTER TABLE `repair` DISABLE KEYS */;
/*!40000 ALTER TABLE `repair` ENABLE KEYS */;

-- Dumping structure for table garage_api.repair_action
CREATE TABLE IF NOT EXISTS `repair_action` (
  `repair_id` int DEFAULT NULL,
  `action_id` int DEFAULT NULL,
  KEY `FKc0l1oyx3y7klbcsl83ou1148k` (`action_id`),
  KEY `FK5am85m4yyfkly95b8mntrh3nf` (`repair_id`),
  CONSTRAINT `FK5am85m4yyfkly95b8mntrh3nf` FOREIGN KEY (`repair_id`) REFERENCES `repair` (`id`),
  CONSTRAINT `FKc0l1oyx3y7klbcsl83ou1148k` FOREIGN KEY (`action_id`) REFERENCES `action` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table garage_api.repair_action: ~4 rows (approximately)
/*!40000 ALTER TABLE `repair_action` DISABLE KEYS */;
/*!40000 ALTER TABLE `repair_action` ENABLE KEYS */;

-- Dumping structure for table garage_api.repair_parts
CREATE TABLE IF NOT EXISTS `repair_parts` (
  `repair_id` int DEFAULT NULL,
  `part_id` int DEFAULT NULL,
  KEY `FKrlkaidpocdgnpfsputrjf3r3f` (`part_id`),
  KEY `FK2t4mbdihfbcx12hk6vkjact4r` (`repair_id`),
  CONSTRAINT `FK2t4mbdihfbcx12hk6vkjact4r` FOREIGN KEY (`repair_id`) REFERENCES `repair` (`id`),
  CONSTRAINT `FKrlkaidpocdgnpfsputrjf3r3f` FOREIGN KEY (`part_id`) REFERENCES `parts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table garage_api.repair_parts: ~30 rows (approximately)
/*!40000 ALTER TABLE `repair_parts` DISABLE KEYS */;
/*!40000 ALTER TABLE `repair_parts` ENABLE KEYS */;

-- Dumping structure for table garage_api.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table garage_api.role: ~2 rows (approximately)
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
REPLACE INTO `role` (`id`, `name`) VALUES
	(1, 'ROLE_ADMIN'),
	(2, 'ROLE_MECHANIC'),
	(3, 'ROLE_FRONTOFFICE'),
	(4, 'ROLE_BACKOFFICE'),
	(5, 'ROLE_CASHIER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dumping structure for table garage_api.roles_privileges
CREATE TABLE IF NOT EXISTS `roles_privileges` (
  `role_id` int DEFAULT NULL,
  `privilege_id` int DEFAULT NULL,
  KEY `FK5yjwxw2gvfyu76j3rgqwo685u` (`privilege_id`),
  KEY `FK9h2vewsqh8luhfq71xokh4who` (`role_id`),
  CONSTRAINT `FK5yjwxw2gvfyu76j3rgqwo685u` FOREIGN KEY (`privilege_id`) REFERENCES `privilege` (`id`),
  CONSTRAINT `FK9h2vewsqh8luhfq71xokh4who` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table garage_api.roles_privileges: ~3 rows (approximately)
/*!40000 ALTER TABLE `roles_privileges` DISABLE KEYS */;
REPLACE INTO `roles_privileges` (`role_id`, `privilege_id`) VALUES
	(2, 1);
/*!40000 ALTER TABLE `roles_privileges` ENABLE KEYS */;

-- Dumping structure for table garage_api.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table garage_api.user: ~2 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`id`, `firstname`, `lastname`, `email`, `password`) VALUES
	(2, 'Super', 'User', 'superuser@gmail.com', '$2a$10$oF39tVKw7ELQfwgFF08s0u2QedonI2LkFpJXF5BGXjPwFQMHYVj7m'),
	(3, 'Mechanic', 'User\r\n', 'mechanic@gmail.com', '$2a$10$oF39tVKw7ELQfwgFF08s0u2QedonI2LkFpJXF5BGXjPwFQMHYVj7m'),
	(4, 'Backoffice', 'User\r\n', 'backoffice@gmail.com', '$2a$10$oF39tVKw7ELQfwgFF08s0u2QedonI2LkFpJXF5BGXjPwFQMHYVj7m'),
	(5, 'Frontoffice', 'User\r\n', 'frontoffice@gmail.com', '$2a$10$oF39tVKw7ELQfwgFF08s0u2QedonI2LkFpJXF5BGXjPwFQMHYVj7m'),
	(6, 'Cashier', 'User\r\n', 'cashier@gmail.com', '$2a$10$oF39tVKw7ELQfwgFF08s0u2QedonI2LkFpJXF5BGXjPwFQMHYVj7m');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table garage_api.users_roles
CREATE TABLE IF NOT EXISTS `users_roles` (
  `user_id` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  KEY `FKt4v0rrweyk393bdgt107vdx0x` (`role_id`),
  KEY `FKgd3iendaoyh04b95ykqise6qh` (`user_id`),
  CONSTRAINT `FKgd3iendaoyh04b95ykqise6qh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKt4v0rrweyk393bdgt107vdx0x` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table garage_api.users_roles: ~2 rows (approximately)
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
REPLACE INTO `users_roles` (`user_id`, `role_id`) VALUES
	(2, 1),
	(3, 2),
	(4, 3),
	(5, 4),
	(6, 5);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
