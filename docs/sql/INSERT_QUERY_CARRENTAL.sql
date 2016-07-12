create schema carrentalsystem;

use carrentalsystem;

/*DROP table ApplicationUser

*/

CREATE TABLE ApplicationUser
(
sysuserId int NOT NULL AUTO_INCREMENT,
firstName varchar(255) NOT NULL,
lastName varchar(255),
middleName varchar(255),
phone varchar(255),
email varchar(255),
PRIMARY KEY (sysUserId)
);

ALTER TABLE carrentalsystem.ApplicationUser ADD COLUMN 
(
username VARCHAR(30) NOT NULL, 
password varchar(255) NOT NULL,
isadmin VARCHAR (10) NOT NULL DEFAULT 'false'
);

CREATE TABLE Address
(
addressId int NOT NULL AUTO_INCREMENT,
personId int (11) NOT NULL,
`isCustomer` BIT NOT NULL,
streetAddress varchar(255) NOT NULL,
state varchar(255),
city varchar(255),
zipCode varchar(255),
country varchar(255),
PRIMARY KEY (addressid)

        
)ENGINE=INNODB;

ALTER TABLE `carrentalsystem`.`Address` 
MODIFY  COLUMN `zipCode` int 

CREATE TABLE Customer
(
customerId int NOT NULL AUTO_INCREMENT,
 
firstName varchar(255) NOT NULL,
lastName varchar(255),
middleName varchar(255),
email varchar(255),
phone varchar(255),
PRIMARY KEY (customerid)
 
        
)ENGINE=INNODB;


CREATE TABLE Car
(
productId int NOT NULL AUTO_INCREMENT,
 
model varchar(255) NOT NULL,
color varchar(255),
releaseYear int,
make varchar(255),
rentalFeePerDay double,
overduefinePerDay double,
quantity int,
carStatus varchar(255),
plate varchar(255),
mileage int,
PRIMARY KEY (productId)
 
        
)ENGINE=INNODB;

ALTER TABLE `carrentalsystem`.`car` 
ADD COLUMN `name` VARCHAR(45) NULL AFTER `mileage`,
ADD COLUMN `description` VARCHAR(45) NULL AFTER `name`;

CREATE TABLE CheckoutRecordEntry
(
checkoutRecordEntryId int NOT NULL AUTO_INCREMENT,
customerId int not null,
personId int,
carId int not null,
quantity int,
checkoutDate datetime,
duedate datetime,
returnedDate datetime,
isReturned bit,
rentalFee double,
rentalFine double,

PRIMARY KEY (checkoutRecordEntryId)
 
        
)ENGINE=INNODB;