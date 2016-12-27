DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO meals (description, user_id, date_time, calories)
VALUES ('Завтрак для админа', 100000, '2016-12-24 06:00:00', 500);

INSERT INTO meals (description, user_id, date_time, calories)
VALUES ('Обед для админа', 100000, '2016-12-24 12:00:00', 750);

INSERT INTO meals (description, user_id, date_time, calories)
VALUES ('Ужин', 100000, '2016-12-24 18:00:00', 500);

INSERT INTO meals (description, user_id, date_time, calories)
VALUES ('Завтрак - доширак', 100001, '2016-12-24 06:00:00', 150);

INSERT INTO meals (description, user_id, date_time, calories)
VALUES ('Обед - Утка по пекински', 100001, '2016-12-24 12:00:00', 800);

INSERT INTO meals (description, user_id, date_time, calories)
VALUES ('Ужин - Мясо по-тайски', 100001, '2016-12-24 19:10:00', 1250);

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);
