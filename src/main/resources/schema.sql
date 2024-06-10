-- Tabla para las suscripciones
CREATE TABLE IF NOT EXISTS subscriptions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    subscription_name VARCHAR(255) NOT NULL,
    monthly_price DOUBLE NOT NULL,
    subscription_type VARCHAR(255) NOT NULL --this column differentiates subscription types
); 
-- Tabla para los usuarios
CREATE TABLE IF NOT EXISTS users (
    email VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255) NOT NULL,
    age INT,
    weight DOUBLE,
    gender ENUM('MALE', 'FEMALE', 'OTHER'),
    
    subscription_id INT,
    FOREIGN KEY (subscription_id) REFERENCES subscriptions(id)
);

