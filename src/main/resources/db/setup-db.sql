drop user if exists 'iclass'@'localhost';

create user 'iclass'@'localhost' identified by 'iclass123';
grant all privileges on ecommercedb.* to 'iclass'@'localhost';
flush privileges;

drop database if exists ecommercedb;

create database ecommercedb;