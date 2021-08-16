create table users(
	id serial primary key,
	name varchar(255),
	birthdate date,
	access text,
	sex char(1)
);
insert into users (name,birthdate,access,sex) values ('oleg','20/09/1973','admin','m');
select * from users;
update users set name = 'vasya';
select * from users;
delete from users;
select * from users;