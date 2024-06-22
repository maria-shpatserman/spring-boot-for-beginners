CREATE SCHEMA  IF NOT EXISTS `testdb`;
SET SCHEMA `testdb`;
DROP TABLE IF EXISTS `employee`;
DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`username`)
) ;

CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL
 ) ;
 INSERT INTO `users`
 VALUES
 ('john','{noop}test123',1),
 ('mary','{noop}test123',1),
 ('susan','{noop}test123',1);

 INSERT INTO `authorities`
 VALUES
 ('john','ROLE_EMPLOYEE'),
 ('mary','ROLE_EMPLOYEE'),
 ('mary','ROLE_MANAGER'),
 ('susan','ROLE_EMPLOYEE'),
 ('susan','ROLE_MANAGER'),
 ('susan','ROLE_ADMIN');
