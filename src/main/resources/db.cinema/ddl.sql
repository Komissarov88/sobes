drop table if exists ticket;
drop table if exists movie_session;
drop table if exists movie;


create table movie(
    id serial,
    name varchar,
    duration time,
    primary key(id)
);

drop table if exists movie_session;
create table movie_session(
    id serial,
    movie_id integer,
    start_time timestamp,
    ticket_price money,
    primary key(id),
    constraint fk_movie foreign key (movie_id) references movie(id) on delete set null
);

drop table if exists ticket;
create table ticket(
    id serial,
    session_id integer,
    primary key (id),
    constraint fk_session foreign key (session_id) references movie_session(id) on delete set null
);