drop user if exists 'ern'@'localhost';

create user 'ern'@'localhost' identified by 'ern123';
grant all privileges on ecommercedb.* to 'ern'@'localhost';
flush privileges;

drop database if exists ecommercedb;

create database ecommercedb;