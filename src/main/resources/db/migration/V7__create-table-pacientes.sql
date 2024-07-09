create table pacientes(

    id bigserial not null,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    telefone varchar(20) not null,
    cpf varchar(14) not null unique,
    logradouro varchar(100) not null,
    numero varchar(20) not null,
    complemento varchar(100),
    bairro varchar(100) not null,
    cidade varchar(100) not null,
    uf varchar(2) not null,
    cep varchar(9) not null,
    disponivel boolean not null,

    primary key (id)

);