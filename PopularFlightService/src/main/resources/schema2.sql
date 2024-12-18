CREATE TABLE ImageService (
                              imageService_Id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              imageTitle VARCHAR(255) NOT NULL,
                              description TEXT,
                              imageUrl VARCHAR(255) NOT NULL
);



CREATE TABLE PopularFlightService (
                                      popularFlightServiceId int PRIMARY KEY AUTO_INCREMENT,
                                      iataCode VARCHAR(10) NOT NULL,
                                      country VARCHAR(255) NOT NULL,
                                      city VARCHAR(255) NOT NULL,
                                      departure VARCHAR(255) NOT NULL,
                                      destination VARCHAR(255) NOT NULL
);
