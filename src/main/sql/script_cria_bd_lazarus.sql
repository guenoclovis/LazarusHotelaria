BEGIN;

----------------------------------------------------------------------
-- SQL DE CRIACAO E POPULACAO DE TABELAS - LAZARUS
----------------------------------------------------------------------
-- Seguir a ordem de criacao:
-- 		1 - TABELA DE USUARIOS
-- 		2 - TABELA DE CLIENTES
-- 		3 - TABELA DE ATRIBUTOS
--		4 - TABELA DE FILIAIS
--		5 - TABELA DE RELACIONAMENTO FILIAIS X ATRIBUTOS
--		6 - TABELA DE TIPOS DE QUARTO
--		7 - TABELA DE RELACIONAMENTO TIPOS DE QUARTO X ATRIBUTOS
--		8 - TABELA DE QUARTOS
--		9 - TABELA DE RELACIONAMENTO QUARTOS X ATRIBUTOS
--		10 - TABELA AUXILIAR USADA PELO SHOWCASE: NAO FAZ PARTE DO TCC

CREATE TABLE USUARIOS(
	COD_USUARIO		SERIAL			NOT NULL,
	NOME			VARCHAR(200)		NOT NULL,
	ATIVO			INTEGER			NOT NULL	DEFAULT 1, 
	DT_NASC			DATE			NULL,
	SEXO			INTEGER			NULL,
	NACIONALIDADE		VARCHAR(50)		NULL,
	PERFIL			VARCHAR(10)			NOT NULL, 
	TELEFONE		VARCHAR(15)		NOT NULL,
	EMAIL			VARCHAR(100)		NOT NULL,
	CPF			VARCHAR(11)		NULL,
	RG			VARCHAR(20)		NULL,
	PASSAPORTE		VARCHAR(30)		NULL,
	END_RUA			VARCHAR(100)		NULL,
	END_NRO			INTEGER			NULL,
	END_BAIRRO		VARCHAR(100)		NULL,
	END_CIDADE		VARCHAR(100)		NULL,
	END_UF			CHAR(2)			NULL,
	END_COMPL		VARCHAR(100)		NULL,
	LOGIN		    VARCHAR(100)		NULL,
	SENHA			VARCHAR(100)		NOT NULL,
	STATUS			INTEGER			NOT NULL	DEFAULT 1, -- 1 (ATIVO); 0 (INATIVO)
	CONSTRAINT PK_USUARIOS PRIMARY KEY (COD_USUARIO)
);
-- DROP TABLE USUARIOS
-- SELECT * FROM USUARIOS
INSERT INTO usuarios VALUES (1, 'Clovis Daniel Gueno', 1, '2017-11-16', 1, 'Brasileira', 1, '(41)3333-3333', 'email@email.com.br', '00000000191', '43141413', '3142352352', 'Estrada da Ribeira', 123, 'Imbuial', 'Colombo', '3 ', 'Casa 123', 'clovis2017', 'ny6Hf6c89jlhZpyfC5o7hQ==', 1);			
INSERT INTO usuarios VALUES (2, 'Eduardo Wosgrau', 1, '1990-11-10', 1, 'Brasileira', 1, '(41)3333-3333', 'email@email.com.br', '00000000191', '43141413', '3142352352', 'Estrada da Ribeira', 123, 'Imbuial', 'Colombo', 'PR', 'Casa 123', 'eduardo2017', 'eduardo2017', 1);
INSERT INTO usuarios VALUES (3, 'Fulano Funcionario 1', 1, '1990-11-10', 1, 'Brasileira', 1, '(41)3333-3333', 'email@email.com.br', '00000000191', '43141413', '3142352352', 'Estrada da Ribeira', 123, 'Imbuial', 'Colombo', 'PR', 'Casa 123', 'fulano12017', 'fulano12017', 1);
INSERT INTO usuarios VALUES (4, 'Fulano Funcionario 2', 1, '1990-11-10', 1, 'Brasileira', 1, '(41)3333-3333', 'email@email.com.br', '00000000191', '43141413', '3142352352', 'Estrada da Ribeira', 123, 'Imbuial', 'Colombo', 'PR', 'Casa 123', 'fulano22017', 'fulano22017', 1);

SELECT setval('usuarios_cod_usuario_seq', COALESCE((SELECT MAX(cod_usuario)+1 FROM usuarios), 1), false);
	
