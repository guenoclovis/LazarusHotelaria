----------------------------------------------------------------------
-- SQL DE CRIAï¿½ï¿½O E POPULAï¿½ï¿½O DE TABELAS - LAZARUS
----------------------------------------------------------------------
-- Seguir a ordem de criaï¿½ï¿½o:
-- 		1 - TABELA DE USUï¿½RIOS
-- 		2 - TABELA DE CLIENTES
-- 		3 - TABELA DE ATRIBUTOS
--		4 - TABELA DE FILIAIS
--		5 - TABELA DE RELACIONAMENTO FILIAIS X ATRIBUTOS
--		6 - TABELA DE TIPOS DE QUARTO
--		7 - TABELA DE RELACIONAMENTO TIPOS DE QUARTO X ATRIBUTOS
--		8 - TABELA DE QUARTOS
--		9 - TABELA DE RELACIONAMENTO QUARTOS X ATRIBUTOS
--		10 - TABELA AUXILIAR USADA PELO SHOWCASE: NAO FAZ PARTE DO TCC
----------------------------------------------------------------------
-- 1 - TABELA DE USUï¿½RIOS
----------------------------------------------------------------------
CREATE TABLE USUARIOS(
	COD_USUARIO		SERIAL			NOT NULL,
	NOME			VARCHAR(200)		NOT NULL,
	IND_ATIVO		CHAR(1)			NOT NULL	DEFAULT 'S', -- 'S' (SIM); 'N' (Nï¿½O)
	DT_NASC			DATE			NOT NULL,
	SEXO			CHAR(1)			NOT NULL,
	NACIONALIDADE		VARCHAR(50)		NOT NULL,
	PERFIL			CHAR(1)			NOT NULL, -- 'A' (ADMINISTRADOR); 'F' (FUNCIONï¿½RIO)
	TELEFONE		VARCHAR(15)		NULL,
	EMAIL			VARCHAR(100)		NOT NULL,
	SENHA_ACESSO		VARCHAR(100)		NOT NULL,
	STATUS			CHAR(1)			NOT NULL	DEFAULT 'I', -- 'I' (INCLUï¿½DO); 'E' (EXCLUï¿½DO)
	CONSTRAINT PK_USUARIOS PRIMARY KEY (COD_USUARIO)
);
-- DROP TABLE USUARIOS
-- SELECT * FROM USUARIOS
-- INSERT INTO USUARIOS VALUES(1, 'GERENTE FODï¿½O', 'S', '31-12-1990', 'M', 'Brasileira', 'A', '(41)3333-3333', 'email@email.com.br', '123', 'I');
----------------------------------------------------------------------
-- 2 - TABELA DE CLIENTES
----------------------------------------------------------------------
CREATE TABLE CLIENTES(
	COD_CLIENTE		SERIAL          NOT NULL,
	NOME			VARCHAR(200)	NOT NULL,
	DT_NASC			DATE		NOT NULL,
	SEXO			CHAR(1)		NOT NULL,
	NACIONALIDADE		VARCHAR(50)	NOT NULL,
	TELEFONE1		VARCHAR(15)	NULL,
	TELEFONE2		VARCHAR(15)	NULL,
	EMAIL1			VARCHAR(100)	NOT NULL,	
	EMAIL2			VARCHAR(100)	NULL,
	CPF			VARCHAR(11)	NULL,
	RG			VARCHAR(20)	NULL,
	PASSAPORTE		VARCHAR(30)	NULL,
	END_RUA			VARCHAR(100)	NULL,
	END_NRO			INTEGER		NULL,
	END_BAIRRO		VARCHAR(100)	NULL,
	END_CIDADE		VARCHAR(100)	NULL,
	END_UF			CHAR(2)		NULL,
	END_COMPL		VARCHAR(100)	NULL,
	SENHA_ACESSO		VARCHAR(100)	NOT NULL,
	STATUS			CHAR(1)		NOT NULL	DEFAULT 'I', -- 'I' (INCLUï¿½DO); 'E' (EXCLUï¿½DO)
	CONSTRAINT PK_CLIENTES	PRIMARY KEY (COD_CLIENTE)
);
-- DROP TABLE CLIENTES
-- SELECT * FROM CLIENTES
INSERT INTO CLIENTES VALUES(1, 'CICLANO DE SOUZA LEMOS', '31-12-1990', 'M', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '00000000000', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Amï¿½ricas', 'Curitiba', 'PR', 'qqq', '123', 'I');

-- Atualizar a sequencia usada para gerar o cod_cliente com: ultimo codigo + 1
SELECT setval('clientes_cod_cliente_seq', COALESCE((SELECT MAX(cod_cliente)+1 FROM clientes), 1), false);

----------------------------------------------------------------------
-- 3 - TABELA DE ATRIBUTOS
----------------------------------------------------------------------
CREATE TABLE ATRIBUTOS(
	COD_ATRIBUTO		SERIAL		NOT NULL,
	TIPO			CHAR(1)		NOT NULL, -- 'H' (HOTEL), 'Q' (QUARTO)
	NOME			VARCHAR(200) 	NOT NULL,
	DESCRICAO		TEXT		NULL,
	STATUS			CHAR(1)		NOT NULL	DEFAULT 'I', -- 'I' (INCLUï¿½DO); 'E' (EXCLUï¿½DO)
	CONSTRAINT PK_ATRIBUTOS PRIMARY KEY (COD_ATRIBUTO)
);
-- DROP TABLE ATRIBUTOS
-- SELECT * FROM ATRIBUTOS
-- INSERT INTO ATRIBUTOS VALUES(1, 'H', 'Piscina e restaurante com vista para a praia.', 'Piscina de x litros...', 'I');
----------------------------------------------------------------------
-- 4 - TABELA DE FILIAIS
----------------------------------------------------------------------
CREATE TABLE FILIAIS(
	COD_FILIAL		SERIAL			NOT NULL,
	NOME			VARCHAR(200)	NOT NULL,
	DESCRICAO		TEXT			NULL,
	EXIBIR_SITE		CHAR(1)			NOT NULL	DEFAULT 'S', -- 'S' (EXIBIR); 'N' (Nï¿½O EXIBIR)
	EMAIL			VARCHAR(100)	NULL,
	STATUS			CHAR(1)			NOT NULL	DEFAULT 'I', -- 'I' (INCLUï¿½DO); 'E' (EXCLUï¿½DO)	
	CONSTRAINT PK_FILIAIS PRIMARY KEY (COD_FILIAL)
);
-- DROP TABLE FILIAIS
-- SELECT * FROM FILIAIS
-- INSERT INTO FILIAIS VALUES(1, 'Curitiba (Centro)', 'Belï¿½ssimo hotel de 6 andares com elevador no centro da capital do Paranï¿½.', 'S', 'I');
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
-- INSERT INTO FILIAL_ATRIBUTO VALUES(1, 1);
----------------------------------------------------------------------
-- 6 - TABELA DE TIPOS DE QUARTO
----------------------------------------------------------------------
CREATE TABLE TIPOS_DE_QUARTO(
	COD_TIPO_QUARTO			SERIAL			NOT NULL,
	NOME				VARCHAR(200)		NOT NULL,
	DESCRICAO			TEXT			NULL,
	EXIBIR_SITE			CHAR(1)			NOT NULL	DEFAULT 'S', -- 'S' (EXIBIR); 'N' (Nï¿½O EXIBIR)
	TIPO				CHAR(1)			NOT NULL 	DEFAULT 'S', -- 'S' (SOLTEIRO), 'C' (CASAL)
	VALOR				NUMERIC(9,2)		NOT NULL	DEFAULT 0,
	STATUS				CHAR(1)			NOT NULL	DEFAULT 'I', -- 'I' (INCLUï¿½DO); 'E' (EXCLUï¿½DO)
	CONSTRAINT PK_TIPO_FIL PRIMARY KEY (COD_TIPO_QUARTO)
);
-- DROP TABLE TIPOS_DE_QUARTO
-- SELECT * FROM TIPOS_DE_QUARTO
-- INSERT INTO TIPOS_DE_QUARTO VALUES(1, 'Quarto de Solteiro Executivo', 'Quarto com uma cama de solteiro.', 'S', 'S', 350.00, 'I');
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
-- INSERT INTO TIPO_QUARTO_ATRIBUTO VALUES(1, 1);
----------------------------------------------------------------------
-- 8 - TABELA DE QUARTOS
----------------------------------------------------------------------
CREATE TABLE QUARTOS(
	COD_QUARTO			SERIAL			NOT NULL,
	ID_QUARTO			VARCHAR			NOT NULL,
	COD_TIPO_QUARTO			INTEGER			NOT NULL,
	NR_CAMAS			INTEGER			NOT NULL	DEFAULT 1,
	STATUS				CHAR(1)			NOT NULL	DEFAULT 'I', -- 'I' (INCLUï¿½DO); 'E' (EXCLUï¿½DO)
	CONSTRAINT PK_QUARTO PRIMARY KEY (COD_QUARTO),
	FOREIGN KEY (COD_TIPO_QUARTO) REFERENCES TIPOS_DE_QUARTO(COD_TIPO_QUARTO)	
);
-- DROP TABLE QUARTOS
-- SELECT * FROM QUARTOS
-- INSERT INTO QUARTOS VALUES(1, 1, 1, 1, 'I')
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
-- INSERT INTO QUARTO_ATRIBUTO VALUES(1, 1);
----------------------------------------------------------------------
----------------------------------------------------------------------
-- 9 - TABELA DE RELACIONAMENTO QUARTOS X ATRIBUTOS
----------------------------------------------------------------------
CREATE TABLE SHOWCASE(
	COD_SHOWCASE			INTEGER			NOT NULL,
	
	
	
);
-- DROP TABLE SHOWCASE
-- SELECT * FROM SHOWCASE
-- INSERT INTO SHOWCASE VALUES(1, 1);
----------------------------------------------------------------------﻿----------------------------------------------------------------------
-- SQL DE CRIAï¿½ï¿½O E POPULAï¿½ï¿½O DE TABELAS - LAZARUS
----------------------------------------------------------------------
-- Seguir a ordem de criaï¿½ï¿½o:
-- 		1 - TABELA DE USUï¿½RIOS
-- 		2 - TABELA DE CLIENTES
-- 		3 - TABELA DE ATRIBUTOS
--		4 - TABELA DE FILIAIS
--		5 - TABELA DE RELACIONAMENTO FILIAIS X ATRIBUTOS
--		6 - TABELA DE TIPOS DE QUARTO
--		7 - TABELA DE RELACIONAMENTO TIPOS DE QUARTO X ATRIBUTOS
--		8 - TABELA DE QUARTOS
--		9 - TABELA DE RELACIONAMENTO QUARTOS X ATRIBUTOS
--		10 - TABELA AUXILIAR USADA PELO SHOWCASE: NAO FAZ PARTE DO TCC
----------------------------------------------------------------------
-- 1 - TABELA DE USUï¿½RIOS
----------------------------------------------------------------------
CREATE TABLE USUARIOS(
	COD_USUARIO		SERIAL			NOT NULL,
	NOME			VARCHAR(200)		NOT NULL,
	IND_ATIVO		CHAR(1)			NOT NULL	DEFAULT 'S', -- 'S' (SIM); 'N' (Nï¿½O)
	DT_NASC			DATE			NOT NULL,
	SEXO			CHAR(1)			NOT NULL,
	NACIONALIDADE		VARCHAR(50)		NOT NULL,
	PERFIL			CHAR(1)			NOT NULL, -- 'A' (ADMINISTRADOR); 'F' (FUNCIONï¿½RIO)
	TELEFONE		VARCHAR(15)		NULL,
	EMAIL			VARCHAR(100)		NOT NULL,
	SENHA_ACESSO		VARCHAR(100)		NOT NULL,
	STATUS			CHAR(1)			NOT NULL	DEFAULT 'I', -- 'I' (INCLUï¿½DO); 'E' (EXCLUï¿½DO)
	CONSTRAINT PK_USUARIOS PRIMARY KEY (COD_USUARIO)
);
-- DROP TABLE USUARIOS
-- SELECT * FROM USUARIOS
-- INSERT INTO USUARIOS VALUES(1, 'GERENTE FODï¿½O', 'S', '31-12-1990', 'M', 'Brasileira', 'A', '(41)3333-3333', 'email@email.com.br', '123', 'I');
----------------------------------------------------------------------
-- 2 - TABELA DE CLIENTES
----------------------------------------------------------------------
CREATE TABLE CLIENTES(
	COD_CLIENTE		SERIAL          NOT NULL,
	NOME			VARCHAR(200)	NOT NULL,
	DT_NASC			DATE		NOT NULL,
	SEXO			INTEGER		NOT NULL, --Mudado de char para integer: 0 = outro; 1 = masculino; 2 = feminino
	NACIONALIDADE		VARCHAR(50)	NOT NULL,
	TELEFONE1		VARCHAR(15)	NULL,
	TELEFONE2		VARCHAR(15)	NULL,
	EMAIL1			VARCHAR(100)	NOT NULL,	
	EMAIL2			VARCHAR(100)	NULL,
	CPF			VARCHAR(11)	NULL,
	RG			VARCHAR(20)	NULL,
	PASSAPORTE		VARCHAR(30)	NULL,
	END_RUA			VARCHAR(100)	NULL,
	END_NRO			INTEGER		NULL,
	END_BAIRRO		VARCHAR(100)	NULL,
	END_CIDADE		VARCHAR(100)	NULL,
	END_UF			CHAR(2)		NULL,
	END_COMPL		VARCHAR(100)	NULL,
	SENHA_ACESSO		VARCHAR(100)	NOT NULL,
	STATUS			CHAR(1)		NOT NULL	DEFAULT 'I', -- 'I' (INCLUï¿½DO); 'E' (EXCLUï¿½DO)
	CONSTRAINT PK_CLIENTES	PRIMARY KEY (COD_CLIENTE)
);
-- DROP TABLE CLIENTES
-- SELECT * FROM CLIENTES
INSERT INTO CLIENTES VALUES(1, 'CICLANO DE SOUZA LEMOS', '31-12-1990', 1, 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '00000000000', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Amï¿½ricas', 'Curitiba', 'PR', 'qqq', '123', 'I');
INSERT INTO CLIENTES VALUES(2, 'LEMOS CICLANO DE SOUZA', '31-12-1990', 1, 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '00000000000', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Amï¿½ricas', 'Curitiba', 'PR', 'qqq', '123', 'I');
-- Atualizar a sequencia usada para gerar o cod_cliente com: ultimo codigo + 1
SELECT setval('clientes_cod_cliente_seq', COALESCE((SELECT MAX(cod_cliente)+1 FROM clientes), 1), false);

----------------------------------------------------------------------
-- 3 - TABELA DE ATRIBUTOS
----------------------------------------------------------------------
CREATE TABLE ATRIBUTOS(
	COD_ATRIBUTO		SERIAL		NOT NULL,
	TIPO			CHAR(1)		NOT NULL, -- 'H' (HOTEL), 'Q' (QUARTO)
	NOME			VARCHAR(200) 	NOT NULL,
	DESCRICAO		TEXT		NULL,
	STATUS			CHAR(1)		NOT NULL	DEFAULT 'I', -- 'I' (INCLUï¿½DO); 'E' (EXCLUï¿½DO)
	CONSTRAINT PK_ATRIBUTOS PRIMARY KEY (COD_ATRIBUTO)
);
-- DROP TABLE ATRIBUTOS
-- SELECT * FROM ATRIBUTOS
-- INSERT INTO ATRIBUTOS VALUES(1, 'H', 'Piscina e restaurante com vista para a praia.', 'Piscina de x litros...', 'I');
----------------------------------------------------------------------
-- 4 - TABELA DE FILIAIS
----------------------------------------------------------------------
CREATE TABLE FILIAIS(
	COD_FILIAL		SERIAL			NOT NULL,
	NOME			VARCHAR(200)		NOT NULL,
	EMAIL			VARCHAR(100)		NOT NULL,
	DESCRICAO		TEXT			NULL,
	EXIBIR_SITE		CHAR(1)			NOT NULL	DEFAULT 'S', -- 'S' (EXIBIR); 'N' (Nï¿½O EXIBIR)
	STATUS			CHAR(1)			NOT NULL	DEFAULT 'I', -- 'I' (INCLUï¿½DO); 'E' (EXCLUï¿½DO)
	CONSTRAINT PK_FILIAIS PRIMARY KEY (COD_FILIAL)
);
-- DROP TABLE FILIAIS
-- SELECT * FROM FILIAIS
-- INSERT INTO FILIAIS VALUES(1, 'Curitiba (Centro)', 'Bel�ssimo hotel de 6 andares com elevador no centro da capital do Paran�', 'S', 'I');
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
-- INSERT INTO FILIAL_ATRIBUTO VALUES(1, 1);
----------------------------------------------------------------------
-- 6 - TABELA DE TIPOS DE QUARTO
----------------------------------------------------------------------
CREATE TABLE TIPOS_DE_QUARTO(
	COD_TIPO_QUARTO			SERIAL			NOT NULL,
	NOME				VARCHAR(200)		NOT NULL,
	DESCRICAO			TEXT			NULL,
	EXIBIR_SITE			CHAR(1)			NOT NULL	DEFAULT 'S', -- 'S' (EXIBIR); 'N' (Nï¿½O EXIBIR)
	TIPO				CHAR(1)			NOT NULL 	DEFAULT 'S', -- 'S' (SOLTEIRO), 'C' (CASAL)
	VALOR				NUMERIC(9,2)		NOT NULL	DEFAULT 0,
	STATUS				CHAR(1)			NOT NULL	DEFAULT 'I', -- 'I' (INCLUï¿½DO); 'E' (EXCLUï¿½DO)
	CONSTRAINT PK_TIPO_FIL PRIMARY KEY (COD_TIPO_QUARTO)
);
-- DROP TABLE TIPOS_DE_QUARTO
-- SELECT * FROM TIPOS_DE_QUARTO
-- INSERT INTO TIPOS_DE_QUARTO VALUES(1, 'Quarto de Solteiro Executivo', 'Quarto com uma cama de solteiro.', 'S', 'S', 350.00, 'I');
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
-- INSERT INTO TIPO_QUARTO_ATRIBUTO VALUES(1, 1);
----------------------------------------------------------------------
-- 8 - TABELA DE QUARTOS
----------------------------------------------------------------------
CREATE TABLE QUARTOS(
	COD_QUARTO			SERIAL			NOT NULL,
	ID_QUARTO			VARCHAR			NOT NULL,
	COD_TIPO_QUARTO			INTEGER			NOT NULL,
	NR_CAMAS			INTEGER			NOT NULL	DEFAULT 1,
	STATUS				CHAR(1)			NOT NULL	DEFAULT 'I', -- 'I' (INCLUï¿½DO); 'E' (EXCLUï¿½DO)
	CONSTRAINT PK_QUARTO PRIMARY KEY (COD_QUARTO),
	FOREIGN KEY (COD_TIPO_QUARTO) REFERENCES TIPOS_DE_QUARTO(COD_TIPO_QUARTO)	
);
-- DROP TABLE QUARTOS
-- SELECT * FROM QUARTOS
-- INSERT INTO QUARTOS VALUES(1, 1, 1, 1, 'I')
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
-- INSERT INTO QUARTO_ATRIBUTO VALUES(1, 1);
----------------------------------------------------------------------
----------------------------------------------------------------------
-- 9 - TABELA DE RELACIONAMENTO QUARTOS X ATRIBUTOS
----------------------------------------------------------------------
CREATE TABLE SHOWCASE(
	COD_SHOWCASE			INTEGER			NOT NULL,
	
	
	
);
-- DROP TABLE SHOWCASE
-- SELECT * FROM SHOWCASE
-- INSERT INTO SHOWCASE VALUES(1, 1);
----------------------------------------------------------------------