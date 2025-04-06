--liquibase formatted sql
--changeset spoletaev:create_calc_result_table
CREATE TABLE calc_result (
    id serial PRIMARY KEY,
    first_arg double precision,
    second_arg double precision,
    result double precision,
    create_date date,
    operation_type varchar
);

