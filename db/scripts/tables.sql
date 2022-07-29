CREATE TABLE IF NOT EXISTS items (
    id SERIAL PRIMARY KEY,
    description TEXT,
    created TIMESTAMP,
    done BOOLEAN
);

CREATE TABLE IF NOT EXISTS accounts (
    id SERIAL PRIMARY KEY,
    name VARCHAR(128) CHECK ( length(name) > 0 ),
    login VARCHAR(128) UNIQUE CHECK ( length(login) > 0 ),
    password VARCHAR(128) CHECK ( length(password) > 0 )
);