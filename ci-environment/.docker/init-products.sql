CREATE DATABASE IF NOT EXISTS products;
USE products;
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`id`)
);
GRANT ALL PRIVILEGES ON *.* TO 'gitea'@'%' IDENTIFIED BY 'gitea';
CREATE USER 'springuser'@'%' IDENTIFIED BY 'ThePassword';
CREATE USER 'springuser'@'localhost' IDENTIFIED BY 'ThePassword';
GRANT ALL PRIVILEGES ON products.* TO 'springuser'@'%' IDENTIFIED BY 'ThePassword';
GRANT ALL PRIVILEGES ON products.* TO 'springuser'@'localhost' IDENTIFIED BY 'ThePassword';
FLUSH PRIVILEGES;