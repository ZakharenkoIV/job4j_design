insert into body(vin, type)
values (7775618, 'sedan');
insert into body(vin, type)
values (7778465, 'pickup');

insert into engine(vin, fuel_type)
values (3335498, 'gasoline');
insert into engine(vin, fuel_type)
values (3335198, 'diesel');

insert into gearbox(number, type)
values (9998998, 'mechanical');
insert into gearbox(number, type)
values (9994166, 'automatic');

insert into machine(brand, body_VIN, engine_VIN, gearbox_number)
values ('Volvo', 7778465, 3335498, 9994166);

insert into machine(brand, body_VIN, engine_VIN, gearbox_number)
values ('Opel', 7778465, 3335498, 9998998);

insert into machine(brand, body_VIN, engine_VIN, gearbox_number)
values ('Volkswagen', 7778465, 3335498, 9994166);

insert into machine(brand, body_VIN, engine_VIN, gearbox_number)
values ('Bentley', 7778465, 3335498, 9994166);

insert into machine(brand, body_VIN, engine_VIN, gearbox_number)
values ('Lexus', 7778465, 3335498, 9998998);


