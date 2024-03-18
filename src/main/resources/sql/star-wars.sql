CREATE DATABASE `star_wars` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `films` (
  `episode_id` int NOT NULL,
  `title` varchar(100) NOT NULL,
  `release_date` date NOT NULL,
  PRIMARY KEY (`episode_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;