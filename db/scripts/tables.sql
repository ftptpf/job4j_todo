CREATE TABLE IF NOT EXISTS items (
    id SERIAL PRIMARY KEY ,
    description TEXT ,
    created TIMESTAMP ,
    done BOOLEAN ,
    user_id int NOT NULL REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(128) ,
    login VARCHAR(128) UNIQUE ,
    password VARCHAR(128)
);
