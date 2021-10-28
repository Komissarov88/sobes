drop table if exists student;
create table student
(
    id   serial,
    name varchar DEFAULT NULL,
    mark varchar DEFAULT NULL,
    PRIMARY KEY (id)
);