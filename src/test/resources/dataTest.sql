insert into contact (id, name, description, created_at, updated_at) values (1, 'Test', 'Test', NOW(), NOW());

insert into phone (id, number, contact_id, created_at, updated_at) values (1, '0958963597', 1, NOW(), NOW());

insert into email (id, email, contact_id, created_at, updated_at) values (1, 'test@test.com', 1, NOW(), NOW());

insert into address (id, street, street_number, reference, contact_id, city_id, created_at, updated_at) values (1, 'test', '2313', 'test', 1, 1, NOW(), NOW());