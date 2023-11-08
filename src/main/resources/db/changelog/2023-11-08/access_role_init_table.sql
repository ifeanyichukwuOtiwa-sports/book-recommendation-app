-- liquibase formatted sql

-- changeset ifeanyichukwuOtiwa-sports:init_table_access_role

CREATE TABLE IF NOT EXISTS access_role(
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name ENUM('superadmin', 'jurisdiction_admin', 'user') DEFAULT 'user' NOT NULL
);