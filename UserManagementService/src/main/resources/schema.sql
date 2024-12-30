CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      first_name VARCHAR(255) NOT NULL,
                      last_name VARCHAR(255) NOT NULL,
                      email VARCHAR(255) UNIQUE NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      city VARCHAR(255),
                      country VARCHAR(255),
                      phone_number VARCHAR(20),
                      address VARCHAR(255),
                      postal_code VARCHAR(20),
                      gender VARCHAR(10),
                      role VARCHAR(50)
);

CREATE TABLE ticket (
                        ticket_id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        departure VARCHAR(255) NOT NULL,
                        destination VARCHAR(255) NOT NULL,
                        gate VARCHAR(10),
                        date DATE NOT NULL,
                        seat VARCHAR(10),
                        boarding_time DATETIME,
                        user_id INT NOT NULL, -- Verknüpft das Ticket mit dem User
                        FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE -- Fremdschlüsselreferenz zur Tabelle 'user'
);