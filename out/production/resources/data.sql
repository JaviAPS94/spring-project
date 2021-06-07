insert into country (id, name, created_at, updated_at) values (1, 'United States', NOW(), NOW());
insert into country (id, name, created_at, updated_at) values(2, 'Ecuador', NOW(), NOW());
insert into country (id, name, created_at, updated_at) values(3, 'Spain', NOW(), NOW());

insert into city (id, name, country_id, created_at, updated_at) values (1, 'Miami', 1, NOW(), NOW());
insert into city (id, name, country_id, created_at, updated_at) values (2, 'New York', 1, NOW(), NOW());
insert into city (id, name, country_id, created_at, updated_at) values (3, 'Quito', 2, NOW(), NOW());
insert into city (id, name, country_id, created_at, updated_at) values (4, 'Guayaquil', 2, NOW(), NOW());
insert into city (id, name, country_id, created_at, updated_at) values (5, 'Barcelona', 3, NOW(), NOW());
insert into city (id, name, country_id, created_at, updated_at) values (6, 'Valencia', 3, NOW(), NOW());