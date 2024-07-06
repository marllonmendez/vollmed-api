create table usuarios(

    id bigserial not null,
    login varchar(100) not null,
    senha varchar(255) not null,

    primary key (id)

);