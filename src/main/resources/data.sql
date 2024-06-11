-- Insert basic and premium subscriptions into the table
INSERT INTO subscriptions (subscription_name, monthly_price, subscription_type) VALUES ('BasicSubscription', 20.00, 'BASIC');
INSERT INTO subscriptions (subscription_name, monthly_price, subscription_type) VALUES ('PremiumSubscription', 40.00, 'PREMIUM');

-- User with BasicSubscription
INSERT INTO users (email, password, name, surname, age, weight, gender, subscription_id) VALUES ('user1@gmail.com', 'password1', 'John', 'Doe', 33, 75.5, 'MALE', 1);
-- User with PremiumSubscription
INSERT INTO users (email, password, name, surname, age, weight, gender, subscription_id) VALUES ('user2@gmail.com', 'password2', 'Jane', 'Smith', 25, 65.0, 'FEMALE', 2);


