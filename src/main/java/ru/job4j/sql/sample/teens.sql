create table teens(
    id serial primary key,
    name varchar(100),
    gender char
);

insert into teens (name, gender) values
('Ivan Ivanov', 'M'),
('Petr Petrov', 'M'),
('Stepan Stepanov', 'M'),
('Masha Voronova', 'F'),
('Katya Sinitsyna', 'F');


select t1.name, t2.name from teens t1 cross join teens t2 where t1.gender != t2.gender;