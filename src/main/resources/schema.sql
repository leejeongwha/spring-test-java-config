CREATE TABLE IF NOT EXISTS board_user ( 
 id integer primary key,
 passwd varchar(40) not null,
 user_name varchar(30) not null,
 age integer not null,
 role varchar(15) not null
);

INSERT INTO board_user (id, passwd, user_name, age, role) VALUES (1, 'passwd1', 'tom', 27, 'ADMIN');
INSERT INTO board_user (id, passwd, user_name, age, role) VALUES (2, 'passwd2', 'jack', 19, 'GUEST');
INSERT INTO board_user (id, passwd, user_name, age, role) VALUES (3, 'passwd3', 'jane', 92, 'ADMIN');
INSERT INTO board_user (id, passwd, user_name, age, role) VALUES (4, 'passwd4', 'jinsu', 59, 'GUEST');
INSERT INTO board_user (id, passwd, user_name, age, role) VALUES (5, 'passwd5', 'flower', 89, 'ADMIN');
INSERT INTO board_user (id, passwd, user_name, age, role) VALUES (6, 'passwd6', 'sun', 9, 'DEV');
INSERT INTO board_user (id, passwd, user_name, age, role) VALUES (7, 'passwd7', 'apple', 49, 'OPERATOR');
INSERT INTO board_user (id, passwd, user_name, age, role) VALUES (8, 'passwd8', 'orange', 46, 'ADMIN');
INSERT INTO board_user (id, passwd, user_name, age, role) VALUES (9, 'passwd9', 'tom', 47, 'ADMIN');
INSERT INTO board_user (id, passwd, user_name, age, role) VALUES (10, 'passwd10', 'coffee', 28, 'ADMIN');
