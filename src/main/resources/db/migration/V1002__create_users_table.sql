CREATE TABLE users (
                       id serial PRIMARY KEY,
                       email VARCHAR(100) NOT NULL,
                       password VARCHAR(200) NOT NULL,
                       enabled boolean NOT NULL,
                       role VARCHAR(30) NOT NULL
)