CREATE DATABASE micro_service_learning;
CREATE USER 'admin'@'localhost' IDENTIFIED BY '123456';
GRANT ALL PRIVILEGES ON micro_service_learning.* TO 'admin'@'localhost';

use micro_service_learning;

create table client_user(
    id bigint auto_increment primary key,
    username varchar(100),
    password varchar(50),
    access_token varchar(100) NULL,
    access_token_validity datetime NULL,
    refresh_token varchar(100) NULL
);

insert into client_user
(username, password)
value
('admin', '123456');