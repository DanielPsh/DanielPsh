-- Database (CMMS)
CREATE DATABASE `CMMS`; 
DROP DATABASE `CMMS`;
SHOW DATABASES;
USE `CMMS`;
-- Tables
SHOW TABLES;
DESCRIBE `employee`;
DROP TABLE `employee`;

-- INT -> integer, DECIMAL -> float, VARCHAR() -> string, DATE -> 'YYYY-MM-DD', 
-- DATETIME -> 'YYYY-MM-DD HH:MM:SS' 

CREATE TABLE `employee`(
	`EID` INT PRIMARY KEY,
    `name` VARCHAR(100), 
    `sex` CHAR(1),
    `role` VARCHAR(7), -- 'officer' -> 'manager' -> 'worker'
    `birth` DATE,
    `contact_no` VARCHAR(30),
    `MID` INT, -- Manager supervising worker
    `OID` INT, -- Officer supervising manager
    CONSTRAINT fk_manager_supervise FOREIGN KEY (`MID`) REFERENCES `employee`(`EID`) ON DELETE SET NULL,
    CONSTRAINT fk_officer_supervise FOREIGN KEY (`OID`) REFERENCES `employee`(`EID`) ON DELETE SET NULL
);

CREATE TABLE `location`(
	`LID` INT PRIMARY KEY,
    `name` VARCHAR(100) UNIQUE,
    `type` VARCHAR(30),
    `no` INT,
    `status` BOOL DEFAULT 1 -- true for active building
);

CREATE TABLE `product`(
	`PID` INT PRIMARY KEY,
    `name` VARCHAR(100),
	`brand` VARCHAR(50),
    UNIQUE (`brand`, `name`)
);

CREATE TABLE `cas`( -- unique identification number to chemical substance
	`PID` INT,
    `cas` VARCHAR(12), 
    PRIMARY KEY(`PID`, `cas`),
    CONSTRAINT fk_product_included FOREIGN KEY (`PID`) REFERENCES `product`(`PID`) ON DELETE CASCADE
);

-- CAS format: separated by hyphens into three parts
-- the first consisting from two up to seven digits
-- the second consisting of two digits
-- the third consisting of a single digit

CREATE TABLE `company`(
	`CID` INT PRIMARY KEY,
    `name` VARCHAR(100) UNIQUE,
    `contact_no` VARCHAR(30)
);

CREATE TABLE `activity`(
	`AID` INT PRIMARY KEY,
    `type` VARCHAR(30),
    `s_date` DATE,
    `e_date` DATE,
    `budget` DECIMAL(15,2),
    `LID` INT,
    `CID` INT,
    CONSTRAINT fk_location_performed FOREIGN KEY (`LID`) REFERENCES `location`(`LID`) ON DELETE SET NULL,
    CONSTRAINT fk_company_contracted FOREIGN KEY (`CID`) REFERENCES `company`(`CID`) ON DELETE SET NULL
);

CREATE TABLE `supervise`(
	`MID` INT,
    `LID` INT,
    `Date` DATE,
    PRIMARY KEY(`MID`, `LID`),
    CONSTRAINT fk_employee_supervise FOREIGN KEY (`MID`) REFERENCES `employee`(`EID`) ON DELETE CASCADE,
    CONSTRAINT fk_location_supervised FOREIGN KEY (`LID`) REFERENCES `location`(`LID`) ON DELETE CASCADE
);

CREATE TABLE `assign`(
	`EID` INT,
    `AID` INT,
    PRIMARY KEY(`EID`, `AID`),
    CONSTRAINT fk_employee_assign FOREIGN KEY (`EID`) REFERENCES `employee`(`EID`) ON DELETE CASCADE,
    CONSTRAINT fk_activity_assigned FOREIGN KEY (`AID`) REFERENCES `activity`(`AID`) ON DELETE CASCADE
);

CREATE TABLE `use`(
	`AID` INT,
    `PID` INT,
    PRIMARY KEY(`AID`, `PID`),
    CONSTRAINT fk_activity_use FOREIGN KEY (`AID`) REFERENCES `activity`(`AID`) ON DELETE CASCADE,
    CONSTRAINT fk_product_used FOREIGN KEY (`PID`) REFERENCES `product`(`PID`) ON DELETE CASCADE
);

