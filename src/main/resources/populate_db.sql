DELETE FROM students;
ALTER SEQUENCE global_seq RESTART WITH 1;

-- INSERT INTO students
-- (id, name, surname, point)
-- VALUES (1, 'Alex', 'Mayson', 4),
--        (2, 'Jayems', 'Gunn', 5),
--        (3, 'Silvester', 'Stalone', 3),
--        (4, 'Alec', 'Bolduine', 2);

INSERT INTO students
(name, surname, point)
VALUES ('Alex', 'Mayson', 4),
       ('Jayems', 'Gunn', 5),
       ('Silvester', 'Stalone', 3),
       ('Alec', 'Bolduine', 2);