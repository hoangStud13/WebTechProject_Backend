
DROP DATABASE IF EXISTS WebTechProject;
CREATE DATABASE WebTechProject;
USE WebTechProject;

CREATE TABLE Ticket (
                        flight_Id int PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(255),
                        departure VARCHAR(255),
                        destination VARCHAR(255),
                        gate VARCHAR(50),
                        date DATE,
                        seat VARCHAR(10),
                        boarding_Time TIMESTAMP
);

