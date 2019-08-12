DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM bets;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password, second_name, first_name, phone_number) VALUES
  ('user', 'email@mail.com', '{noop}password', 'secondName', 'firstName', 'phoneNumber'),
  ('admin', 'admin@mail.com', '{noop}password', 'secondName', 'firstName', 'phoneNumber');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);


INSERT INTO bets (event, value, currency, return_sum, net_profit, coefficient, date_time, express, user_id)
VALUES ('Россия - Англия', 123, '123', 123, 123, 1.23, '2015-05-30 10:00:00', false, 100000),
       ('Россия - Парагвай', 321, '321', 321, 321, 3.21, '2015-05-30 15:00:00', false, 100000),
       ('Парагвай - Англия', 222, '222', 222, 222, 2.22, '2015-05-30 20:00:00', false, 100000),
       ('Admin bet 1', 0.0, '123', 0.0, 0.0, 0.0,'2015-05-30 10:00:00', true, 100001),
       ('Admin bet 2', 0.0, '123', 0.0, 0.0, 0.0,'2015-05-30 20:00:00', true, 100001);

