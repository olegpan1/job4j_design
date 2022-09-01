create table roles(
    id serial primary key,
    role varchar (100)
);

create table users(
    id serial primary key,
    username varchar (100),
    role_id int references roles(id)
);

insert  into roles(role) values ('admin');
insert  into roles(role) values ('user');
insert  into roles(role) values ('guest');

insert  into users(username, role_id) values ('Vasya Petrov', 1);
insert  into users(username, role_id) values ('Petya Ivanov', 2);

insert  into users(username) values ('Masha Sidorova');

select u.username, r.role from users as u join roles as r on u.role_id = r.id;
select u.username, r.role from users as u join roles as r on u.role_id != r.id;
select u.username as Имя, r.role as Роль from users as u join roles as r on u.role_id = r.id;