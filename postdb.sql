create database surl_dev;
drop database surl_dev;

select datname from pg_database;

create table post (
                      id bigserial not null,
                      primary key (id),
                      create_date timestamp(6),
                      modify_date timestamp(6),
                      title varchar(255)
);

-- # V1
insert into post
(id, create_date, modify_date, title)
values
    (default, NOW(), NOW(), '제목 1');

-- # v2
insert into post
(create_date, modify_date, title)
values
    (NOW(), NOW(), '제목 1');

select P.*
from post AS P
where upper(P.title) like upper('%당근%') escape '\'
order by P.id desc
    limit 10 offset 0;

select count(P.id)
from post AS P
where upper(P.title) like upper('%당근%') escape '\';

select * from surl;
select * from article;
select * from member;