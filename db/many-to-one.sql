create table type(
    id serial primary key,
    name varchar(50)
);

create table car(
    id serial primary key,
    model varchar(255),
	owner varchar(255),
    type_id int references type(id),
	power int,
	number varchar(255)
);

insert into type(name) values ('cabriolet');
insert into type(name) values ('SUV');
insert into car(model, owner, type_id, power, number) values ('Porsche 911', 'Vasya Petrov', 1, 500, 'x000xx00');

select * from car;
select * from type where id in (select id from car);

