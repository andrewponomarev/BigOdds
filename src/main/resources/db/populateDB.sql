DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);


INSERT INTO bets (event, value, currency, return_sum, net_profit, date_time, is_express, user_id)
VALUES ('Admin bet 1', 0.0, '123', 0.0, 0.0, 0.0,'2015-05-30 10:00:00', true, 100001),
       ('Admin bet 2', 0.0, '123', 0.0, 0.0, 0.0,'2015-05-30 20:00:00', true, 100001);

