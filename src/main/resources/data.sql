-- Insert basic and premium subscriptions into the table
INSERT INTO subscriptions (subscription_name, monthly_price, subscription_type) VALUES ('BasicSubscription', 20.00, 'BASIC');
INSERT INTO subscriptions (subscription_name, monthly_price, subscription_type) VALUES ('PremiumSubscription', 40.00, 'PREMIUM');

-- User with BasicSubscription
INSERT INTO users (email, password, name, surname, age, weight, gender, subscription_id) VALUES ('user1@gmail.com', 'password1', 'john', 'doe', 33, 75.5, 'MALE', 1);
-- User with PremiumSubscription
INSERT INTO users (email, password, name, surname, age, weight, gender, subscription_id) VALUES ('user2@gmail.com', 'password2', 'jane', 'smith', 25, 65.0, 'FEMALE', 2);

--Insert Gym clases of the Gym
INSERT INTO gym_classes (gym_class_id, name_class, current_capacity, total_capacity,weekly_day) VALUES (01,'Yoga', 6, 12, 'Monday');
INSERT INTO gym_classes (gym_class_id, name_class, current_capacity, total_capacity,weekly_day) VALUES (02,'Pilates', 6, 20, 'Tuesday');
INSERT INTO gym_classes (gym_class_id, name_class, current_capacity, total_capacity,weekly_day) VALUES (03,'BodyPump', 19, 20, 'Thursday');
INSERT INTO gym_classes (gym_class_id, name_class, current_capacity, total_capacity,weekly_day) VALUES (04,'Spinning', 30, 30, 'Friday');

-- Assign users to existing gym classes

-- User 1 (user1@gmail.com) enrolled in Yoga (gym_class_id = 1)
INSERT INTO user_gymclass (user_email, gym_class_id) VALUES ('user1@gmail.com', 1);

-- User 1 (user1@gmail.com) enrolled in Spinning (gym_class_id = 4)
INSERT INTO user_gymclass (user_email, gym_class_id) VALUES ('user1@gmail.com', 2);

-- User 2 (user2@gmail.com) enrolled in Pilates (gym_class_id = 2)
INSERT INTO user_gymclass (user_email, gym_class_id) VALUES ('user2@gmail.com', 2);

-- User 2 (user2@gmail.com) enrolled in BodyPump (gym_class_id = 3)
INSERT INTO user_gymclass (user_email, gym_class_id) VALUES ('user2@gmail.com', 3);

