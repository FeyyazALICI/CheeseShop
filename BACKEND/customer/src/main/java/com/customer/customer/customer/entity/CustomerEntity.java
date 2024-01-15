package com.customer.customer.customer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "customer")
public class CustomerEntity {

    /**
    * Last Update on 31.12.2023
    *
    * @author Feyyaz ALICI
    */


    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String surname;

    @NotNull(message = "Email can not be left empty nor blank")
    @NotBlank(message = "Email can not be left empty nor blank")
    private String email;

    @NotNull(message = "Password can not be left empty nor blank")
    @NotBlank(message = "Password can not be left empty nor blank")
    private String password;

    public CustomerEntity(){
        // default empty constructor
    }

    public CustomerEntity (long id, String name, String surname, String email, String password){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
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