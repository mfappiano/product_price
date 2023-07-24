CREATE TABLE IF NOT EXISTS brand (
    id   BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS price (
    id          BIGINT PRIMARY KEY,
    brand_id    BIGINT NOT NULL,
    start_date  TIMESTAMP NOT NULL,
    end_date    TIMESTAMP NOT NULL,
    price_list  VARCHAR(255) NOT NULL,
    product_id  BIGINT NOT NULL,
    priority    INT NOT NULL,
    price       DECIMAL(10,2) NOT NULL,
    curr        VARCHAR(10) NOT NULL,
    CONSTRAINT FK55itppkw3i07do3h7qoclqd4k FOREIGN KEY (brand_id) REFERENCES brand(id)
);