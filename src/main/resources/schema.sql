CREATE TABLE IF NOT EXISTS USERS(
    email VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    age INT,
    weight DOUBLE ,
    gender ENUM('MALE', 'FEMALE', 'OTHER') 
);