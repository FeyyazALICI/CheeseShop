
CREATE DATABASE CHEESESHOP

CREATE TABLE CHEESESHOP.stock (
id INT PRIMARY KEY AUTO_INCREMENT,
group_id varchar(40),
hold_quantity INT,
active_quantity INT
);

CREATE TABLE CHEESESHOP.customer (
id INT PRIMARY KEY AUTO_INCREMENT,
name varchar(40),
surname varchar(40),
email varchar(100),
password varchar(20)
);

CREATE TABLE CHEESESHOP.cheddar (
id INT NOT NULL PRIMARY KEY,
hold INT,
purchased INT,
FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE ON UPDATE CASCADE);


CREATE TABLE CHEESESHOP.edam (
id INT NOT NULL PRIMARY KEY,
hold INT,
purchased INT,
FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE ON UPDATE CASCADE);

CREATE TABLE CHEESESHOP.swiss (
id INT NOT NULL PRIMARY KEY,
hold INT,
purchased INT,
FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE ON UPDATE CASCADE);


INSERT INTO CHEESESHOP.cheddar (id, hold, purchased) VALUES(1, 0, 0);
INSERT INTO CHEESESHOP.cheddar (id, hold, purchased) VALUES(2, 0, 0);
INSERT INTO CHEESESHOP.cheddar (id, hold, purchased) VALUES(3, 0, 0);

INSERT INTO CHEESESHOP.edam (id, hold, purchased) VALUES(1, 0, 0);
INSERT INTO CHEESESHOP.edam (id, hold, purchased) VALUES(2, 0, 0);
INSERT INTO CHEESESHOP.edam (id, hold, purchased) VALUES(3, 0, 0);

INSERT INTO CHEESESHOP.swiss (id, hold, purchased) VALUES(1, 0, 0);
INSERT INTO CHEESESHOP.swiss (id, hold, purchased) VALUES(2, 0, 0);
INSERT INTO CHEESESHOP.swiss (id, hold, purchased) VALUES(3, 0, 0);

INSERT INTO stock (group_id, hold_quantity, active_quantity) VALUES('Cheddar', 0, 100);
INSERT INTO stock (group_id, hold_quantity, active_quantity) VALUES('Edam', 0, 100);
INSERT INTO stock (group_id, hold_quantity, active_quantity) VALUES('Swiss', 0, 100);

INSERT INTO CHEESESHOP.customer (name, surname, email, password) VALUES('user0', null, 'user0@gmail.com', password);
INSERT INTO CHEESESHOP.customer (name, surname, email, password) VALUES('user1', null, 'user1@gmail.com', password);
INSERT INTO CHEESESHOP.customer (name, surname, email, password) VALUES('user2', null, 'user2@gmail.com', password);

CREATE USER 'myuser'@'localhost' IDENTIFIED BY 'Light80s!';
GRANT ALL PRIVILEGES ON CHEESESHOP.* TO 'myuser'@'localhost';
