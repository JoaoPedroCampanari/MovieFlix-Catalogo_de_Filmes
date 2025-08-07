create table users(
    id UUID PRIMARY KEY,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null
);