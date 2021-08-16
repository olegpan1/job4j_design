create table number(
    id serial primary key,
	region int,
    number char(6)
);

create table cars(
    id serial primary key,
    model varchar(255),
	owner varchar(255),
    type_id int references type(id),
	power int,
	number_id int references number(id) unique
);

insert into number(region, number) values (177, 'x111xx');
insert into number(region, number) values (154, 'x333xx');
insert into cars(model, owner, type_id, power, number_id) values ('Porsche 911', 'Vasya Petrov', 1, 500, 2);

select * from cars;
select * from number where id in (select number_id from cars);


