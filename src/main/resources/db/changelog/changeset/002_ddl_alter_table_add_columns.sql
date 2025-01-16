--liquibase formatted sql
--changeset spoletaev:alters_table_add_columns
alter table users
add column first_arg INTEGER,
add column second_arg INTEGER,
add column result DOUBLE PRECISION;

