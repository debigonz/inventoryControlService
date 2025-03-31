CREATE SCHEMA IF NOT EXISTS `user_database`;
USE `user_database`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `username` VARCHAR(255) NOT NULL UNIQUE,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL UNIQUE,
  `role` VARCHAR(50) NOT NULL
);

INSERT INTO `users` (username, password, email, role) VALUES 
('debigonz', '$2a$10$Eix6z1WqNEN7zA7X6c5jQe8T5c9fQzB5K/9R4hX7XW2N4t1/0kP6W', 'debigonz19@gmail.com', 'ADMIN'),
('santiblanc', '$2a$10$BqE6z1WqNEN7zA7X6c5jQe8T5c9fQzB5K/9R4hX7XW2N4t1/0kP6W', 'santiblanc@example.com', 'USER');