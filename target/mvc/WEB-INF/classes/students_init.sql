DROP TABLE IF EXISTS student;

CREATE SEQUENCE global_seq START WITH 1;

CREATE TABLE students
(
        id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
        name            VARCHAR NOT NULL,
        surname         VARCHAR NOT NULL,
        point           INTEGER NOT NULL
)
