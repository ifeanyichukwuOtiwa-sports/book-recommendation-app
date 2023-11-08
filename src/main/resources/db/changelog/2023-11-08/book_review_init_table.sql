-- liquibase formatted sql

-- changeset ifeanyichukwuOtiwa-sports:init-table-book

CREATE TABLE IF NOT EXISTS book_review(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user BINARY(16) NOT NULL,
    reviews VARCHAR(255) NOT NULL,
    book_id BIGINT NOT NULL
);