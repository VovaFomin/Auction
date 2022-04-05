CREATE TABLE IF NOT EXISTS lot (
    id          bigserial PRIMARY KEY,
    title       varchar(100) NOT NULL,
    description text,
    start_price bigint,
    max_bid_rate_step bigint,
    start_time  timestamp,
    end_time    timestamp
);
