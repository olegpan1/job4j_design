create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    expired_date date,
    price money,
    type_id int references type
);

insert into type(name) values ('СЫР'), ('МОЛОКО'), ('МОРОЖЕНОЕ');

insert into product(name, expired_date, price, type_id) values ('Сыр 1', '01-08-2021', 1000.45, 1),
('Сыр 2', '01-09-2021', 900.90, 1),('Сыр 3', '01-08-2022', 1500.55, 1),
('Сыр 4', '01-10-2021', 800.5, 1),('Сыр 5', '01-04-2021', 2000.99, 1);

insert into product(name, expired_date, price, type_id) values ('Молоко 1', '01-09-2021', 100.45, 2),
('Молоко 2', '23-09-2021', 90.90, 2),('Молоко 3', '01-08-2021', 110.45, 2),
('Молоко 4', '01-10-2021', 80.5, 2);

insert into product(name, expired_date, price, type_id) values ('Шоколадное мороженое', '01-09-2021', 50.88, 3),
('Эскимо"', '23-09-2021', 99.99, 3);

select p.name as Все_продукты_с_типом_СЫР from product as p join type as t on p.type_id = t.id   where t.name like 'СЫР';

select name as Все_продукты_со_словом_морженое from product  where name like '%мороженое%';

select name as Просроченные_продукты from product  where expired_date < current_date;

select name as Самый_дорогой_продукт from product  where price = (select max(price) from product);

select t.name as Тип, count(p.name) as Общее_количество from product as p join type as t on p.type_id= t.id group by t.name;

select p.name as Продукты_с_типом_СЫР_и_МОЛОКО from product as p join type as t on p.type_id = t.id   where t.name like 'СЫР'
or t.name like 'МОЛОКО';

select t.name as Тип_продуктов_меньше_10, count(p.name) from product as p join type as t on p.type_id= t.id group by t.name
having count(p.name) < 10;

select t.name as ТИП, p.name as ПРОДУКТЫ from product as p join type as t on p.type_id= t.id;