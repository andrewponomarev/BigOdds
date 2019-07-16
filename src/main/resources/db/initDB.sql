DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS bets;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                 NOT NULL,
  email            VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL,
  second_name       VARCHAR                         ,
  first_name        VARCHAR                         ,
  birth_day         TIMESTAMP DEFAULT now()         ,
  phone_number      VARCHAR
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE bets (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id     INTEGER   NOT NULL,
  date_time   TIMESTAMP NOT NULL,
  value       NUMERIC (20,3) NOT NULL,
  coefficient NUMERIC (20,3) NOT NULL,
  currency    TEXT      NOT NULL,
  event       TEXT       NOT NULL,
  net_profit  NUMERIC(20,3)        ,
  return_sum  NUMERIC(20,3)        ,
  express     BOOL DEFAULT FALSE ,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX bets_unique_user_datetime_idx
  ON bets (user_id, date_time);