----------------------------------------------------------------------
-- 2 - TABELA DE CLIENTES
----------------------------------------------------------------------
CREATE TABLE CLIENTES(
	COD_CLIENTE		SERIAL          NOT NULL,
	NOME			VARCHAR(200)	NOT NULL,
	DT_NASC			DATE		NULL,
	SEXO			INTEGER		NULL,
	NACIONALIDADE		VARCHAR(50)	NULL,
	TELEFONE1		VARCHAR(15)	NULL,
	TELEFONE2		VARCHAR(15)	NULL,
	EMAIL1			VARCHAR(100)	NOT NULL,	
	EMAIL2			VARCHAR(100)	NULL,
	CPF			VARCHAR(11)	NOT NULL,
	RG			VARCHAR(20)	NULL,
	PASSAPORTE		VARCHAR(30)	NULL,
	END_RUA			VARCHAR(100)	NULL,
	END_NRO			INTEGER		NULL,
	END_BAIRRO		VARCHAR(100)	NULL,
	END_CIDADE		VARCHAR(100)	NULL,
	END_UF			CHAR(2)		NULL,
	END_COMPL		VARCHAR(100)	NULL,
	SENHA_ACESSO		VARCHAR(100)	NULL,
	STATUS			INTEGER		NULL	DEFAULT 1, -- 1 (ATIVO); 0 (INATIVO)
	COD_FOTO        INTEGER,
	CONSTRAINT PK_CLIENTES	PRIMARY KEY (COD_CLIENTE)
);
-- DROP TABLE CLIENTES
-- DROP SEQUENCE clientes_cod_cliente_seq
-- SELECT * FROM CLIENTES
INSERT INTO CLIENTES VALUES(1, 'CICLANO 1 DE SOUZA LEMOS', '31-12-1990', '1', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '00000000000', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(2, 'CICLANO 2 DE SOUZA LEMOS', '31-12-1990', '1', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '00000000000', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(3, 'CICLANO 3 DE SOUZA LEMOS', '31-12-1990', '1', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '00000000000', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(4, 'CICLANO 4 DE SOUZA LEMOS', '31-12-1990', '1', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '00000000000', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(5, 'CICLANO 5 DE SOUZA LEMOS', '31-12-1990', '1', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '00000000000', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(6, 'CICLANO 6 DE SOUZA LEMOS', '31-12-1990', '0', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '00000000000', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(7, 'CICLANO 7 DE SOUZA LEMOS', '31-12-1990', '0', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '00000000000', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(8, 'CICLANO 8 DE SOUZA LEMOS', '31-12-1990', '0', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '00000000000', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(9, 'CICLANO 9 DE SOUZA LEMOS', '31-12-1990', '2', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '00000000000', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(10, 'CICLANO 10 DE SOUZA LEMOS', '31-12-1990', '2', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '00000000000', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(11, 'CICLANO 11 DE SOUZA LEMOS', '31-12-1990', '2', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '00000000000', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);


-- Atualizar a sequencia usada para gerar o cod_cliente com: ultimo codigo + 1
SELECT setval('clientes_cod_cliente_seq', COALESCE((SELECT MAX(cod_cliente)+1 FROM clientes), 1), false);

----------------------------------------------------------------------
-- 3 - TABELA DE ATRIBUTOS
----------------------------------------------------------------------
CREATE TABLE ATRIBUTOS(
	COD_ATRIBUTO		SERIAL		NOT NULL,
	TIPO			INTEGER		NOT NULL, -- 1 (HOTEL), 2 (QUARTO)
	NOME			VARCHAR(200) 	NOT NULL,
	DESCRICAO		TEXT		NULL,
	STATUS			INTEGER		NOT NULL	DEFAULT 1, -- 1 (ATIVO); 0 (INATIVO)
	CONSTRAINT PK_ATRIBUTOS PRIMARY KEY (COD_ATRIBUTO)
);
-- DROP TABLE ATRIBUTOS
-- SELECT * FROM ATRIBUTOS
INSERT INTO ATRIBUTOS VALUES(1, 1, 'Piscina e restaurante com vista para a praia.', 'Piscina de x litros...', 1);

SELECT setval('atributos_cod_atributo_seq', COALESCE((SELECT MAX(cod_atributo)+1 FROM atributos), 1), false);

----------------------------------------------------------------------
-- 4 - TABELA DE FILIAIS
----------------------------------------------------------------------
CREATE TABLE FILIAIS(
	COD_FILIAL		SERIAL			NOT NULL,
	NOME			VARCHAR(200)		NOT NULL,
	DESCRICAO		TEXT			NULL,
	EXIBIR_SITE		INTEGER			NOT NULL	DEFAULT 1, -- 1 (EXIBIR); 0 (NAO EXIBIR)
	EMAIL			VARCHAR(100)		NULL,
	STATUS			INTEGER			NOT NULL	DEFAULT 1, -- 1 (ATIVO); 0 (INATIVO)
	COD_FOTO        INTEGER,	
	CONSTRAINT PK_FILIAIS PRIMARY KEY (COD_FILIAL)
);
-- DROP TABLE FILIAIS
-- SELECT * FROM FILIAIS
INSERT INTO FILIAIS VALUES(1, 'Curitiba (Centro)', 'Belissimo hotel de 6 andares com elevador no centro da capital do Parana.', 1, 1);
INSERT INTO FILIAIS VALUES(2, 'Florianopolis (Centro)', 'Belissimo hotel de 6 andares com elevador no centro da capital de Santa Catarina.', 1, 1);
INSERT INTO FILIAIS VALUES(3, 'Rio de Janeiro (Centro)', 'Belissimo hotel de 6 andares com elevador no centro da capital do Rio de Janeiro.', 1, 1);

SELECT setval('filiais_cod_filial_seq', COALESCE((SELECT MAX(cod_filial)+1 FROM filiais), 1), false);

----------------------------------------------------------------------
-- 5 - TABELA DE RELACIONAMENTO FILIAIS X ATRIBUTOS
----------------------------------------------------------------------
CREATE TABLE FILIAL_ATRIBUTO(
	COD_FILIAL		INTEGER		NOT NULL,
	COD_ATRIBUTO		INTEGER		NOT NULL,
	CONSTRAINT PK_FIL_ATRIB PRIMARY KEY (COD_FILIAL, COD_ATRIBUTO),
	FOREIGN KEY (COD_FILIAL) REFERENCES FILIAIS(COD_FILIAL),
	FOREIGN KEY (COD_ATRIBUTO) REFERENCES ATRIBUTOS(COD_ATRIBUTO)
);
-- DROP TABLE FILIAL_ATRIBUTO
-- SELECT * FROM FILIAL_ATRIBUTO

INSERT INTO FILIAL_ATRIBUTO VALUES(1, 1);

----------------------------------------------------------------------
-- 6 - TABELA DE TIPOS DE QUARTO
----------------------------------------------------------------------
CREATE TABLE TIPOS_DE_QUARTO(
	COD_TIPO_QUARTO			SERIAL			NOT NULL,
	NOME				VARCHAR(200)		NOT NULL,
	DESCRICAO			TEXT			NULL,
	EXIBIR_SITE			INTEGER			NOT NULL	DEFAULT 1, -- 1 (EXIBIR); 0 (NAO EXIBIR)
	TIPO				INTEGER			NOT NULL 	DEFAULT 1, -- 1 (SOLTEIRO), 2 (CASAL)
	VALOR				NUMERIC(9,2)		NOT NULL	DEFAULT 0,
	STATUS				INTEGER			NOT NULL	DEFAULT 1, -- 1 (ATIVO); 0 (INATIVO)
	CONSTRAINT PK_TIPO_FIL PRIMARY KEY (COD_TIPO_QUARTO)
);
-- DROP TABLE TIPOS_DE_QUARTO
-- SELECT * FROM TIPOS_DE_QUARTO
INSERT INTO TIPOS_DE_QUARTO VALUES(1, 'Quarto de Solteiro Executivo', 'Quarto com uma cama de solteiro.', 1, 1, 350.00, 1);
----------------------------------------------------------------------
-- 7 - TABELA DE RELACIONAMENTO TIPOS DE QUARTO X ATRIBUTOS
----------------------------------------------------------------------
CREATE TABLE TIPO_QUARTO_ATRIBUTO(
	COD_TIPO_QUARTO		INTEGER		NOT NULL,
	COD_ATRIBUTO		INTEGER		NOT NULL,
	CONSTRAINT PK_TIPO_FIL_ATRIB PRIMARY KEY (COD_TIPO_QUARTO, COD_ATRIBUTO),
	FOREIGN KEY (COD_TIPO_QUARTO) REFERENCES TIPOS_DE_QUARTO(COD_TIPO_QUARTO),
	FOREIGN KEY (COD_ATRIBUTO) REFERENCES ATRIBUTOS(COD_ATRIBUTO)
);
-- DROP TABLE TIPO_QUARTO_ATRIBUTO
-- SELECT * FROM TIPO_QUARTO_ATRIBUTO
INSERT INTO TIPO_QUARTO_ATRIBUTO VALUES(1, 1);
----------------------------------------------------------------------
-- 8 - TABELA DE QUARTOS
----------------------------------------------------------------------
CREATE TABLE QUARTOS(
	COD_QUARTO			SERIAL			NOT NULL,
	COD_FILIAL      	INTEGER,	
	ID_QUARTO			VARCHAR			NOT NULL,
	COD_TIPO_QUARTO			INTEGER			NOT NULL,
	NR_CAMAS			INTEGER			NOT NULL	DEFAULT 1,
	DESCRICAO			VARCHAR(400)	NOT NULL,
	COD_FOTO        INTEGER,	
	STATUS				INTEGER			NOT NULL	DEFAULT 1, -- 1 (ATIVO); 0 (INATIVO)
	CONSTRAINT PK_QUARTO PRIMARY KEY (COD_QUARTO),
	FOREIGN KEY (COD_TIPO_QUARTO) REFERENCES TIPOS_DE_QUARTO(COD_TIPO_QUARTO)	
);
-- DROP TABLE QUARTOS
-- SELECT * FROM QUARTOS
INSERT INTO QUARTOS VALUES(1, 1, 101, 1, 2, 'Quarto com vista para o mar.',null, 1);
INSERT INTO QUARTOS VALUES(2, 1, 102, 1, 2, 'Quarto com vista para o lago.',null, 1);

SELECT setval('Quartos_cod_quarto_seq', COALESCE((SELECT MAX(cod_quarto)+1 FROM quartos), 1), false);
----------------------------------------------------------------------
-- 9 - TABELA DE RELACIONAMENTO QUARTOS X ATRIBUTOS
----------------------------------------------------------------------
CREATE TABLE QUARTO_ATRIBUTO(
	COD_QUARTO			INTEGER			NOT NULL,
	COD_ATRIBUTO			INTEGER			NOT NULL,
	CONSTRAINT PK_QUARTO_ATRIB PRIMARY KEY (COD_QUARTO, COD_ATRIBUTO),
	FOREIGN KEY (COD_QUARTO) REFERENCES QUARTOS(COD_QUARTO),
	FOREIGN KEY (COD_ATRIBUTO) REFERENCES ATRIBUTOS(COD_ATRIBUTO)
);
-- DROP TABLE QUARTO_ATRIBUTO
-- SELECT * FROM QUARTO_ATRIBUTO
INSERT INTO QUARTO_ATRIBUTO VALUES(1, 1);

----------------------------------------------------------------------﻿----------------------------------------------------------------------
CREATE TABLE RESERVA(
	COD_RESERVA		SERIAL		NOT NULL,
	DT_ENTRADA		DATE		NOT NULL,
	DT_SAIDA		DATE		NOT NULL,
	DT_RESERVA 		DATE		NOT NULL,
	PRECO			NUMERIC         NOT NULL,
	STATUS			INTEGER		NOT NULL	DEFAULT 1, -- 1 (ATIVO); 0 (INATIVO)
	COD_CLIENTE		INTEGER		NOT NULL,
	COD_QUARTO		INTEGER		NOT NULL,
	CONSTRAINT PK_RESERVA PRIMARY KEY (COD_RESERVA),
	FOREIGN KEY (COD_CLIENTE) REFERENCES CLIENTES(COD_CLIENTE),
	FOREIGN KEY (COD_QUARTO) REFERENCES QUARTOS(COD_QUARTO)
);

CREATE TABLE FOTOS(
	COD_FOTO SERIAL  NOT NULL,
	LEGENDA  VARCHAR NOT NULL,
	NOME_FOTO_ORIGINAL     VARCHAR NOT NULL,
	NOME_FOTO_MINIATURA     VARCHAR NOT NULL
);

SELECT setval('fotos_cod_foto_seq', COALESCE((SELECT MAX(cod_foto)+1 FROM fotos), 1), false);

END;