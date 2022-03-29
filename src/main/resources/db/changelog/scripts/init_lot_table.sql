CREATE TABLE IF NOT EXISTS lot (
    id          bigserial PRIMARY KEY,
    name        text      NOT NULL,
    description text      NOT NULL
);
