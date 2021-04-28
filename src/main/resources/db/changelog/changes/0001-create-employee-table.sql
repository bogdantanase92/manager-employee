--liquibase formatted sql

--changeset manager-employee:1

CREATE TABLE employee
(
    id              VARCHAR(255)            NOT NULL,
    name            VARCHAR(255)            NOT NULL,
    email           VARCHAR(255)            NOT NULL,
    contract_type   VARCHAR(255)            NOT NULL,
    department      VARCHAR(255)            NOT NULL,
    state           VARCHAR(255)            NOT NULL,
    PRIMARY KEY (id)
);

