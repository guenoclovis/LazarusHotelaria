CREATE TABLE cliente(
        id SERIAL NOT NULL,
        nome varchar(45) NOT NULL,
        cpf varchar(11),
        sobrenome varchar(45),
        endereco varchar(45),
        int ativo
);

ALTER TABLE cliente ADD PRIMARY KEY (id);

