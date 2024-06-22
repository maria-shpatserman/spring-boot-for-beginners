CREATE SCHEMA  IF NOT EXISTS `testdb`;
SET SCHEMA `testdb`;
DROP TABLE IF EXISTS `employee`;
DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `members`;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `members` (
  `user_id` varchar(50) NOT NULL,
  `pw` varchar(50) NOT NULL,
  `active` tinyint NOT NULL,
  PRIMARY KEY (`user_id`)
) ;

CREATE TABLE `roles` (
  `user_id` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL
 ) ;
 INSERT INTO `members`
 VALUES
 ('john','{noop}test123',1),
 ('mary','{noop}test123',1),
 ('susan','{noop}test123',1);

 INSERT INTO `roles`
 VALUES
 ('john','ROLE_EMPLOYEE'),
 ('mary','ROLE_EMPLOYEE'),
 ('mary','ROLE_MANAGER'),
 ('susan','ROLE_EMPLOYEE'),
 ('susan','ROLE_MANAGER'),
 ('susan','ROLE_ADMIN');
