CREATE TABLE IF NOT EXISTS items (
    id SERIAL PRIMARY KEY ,
    description TEXT ,
    created TIMESTAMP ,
    done BOOLEAN
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(128) ,
    login VARCHAR(128) UNIQUE ,
    password VARCHAR(128)
);

ALTER TABLE items ADD COLUMN id_user int REFERENCES users(id);