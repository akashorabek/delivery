CREATE TABLE baskets (
    id serial PRIMARY KEY,
    user_id int NOT NULL,
    place_id int NOT NULL,
    sum float NOT NUll,
    FOREIGN KEY (user_id)
    REFERENCES users(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (place_id)
    REFERENCES places(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE basket_items (
    id serial PRIMARY KEY,
    basket_id int NOT NULL,
    food_id int NOT NULL,
    count int NOT NULL,
    FOREIGN KEY (basket_id)
    REFERENCES baskets(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (food_id)
    REFERENCES foods(id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);