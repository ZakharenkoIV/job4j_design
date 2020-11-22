create database experts;

create table role
(
    id   serial primary key,
    name varchar(2000)
);

create table users
(
    id      serial primary key,
    name    varchar(2000),
    role_id int references role (id)
);

create table rules
(
    id   serial primary key,
    name varchar(2000)
);

create table role_rules
(
    id       serial primary key,
    role_id  int references role (id),
    rules_id int references rules (id)
);

create table category
(
    id   serial primary key,
    name varchar(2000)
);

create table state
(
    id   serial primary key,
    name varchar(2000)
);

create table item
(
    id          serial primary key,
    name        varchar(2000),
    users_id    int references users (id),
    category_id int references category (id),
    state_id    int references state (id)
);

create table comments
(
    id      serial primary key,
    name    varchar(2000),
    item_id int references item (id)
);

create table attach
(
    id      serial primary key,
    name    varchar(2000),
    item_id int references item (id)
);

insert into role(name)
values ('Роль 1');
insert into users(name)
values ('Пользователь 1');
insert into rules(name)
values ('Права роли 1');
insert into category(name)
values ('Категория заявки 1');
insert into state(name)
values ('Состояние заявки 1');
insert into item(name)
values ('Заявка 1');
insert into comments(name)
values ('Комментарий заявки 1');
insert into attach(name)
values ('Приложенный файл заявки 1');
