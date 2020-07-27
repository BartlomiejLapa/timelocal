DROP TABLE IF EXISTS "role";
CREATE TABLE "role" (
  id bigserial PRIMARY KEY NOT NULL,
  name varchar(255) DEFAULT NULL

);

DROP TABLE IF EXISTS "user";
CREATE TABLE "user" (
  id bigserial  PRIMARY KEY NOT NULL,
  username varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL
);

DROP TABLE IF EXISTS "users_roles";
CREATE TABLE "users_roles" (
  user_id bigserial NOT NULL,
  role_id bigserial NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT "user_id" FOREIGN KEY (user_id) REFERENCES "user" (id),
  CONSTRAINT "role_id" FOREIGN KEY (role_id) REFERENCES "role" (id)
);