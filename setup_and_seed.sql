USE budget_db;

DROP TABLE IF EXISTS expense;
DROP TABLE IF EXISTS income;
DROP TABLE IF EXISTS savings;
DROP TABLE IF EXISTS user;

CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(64) NOT NULL,
                      password VARCHAR(64) NOT NULL,
                      createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                      updatedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      deletedAt TIMESTAMP NULL DEFAULT NULL
);

CREATE TABLE savings (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         user_id INT NOT NULL,
                         amount DOUBLE NOT NULL,
                         description VARCHAR(128) NOT NULL,
                         createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updatedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         deletedAt TIMESTAMP NULL DEFAULT NULL,
                         FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE income (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        user_id INT NOT NULL,
                        amount DOUBLE NOT NULL,
                        source VARCHAR(128) NOT NULL,
                        createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updatedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        deletedAt TIMESTAMP NULL DEFAULT NULL,
                        FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE expense (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         user_id INT NOT NULL,
                         amount DOUBLE NOT NULL,
                         type VARCHAR(256) NOT NULL,
                         createdAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updatedAt TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                         deletedAt TIMESTAMP NULL DEFAULT NULL,
                         FOREIGN KEY (user_id) REFERENCES user(id)
);


INSERT INTO user (username, password, createdAt, updatedAt) VALUES
                                                                ('test', 'test123', '2024-09-01 08:00:00', '2024-09-01 08:00:00');

INSERT INTO savings (user_id, amount, description, createdAt, updatedAt) VALUES
                                                                             (1, 1000.00, 'Emergency Fund', '2024-09-05 09:00:00', '2024-09-05 09:00:00'),
                                                                             (1, 500.00, 'Vacation Savings', '2024-09-10 09:00:00', '2024-09-10 09:00:00'),
                                                                             (1, 1500.00, 'Home Renovation', '2024-09-15 09:00:00', '2024-09-15 09:00:00'),
                                                                             (1, 2000.00, 'New Car Fund', '2024-09-20 09:00:00', '2024-09-20 09:00:00');

INSERT INTO income (user_id, amount, source, createdAt, updatedAt) VALUES
                                                                       (1, 2500.00, 'Salary', '2024-09-01 08:00:00', '2024-09-01 08:00:00'),
                                                                       (1, 150.00, 'Freelancing', '2024-09-15 08:00:00', '2024-09-15 08:00:00'),
                                                                       (1, 3000.00, 'Salary', '2024-09-01 08:00:00', '2024-09-01 08:00:00'),
                                                                       (1, 200.00, 'Investment', '2024-09-10 08:00:00', '2024-09-10 08:00:00'),
                                                                       (1, 2800.00, 'Salary', '2024-09-01 08:00:00', '2024-09-01 08:00:00'),
                                                                       (1, 300.00, 'Side Business', '2024-09-20 08:00:00', '2024-09-20 08:00:00');

INSERT INTO expense (user_id, amount, type, createdAt, updatedAt) VALUES
                                                                      (1, 200.00, 'GROCERY', '2024-09-03 10:00:00', '2024-09-03 10:00:00'),
                                                                      (1, 100.00, 'OTHERS', '2024-09-07 10:00:00', '2024-09-07 10:00:00'),
                                                                      (1, 150.00, 'FOOD', '2024-09-12 10:00:00', '2024-09-12 10:00:00'),
                                                                      (1, 6500.00, 'RENT', '2024-09-01 10:00:00', '2024-09-01 10:00:00'),
                                                                      (1, 500.00, 'INTERNET', '2024-09-05 10:00:00', '2024-09-05 10:00:00'),
                                                                      (1, 50.00, 'OTHERS', '2024-09-20 10:00:00', '2024-09-20 10:00:00'),
                                                                      (1, 300.00, 'FOOD', '2024-09-01 10:00:00', '2024-09-01 10:00:00'),
                                                                      (1, 1300.00, 'ELECTRICITY', '2024-09-10 10:00:00', '2024-09-10 10:00:00'),
                                                                      (1, 100.00, 'FOOD', '2024-09-18 10:00:00', '2024-09-18 10:00:00');
