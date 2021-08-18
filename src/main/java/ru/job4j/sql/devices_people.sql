create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name, price) values ('Phone', 500.99), ('Computer', 3055.33), ('Laptop', 5999.45);
insert  into people (name) values ('Petya'), ('Vasya'), ('Katya');
insert  into devices_people (device_id, people_id) values (1, 1), (1, 2), (2, 1),  (3, 1), (3, 3);

select avg(price) from devices;

select p.name, avg (d.price) from people as p join devices_people as dp on p.id = dp.people_id join devices as d
on dp.device_id = d.id group by p.name;

select p.name, avg (d.price) from people as p join devices_people as dp on p.id = dp.people_id join devices as d
on dp.device_id = d.id group by p.name having avg (d.price) > 5000;