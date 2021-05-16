



DROP TABLE IF EXISTS employee;

CREATE TABLE  employee (
id bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
first_name varchar(45) DEFAULT NULL,
last_name varchar(45) DEFAULT NULL,
email varchar(45) DEFAULT NULL,
phone varchar(45) DEFAULT NULL
) ;


INSERT INTO `employee` (`id`, `first_name`, `last_name`, `email`, `phone`) VALUES
(2, 'Hoston', 'lindey', 'hl@gmail.com', '90908989899');
