-- liquibase formatted sql

-- changeset ifeanyichukwuOtiwa-sports:init-table-book

CREATE TABLE IF NOT EXISTS book(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    isbn VARCHAR(255) NOT NULL,
    review_count INTEGER,
    rating DECIMAL(3, 1) NOT NULL,
    author_id BIGINT NOT NULL
);