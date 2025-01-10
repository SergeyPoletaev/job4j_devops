--liquibase formatted sql
--changeset spoletaev:create_users_table
create TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(2000)
);

