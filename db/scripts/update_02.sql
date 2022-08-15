CREATE TABLE IF NOT EXISTS categories (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(128) UNIQUE
);

CREATE TABLE IF NOT EXISTS items_categories (
    items_id int NOT NULL ,
    categories_id int NOT NULL
);

ALTER TABLE items_categories
ADD CONSTRAINT items_id_constraint
FOREIGN KEY (items_id) REFERENCES items(id);

ALTER TABLE items_categories
ADD CONSTRAINT categories_id_constraint
FOREIGN KEY (categories_id) REFERENCES categories(id);

INSERT INTO categories (name) VALUES ('Работа');
INSERT INTO categories (name) VALUES ('Дом');
INSERT INTO categories (name) VALUES ('Учеба');

