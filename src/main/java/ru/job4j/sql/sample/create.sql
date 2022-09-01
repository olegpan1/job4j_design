create table roles(
    id serial primary key,
    role varchar (100)
);

create table users(
    id serial primary key,
    username varchar (100),
    role_id int references roles(id)
);

create table rules(
    id serial primary key,
    rule varchar (100)
);

create table role_rules(
    id serial primary key,
    role_id int references roles(id),
    rule_id int references rules(id)
);

create table categorys(
    id serial primary key,
    category char(2)
);

create table states(
    id serial primary key ,
    state varchar (20)
);
create table items(
    id serial primary key,
    item varchar (255),
    user_id int references users(id),
    category_id int references categorys(id),
    state_id int references states(id)
);

create table comments(
    id serial primary key,
    comment varchar (255),
    item_id int references items(id)
);

create table attaches(
    id serial primary key,
    attach json ,
    item_id int references items(id)
);
