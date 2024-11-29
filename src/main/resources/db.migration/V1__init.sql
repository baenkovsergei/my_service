CREATE TABLE if not exists car (
    id INTEGER PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE if not exists category (
    id INTEGER PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE if not exists car_category (
    car_id INTEGER NOT NULL,
    category_id INTEGER NOT NULL,
    PRIMARY KEY (car_id, category_id),
    CONSTRAINT fk_car FOREIGN KEY (car_id) REFERENCES car (id) ON DELETE CASCADE,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES category (id) ON DELETE CASCADE
);

CREATE TABLE users (
    id INTEGER PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE comments(
    id INTEGER PRIMARY KEY,
    comment VARCHAR NOT NULL,
    user_id INTEGER NOT NULL,
    car_id INTEGER NOT NULL,
    CONSTRAINT fk_users FOREIGN KEY(user_id) REFERENCES users(id),
    CONSTRAINT fk_car FOREIGN KEY(car_id) REFERENCES car(id)
);



