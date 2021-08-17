insert  into roles(role) values ('admin');
insert  into roles(role) values ('user');
insert  into roles(role) values ('guest');

insert  into users(username, role_id) values ('Vasya Petrov', 1);
insert  into users(username, role_id) values ('Petya Ivanov', 2);
insert  into users(username, role_id) values ('Masha Sidorova', 3);

insert  into rules(rule) values ('Full access');
insert  into rules(rule) values ('Limited access');
insert  into rules(rule) values ('Only read');

insert  into role_rules(role_id, rule_id) values (1, 1);
insert  into role_rules(role_id, rule_id) values (2, 2);
insert  into role_rules(role_id, rule_id) values (3, 3);

insert  into categorys(category) values ('AA');
insert  into categorys(category) values ('AB');
insert  into categorys(category) values ('BB');
insert  into categorys(category) values ('CC');

insert  into states(state) values ('In work');
insert  into states(state) values ('Completed');
insert  into states(state) values ('On check');

insert  into items(item, user_id, category_id, state_id) values ('Do something', 1, 1, 3);
insert  into items(item, user_id, category_id, state_id) values ('To do nothing', 2, 1, 2);
insert  into items(item, user_id, category_id, state_id) values ('Make changes', 3, 2, 1);

insert  into comments( comment, item_id) values ('I will do it tomorrow', 1);
insert  into comments( comment, item_id) values ('What are the requirements', 2);
insert  into comments( comment, item_id) values ('Doing', 3);

insert  into attaches(attach, item_id) values ('{"inStock":true,"dateOfManufacture":2010}', 2);
insert  into attaches(attach, item_id) values ('{"contactDealer":{"phone":"0-123-456"}}', 1);