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
--Tabla para las Clases del Gimnasio
CREATE TABLE IF NOT EXISTS gym_classes (
    gym_class_id INT NOT NULL PRIMARY KEY,
    name_class VARCHAR(255) NOT NULL,
    current_capacity INT NOT NULL,
    total_capacity INT NOT NULL,
    weekly_day VARCHAR(255) NOT NULL
);
-- Tabla intermedia para asignar usuarios a clases de gimnasio
CREATE TABLE IF NOT EXISTS user_gymclass (
    user_email VARCHAR(255) NOT NULL,
    gym_class_id INT NOT NULL,
    PRIMARY KEY (user_email, gym_class_id),
    FOREIGN KEY (user_email) REFERENCES users(email),
    FOREIGN KEY (gym_class_id) REFERENCES gym_classes(gym_class_id)
);
