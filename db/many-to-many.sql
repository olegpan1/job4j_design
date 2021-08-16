create table services(
    id serial primary key,
	name varchar(255)
);

create table autos(
    id serial primary key,
   	number_id char(9)
);

create table autos_services(
    id serial primary key,
   	auto_id int references autos(id),
	service_id int references services(id)
);

insert into services(name) values ('FIT');
insert into services(name) values ('AService');

insert into autos(number_id) values ('x444xx777');
insert into autos(number_id) values ('x555xx555');
insert into autos(number_id) values ('x666xx666');

insert into autos_services(service_id, auto_id) values (1,2);
insert into autos_services(service_id, auto_id) values (1,3);
insert into autos_services(service_id, auto_id) values (2,1);
insert into autos_services(service_id, auto_id) values (2,2);

select * from autos_services;



