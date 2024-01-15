package com.supplier.supplier.supplier.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "stock")
@Data
@Entity
public class StockEntity {
    
    /**
    * Last Update on 31.12.2023
    *
    * @author Feyyaz ALICI
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        // id should not be BigDecimal for auto_increment values
    private String group_id;
    private BigDecimal hold_quantity;
    private BigDecimal active_quantity;

    public StockEntity(){
        // Default empty constructor
    }

    public StockEntity(Long id,  String group_id, BigDecimal hold_quantity, BigDecimal active_quantity){
        this.id=id;
        this.group_id=group_id;
        this.hold_quantity=hold_quantity;
        this.active_quantity=active_quantity;
    }
}


/* For Validation
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    * For Swagger       // SwaggerConfig.java is needed         // http://localhost:8080/swagger-ui/index.html
    <dependency>
	    <groupId>org.springdoc</groupId>
	    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
	    <version>2.3.0</version>
    </dependency>
*/




/*
CREATE DATABASE CHEESESHOP

# Creating stock and customer Tables
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

# Populating stock and customer Tables
INSERT INTO CHEESESHOP.stock (group_id, hold_quantity, active_quantity) VALUES('Cheddar', 0, 100);
INSERT INTO CHEESESHOP.stock (group_id, hold_quantity, active_quantity) VALUES('Edam', 0, 100);
INSERT INTO CHEESESHOP.stock (group_id, hold_quantity, active_quantity) VALUES('Swiss', 0, 100);

INSERT INTO CHEESESHOP.customer (name, surname, email, password) VALUES('user0', null, 'user0@gmail.com', password);
INSERT INTO CHEESESHOP.customer (name, surname, email, password) VALUES('user1', null, 'user1@gmail.com', password);
INSERT INTO CHEESESHOP.customer (name, surname, email, password) VALUES('user2', null, 'user2@gmail.com', password);

# Creating basket table
CREATE TABLE CHEESESHOP.basket (
    basket_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    stock_id INT,
    quantity INT,
    FOREIGN KEY (customer_id) REFERENCES CHEESESHOP.customer(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (stock_id) REFERENCES CHEESESHOP.stock(id) ON DELETE CASCADE ON UPDATE CASCADE
);

# Creating User Called myuser
CREATE USER 'myuser'@'localhost' IDENTIFIED BY 'Light80s!';
GRANT ALL PRIVILEGES ON CHEESESHOP.* TO 'myuser'@'localhost';
*/