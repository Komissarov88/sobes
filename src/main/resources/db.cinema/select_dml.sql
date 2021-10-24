-- ошибки в расписании
with movies as (
    select movie.id                       as id,
           movie.name                     as name,
           ms.start_time                  as start_time,
           ms.start_time + movie.duration as end_time,
           movie.duration                 as duration
    from movie
             join movie_session ms on movie.id = ms.movie_id
    order by start_time
)
select name, start_time, duration
from movies as a
where exists(
              select id
              from movies as b
              where a.start_time > b.start_time
                and a.start_time <= b.end_time);


-- перерывы 30 минут и более между фильмами — выводить по уменьшению длительности перерыва.
-- Колонки «фильм 1», «время начала», «длительность», «время начала второго фильма», «длительность перерыва»
with movies as (
    select row_number() over ()           as row_number,
           movie.id                       as id,
           movie.name                     as name,
           ms.start_time                  as start_time,
           ms.start_time + movie.duration as end_time,
           movie.duration                 as duration
    from movie
             join movie_session ms on movie.id = ms.movie_id
    order by start_time
)
select f.name,
       f.start_time,
       f.duration,
       s.start_time              as begin_time,
       s.start_time - f.end_time as break
from movies as f
         join (select row_number,
                      start_time
               from movies) as s
              on f.row_number = s.row_number - 1;


-- список фильмов, для каждого — с указанием общего числа посетителей за все время,
-- среднего числа зрителей за сеанс и общей суммы сборов по каждому фильму (отсортировать по убыванию прибыли).
-- Внизу таблицы должна быть строчка «итого», содержащая данные по всем фильмам сразу

with counts as (
    with cinema as (
        select movie_id,
               name,
               session_id,
               ticket_id,
               ticket_price
        from movie
                 left join
             (
                 select ticket.id as ticket_id,
                        session_id,
                        movie_id,
                        ticket_price
                 from ticket
                          join movie_session on movie_session.id = ticket.session_id
             ) as with_tickets
             on with_tickets.movie_id = movie.id
    )
    select movie_id, sum(total) visitors, avg(total) avg_visitors, sum(total * ticket_price) as income
    from (
             select movie_id,
                    count(ticket_id) as total,
                    ticket_price
             from cinema
             group by movie_id, session_id, ticket_price) ms
    group by movie_id
    order by income desc)
select *
from counts
union
select null as movie_id,
       sum(visitors),
       null,
       sum(income)
from counts;


-- число посетителей и кассовые сборы, сгруппированные по времени начала фильма:
-- с 9 до 15, с 15 до 18, с 18 до 21, с 21 до 00:00 (сколько посетителей пришло с 9 до 15 часов и т.д.)

with cinema as (
    select ticket_id,
           ticket_price,
           start_time
    from movie
             left join
         (
             select ticket.id as ticket_id,
                    movie_id,
                    ticket_price,
                    start_time
             from ticket
                      join movie_session on movie_session.id = ticket.session_id
         ) as with_tickets
         on with_tickets.movie_id = movie.id
)
select count(ticket_id) visitors,
       sum(ticket_price) income,
       case
           when start_time::time between '09:00' and '14:59' then '09-15'
           when start_time::time between '15:00' and '17:59' then '15-18'
           when start_time::time between '18:00' and '20:59' then '18-21'
           when start_time::time between '21:00' and '23:59' then '21-00'
           end as time
from cinema
group by time
order by time;