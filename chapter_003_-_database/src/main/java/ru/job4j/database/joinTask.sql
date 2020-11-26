create table departments
(
    department_id serial primary key,
    name          varchar(255)
);

create table employees
(
    department_id int references departments (department_id),
    name          varchar(255)
);

insert into departments(department_id, name)
values (1, 'Лигистика');
insert into departments(department_id, name)
values (2, 'Сбыт');
insert into departments(department_id, name)
values (3, 'Администрация');

insert into employees(department_id, name)
values (3, 'Иванов');
insert into employees(department_id, name)
values (1, 'Петров');
insert into employees(department_id, name)
values (3, 'Сидоров');
insert into employees(department_id, name)
values (1, 'Смирнов');
insert into employees(department_id, name)
values (2, 'Стрельцов');
insert into employees(department_id, name)
values (3, 'Гальцев');
insert into employees(department_id, name)
values (1, 'Терёхина');
insert into employees(department_id, name)
values (1, 'Зоткина');
insert into employees(department_id, name)
values (null, 'Чувашская');
insert into employees(department_id, name)
values (null, 'Тимофеевна');
insert into employees(department_id, name)
values (null, 'Ильинична');



select employees.name as name
from employees
         left join departments on departments.department_id = employees.department_id
where employees.department_id is null;

select *
from employees
         left join departments d on employees.department_id = d.department_id;

select *
from departments
         right join employees e on departments.department_id = e.department_id;



create table teens
(
    name   varchar(255),
    gender varchar(255)
);

insert into teens(name, gender)
values ('Маша', 'women');
insert into teens(name, gender)
values ('Егор', 'men');
insert into teens(name, gender)
values ('Света', 'women');
insert into teens(name, gender)
values ('Марина', 'women');
insert into teens(name, gender)
values ('Оксана', 'women');
insert into teens(name, gender)
values ('Тимофей', 'men');
insert into teens(name, gender)
values ('Дмитрий', 'men');
insert into teens(name, gender)
values ('Олег', 'men');
insert into teens(name, gender)
values ('Татьяна', 'women');
insert into teens(name, gender)
values ('Николай', 'men');
insert into teens(name, gender)
values ('Динис', 'men');
insert into teens(name, gender)
values ('Павел', 'men');
insert into teens(name, gender)
values ('Константин', 'men');
insert into teens(name, gender)
values ('Эдуард', 'men');
insert into teens(name, gender)
values ('Кристина', 'women');
insert into teens(name, gender)
values ('Максим', 'men');



select t1.name as men, t2.name as women
from teens t1
         cross join teens t2
where t1.gender = 'men'
  and t1.gender != t2.gender;