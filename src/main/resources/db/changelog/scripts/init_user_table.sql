CREATE TABLE IF NOT EXISTS person (
    id          bigserial PRIMARY KEY,
    first_name  varchar(320)        NOT NULL,
    last_name   varchar(320)        NOT NULL,
    email       varchar(320) UNIQUE NOT NULL
);