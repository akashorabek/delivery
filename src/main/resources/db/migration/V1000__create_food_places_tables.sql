CREATE TABLE places (
                        id serial PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        image VARCHAR(250) NOT NULL,
                        description VARCHAR(250) NOT NULL
);

CREATE TABLE foods (
                       id serial PRIMARY KEY,
                       place_id int NOT NULL,
                       name VARCHAR(100) NOT NULL,
                       price float NOT NULL,
                       description VARCHAR(250) NOT NULL,
                       CONSTRAINT fk_place
                           FOREIGN KEY(place_id)
                               REFERENCES places(id)
                               ON DELETE RESTRICT
                               ON UPDATE CASCADE
);