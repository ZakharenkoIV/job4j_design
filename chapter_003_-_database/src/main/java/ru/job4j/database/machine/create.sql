create database machines;

create table body
(
    VIN  int primary key,
    type varchar(255)
);

create table engine
(
    VIN       int primary key,
    fuel_type varchar(255)
);

create table gearbox
(
    number int primary key,
    type   varchar(255)
);

create table machine
(
    brand          varchar(255),
    body_VIN       int references body (VIN),
    engine_VIN     int references engine (VIN),
    gearbox_number int references gearbox (number)
);

