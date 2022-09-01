create table departments(
    id serial primary key,
    name varchar(100)
);

create table employees(
    id serial primary key,
    name varchar(100),
    department_id int references departments(id)
);

insert into departments(name) values ('Headoffice'), ('Finance'), ('Support'), ('PR');

insert into employees(name, department_id) values ('Ivan Ivanov', 1), ('Petr Petrov', 1),
('Stepan Stepanov', 3), ('Jhon Smith', 3), ('Tony Oneil', 3), ('Jessika Parker', 4);

select * from employees e left join departments d on e.department_id = d.id;

select * from employees e right join departments d on e.department_id = d.id;

select * from employees e full join departments d on e.department_id = d.id;

select * from employees e cross join departments d;

select * from departments d left join employees e on e.department_id = d.id where e.id is null;

select * from employees e left join departments d on e.department_id = d.id;
select * from departments d right join employees e  on e.department_id = d.id;
