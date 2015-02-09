create database shop;

show databases;

use shop;

create table user (
user_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
user_name VARCHAR(30) NOT NULL UNIQUE,
password VARCHAR(16) NOT NULL,
first_name VARCHAR(30) NOT NULL,
last_name VARCHAR(30) NOT NULL,
user_type VARCHAR(20) NOT NULL);

select * from user;

describe user;

create table products (
product_id INT NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
product_name VARCHAR(100),
product_type VARCHAR(20),
product_description LONGTEXT,
price DOUBLE,
quantity_in_stock INT);

ALTER TABLE user
ADD email VARCHAR(60) UNIQUE AFTER last_name;

show tables;

describe user_data;

CREATE TABLE user_data (
id INT AUTO_INCREMENT PRIMARY KEY,
user_id INT NOT NULL,
address TINYTEXT,
phone VARCHAR(20),
comments TEXT,
in_black_list BOOLEAN,
FOREIGN KEY (user_id) REFERENCES user (user_id)
);

CREATE TABLE orders (
order_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
user_id int NOT NULL,
order_date DATETIME NOT NULL,
order_status VARCHAR(16) NOT NULL,
comments TEXT,
FOREIGN KEY (user_id) REFERENCES user (user_id)
);

CREATE TABLE order_details (
id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
order_id INT NOT NULL,
product_id INT UNSIGNED NOT NULL,
quantity_each INT UNSIGNED NOT NULL,
price_each DOUBLE NOT NULL,
FOREIGN KEY (order_id) REFERENCES orders (order_id)
);

CREATE TABLE products (
product_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
product_name VARCHAR(100) NOT NULL,
product_type VARCHAR(20) NOT NULL,
product_description TEXT,
price DOUBLE UNSIGNED,
quantity_in_stock INT UNSIGNED
);

ALTER TABLE order_details
ADD FOREIGN KEY (product_id) 
REFERENCES products (product_id);

CREATE TABLE payments (
payment_id INT UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT PRIMARY KEY,
user_id INT NOT NULL,
order_id INT UNSIGNED NOT NULL,
payment_date DATETIME NOT NULL,
amount DOUBLE NOT NULL,
FOREIGN KEY (user_id) REFERENCES user (user_id)
);

ALTER TABLE orders
ADD amount DOUBLE NOT NULL
AFTER user_id;

ALTER TABLE orders
CHANGE order_date date DATETIME NOT NULL;

ALTER TABLE orders
CHANGE order_status status VARCHAR(20) not null;

ALTER TABLE payments
CHANGE payment_date date DATETIME NOT NULL;

ALTER TABLE products
CHANGE product_type type VARCHAR(20) NOT NULL;

ALTER TABLE products
CHANGE product_name name VARCHAR(100) NOT NULL;

ALTER TABLE products
CHANGE product_description description TEXT;