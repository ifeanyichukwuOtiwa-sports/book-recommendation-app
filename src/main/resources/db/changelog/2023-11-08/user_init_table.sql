-- liquibase formatted sql

-- changeset ifeanyichukwuOtiwa-sports:init-table-book

CREATE TABLE IF NOT EXISTS user(
    id BINARY(16) PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(500) NOT NULL,
    access_role_id INT NOT NULL
);