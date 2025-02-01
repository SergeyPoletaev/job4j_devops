--liquibase formatted sql
--changeset spoletaev:create_calc_events_table
CREATE TABLE calc_events (
    id serial PRIMARY KEY,
    first integer,
    second integer,
    result double precision,
    create_date date DEFAULT CURRENT_DATE,
    type varchar,
    user_id bigint REFERENCES users(id)
);

