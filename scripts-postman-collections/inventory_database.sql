-- SQL script to create and populate the inventory database
-- This script creates the necessary schema, tables, and inserts initial data.
CREATE SCHEMA IF NOT EXISTS `inventory_database`;
USE `inventory_database`;

-- Tables 
-- Users Table
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `username` VARCHAR(255) NOT NULL UNIQUE,
  `password` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL UNIQUE,
  `role` VARCHAR(50) NOT NULL
);

-- Products Table
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(255) NOT NULL,
  `description` VARCHAR(500)
);

-- Inventories Table
DROP TABLE IF EXISTS `inventories`;
CREATE TABLE inventories (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `product_id` BIGINT NOT NULL,
    `quantity` INT,
    `price` DOUBLE,
    `cost` DOUBLE,
    `category` VARCHAR(100),
    `status` ENUM ('ACTIVE', 'INACTIVE', 'DISCONTINUED') NOT NULL,
    FOREIGN KEY (`product_id`) REFERENCES `products`(`id`)
);

-- Inserts 
-- Users
INSERT INTO `users` (username, password, email, role) VALUES 
('debigonz', '$2a$10$NEtkNA128DRO8Jm6B2Y.e.3/XhGFWiOpvR7OnqbNe6.wmm9o.Xc5u', 'debigonz19@gmail.com', 'ADMIN'),
('santiblanc', '$2a$10$rQjBReiKv4ml4Mmnuz6Mfu9DwiaPePr5S15419l5GFOTs8iflIDJW', 'santiblanc@example.com', 'USER');

-- Products
INSERT INTO `products` (name, description) VALUES
  ('Laptop', 'High-performance laptop for professionals'),
  ('Smartphone', 'Latest model smartphone with advanced features'),
  ('Headphones', 'Noise-cancelling over-ear headphones'),
  ('Monitor', '27-inch 4K UHD monitor'),
  ('Keyboard', 'Mechanical keyboard with RGB lighting');

-- Inventories
INSERT INTO inventories (product_id, quantity, price, cost, category, status) VALUES
  (1, 50, 1200.00, 900.00, 'Electronics', 'ACTIVE'),
  (2, 100, 800.00, 600.00, 'Electronics', 'ACTIVE'),
  (3, 75, 150.00, 100.00, 'Audio', 'ACTIVE'),
  (4, 30, 400.00, 300.00, 'Displays', 'INACTIVE'),
  (5, 60, 90.00, 60.00, 'Accessories', 'ACTIVE');
