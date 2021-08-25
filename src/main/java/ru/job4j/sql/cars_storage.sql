create table bodys(
    id serial primary key,
    name varchar(50)
);

create table engines(
    id serial primary key,
    name varchar(50)
);

create table gearboxes(
    id serial primary key,
    name varchar(50)
);

create table cars(
    id serial primary key,
    name varchar(50),
    body_id int references bodys(id),
    engine_id int references engines(id),
    gearbox_id int references gearboxes(id)
);

insert into bodys(name) values ('Body 1'), ('Body 2'), ('Body 3'), ('Body 4');

insert into engines(name) values ('Engine 1'), ('Engine 2'), ('Engine 3'), ('Engine 4');

insert into gearboxes(name) values ('Gearbox 1'), ('Gearbox 2'), ('Gearbox 3'), ('Gearbox 4');

insert into cars(name, body_id, engine_id, gearbox_id) values  ('Car 1', 1, 1, 1), ('Car 2', 1, 2, 3);

select * from cars;

select * from bodys as b left join cars c on b.id = c.body_id where c.id is null;

select * from engines as e left join cars c on e.id = c.engine_id where c.id is null;

select * from gearboxes as g left join cars c on g.id = c.gearbox_id where c.id is null;
