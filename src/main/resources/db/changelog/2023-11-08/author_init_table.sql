-- liquibase formatted sql

-- changeset ifeanyichukwuOtiwa-sports:init-table-author

CREATE TABLE IF NOT EXISTS author(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);