--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.17
-- Dumped by pg_dump version 9.3.17
-- Started on 2017-12-07 21:27:27

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 1 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2068 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 176 (class 1259 OID 51072)
-- Name: atributos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE atributos (
    cod_atributo integer NOT NULL,
    tipo integer NOT NULL,
    nome character varying(200) NOT NULL,
    descricao text,
    status integer DEFAULT 1 NOT NULL
);


ALTER TABLE public.atributos OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 51070)
-- Name: atributos_cod_atributo_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE atributos_cod_atributo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.atributos_cod_atributo_seq OWNER TO postgres;

--
-- TOC entry 2069 (class 0 OID 0)
-- Dependencies: 175
-- Name: atributos_cod_atributo_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE atributos_cod_atributo_seq OWNED BY atributos.cod_atributo;


--
-- TOC entry 174 (class 1259 OID 51060)
-- Name: clientes; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE clientes (
    cod_cliente integer NOT NULL,
    nome character varying(200) NOT NULL,
    dt_nasc date,
    sexo integer,
    nacionalidade character varying(50),
    telefone1 character varying(15),
    telefone2 character varying(15),
    email1 character varying(100) NOT NULL,
    email2 character varying(100),
    cpf character varying(11) NOT NULL,
    rg character varying(20),
    passaporte character varying(30),
    end_rua character varying(100),
    end_nro integer,
    end_bairro character varying(100),
    end_cidade character varying(100),
    end_uf character(2),
    end_compl character varying(100),
    senha_acesso character varying(100),
    status integer DEFAULT 1,
    cod_foto integer
);


ALTER TABLE public.clientes OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 51058)
-- Name: clientes_cod_cliente_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE clientes_cod_cliente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clientes_cod_cliente_seq OWNER TO postgres;

--
-- TOC entry 2070 (class 0 OID 0)
-- Dependencies: 173
-- Name: clientes_cod_cliente_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE clientes_cod_cliente_seq OWNED BY clientes.cod_cliente;


--
-- TOC entry 178 (class 1259 OID 51084)
-- Name: filiais; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE filiais (
    cod_filial integer NOT NULL,
    nome character varying(200) NOT NULL,
    descricao text,
    exibir_site integer DEFAULT 1 NOT NULL,
    email character varying(100),
    status integer DEFAULT 1 NOT NULL,
    cod_foto integer
);


ALTER TABLE public.filiais OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 51082)
-- Name: filiais_cod_filial_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE filiais_cod_filial_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.filiais_cod_filial_seq OWNER TO postgres;

--
-- TOC entry 2071 (class 0 OID 0)
-- Dependencies: 177
-- Name: filiais_cod_filial_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE filiais_cod_filial_seq OWNED BY filiais.cod_filial;


--
-- TOC entry 179 (class 1259 OID 51095)
-- Name: filial_atributo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE filial_atributo (
    cod_filial integer NOT NULL,
    cod_atributo integer NOT NULL
);


ALTER TABLE public.filial_atributo OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 51197)
-- Name: fotos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE fotos (
    cod_foto integer NOT NULL,
    legenda character varying NOT NULL,
    nome_foto_original character varying NOT NULL,
    nome_foto_miniatura character varying NOT NULL
);


ALTER TABLE public.fotos OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 51195)
-- Name: fotos_cod_foto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE fotos_cod_foto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.fotos_cod_foto_seq OWNER TO postgres;

--
-- TOC entry 2072 (class 0 OID 0)
-- Dependencies: 188
-- Name: fotos_cod_foto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE fotos_cod_foto_seq OWNED BY fotos.cod_foto;


--
-- TOC entry 185 (class 1259 OID 51158)
-- Name: quarto_atributo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE quarto_atributo (
    cod_quarto integer NOT NULL,
    cod_atributo integer NOT NULL
);


ALTER TABLE public.quarto_atributo OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 51142)
-- Name: quartos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE quartos (
    cod_quarto integer NOT NULL,
    cod_filial integer,
    id_quarto character varying NOT NULL,
    cod_tipo_quarto integer NOT NULL,
    nr_camas integer DEFAULT 1 NOT NULL,
    descricao character varying(400) NOT NULL,
    cod_foto integer,
    status integer DEFAULT 1 NOT NULL
);


ALTER TABLE public.quartos OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 51140)
-- Name: quartos_cod_quarto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE quartos_cod_quarto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.quartos_cod_quarto_seq OWNER TO postgres;

--
-- TOC entry 2073 (class 0 OID 0)
-- Dependencies: 183
-- Name: quartos_cod_quarto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE quartos_cod_quarto_seq OWNED BY quartos.cod_quarto;


--
-- TOC entry 187 (class 1259 OID 51175)
-- Name: reserva; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE reserva (
    cod_reserva integer NOT NULL,
    dt_entrada date NOT NULL,
    dt_saida date NOT NULL,
    dt_reserva date NOT NULL,
    preco numeric NOT NULL,
    status integer DEFAULT 1 NOT NULL,
    cod_cliente integer NOT NULL,
    cod_quarto integer NOT NULL
);


ALTER TABLE public.reserva OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 51173)
-- Name: reserva_cod_reserva_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE reserva_cod_reserva_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reserva_cod_reserva_seq OWNER TO postgres;

--
-- TOC entry 2074 (class 0 OID 0)
-- Dependencies: 186
-- Name: reserva_cod_reserva_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE reserva_cod_reserva_seq OWNED BY reserva.cod_reserva;


--
-- TOC entry 182 (class 1259 OID 51125)
-- Name: tipo_quarto_atributo; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipo_quarto_atributo (
    cod_tipo_quarto integer NOT NULL,
    cod_atributo integer NOT NULL
);


ALTER TABLE public.tipo_quarto_atributo OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 51112)
-- Name: tipos_de_quarto; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipos_de_quarto (
    cod_tipo_quarto integer NOT NULL,
    nome character varying(200) NOT NULL,
    descricao text,
    exibir_site integer DEFAULT 1 NOT NULL,
    tipo integer DEFAULT 1 NOT NULL,
    valor numeric(9,2) DEFAULT 0 NOT NULL,
    status integer DEFAULT 1 NOT NULL
);


ALTER TABLE public.tipos_de_quarto OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 51110)
-- Name: tipos_de_quarto_cod_tipo_quarto_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipos_de_quarto_cod_tipo_quarto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipos_de_quarto_cod_tipo_quarto_seq OWNER TO postgres;

--
-- TOC entry 2075 (class 0 OID 0)
-- Dependencies: 180
-- Name: tipos_de_quarto_cod_tipo_quarto_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipos_de_quarto_cod_tipo_quarto_seq OWNED BY tipos_de_quarto.cod_tipo_quarto;


--
-- TOC entry 172 (class 1259 OID 51047)
-- Name: usuarios; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuarios (
    cod_usuario integer NOT NULL,
    nome character varying(200) NOT NULL,
    ativo integer DEFAULT 1 NOT NULL,
    dt_nasc date,
    sexo integer,
    nacionalidade character varying(50),
    perfil character varying(10) NOT NULL,
    telefone character varying(15) NOT NULL,
    email character varying(100) NOT NULL,
    cpf character varying(11),
    rg character varying(20),
    passaporte character varying(30),
    end_rua character varying(100),
    end_nro integer,
    end_bairro character varying(100),
    end_cidade character varying(100),
    end_uf character(2),
    end_compl character varying(100),
    login character varying(100),
    senha character varying(100) NOT NULL,
    status integer DEFAULT 1 NOT NULL
);


ALTER TABLE public.usuarios OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 51045)
-- Name: usuarios_cod_usuario_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE usuarios_cod_usuario_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.usuarios_cod_usuario_seq OWNER TO postgres;

--
-- TOC entry 2076 (class 0 OID 0)
-- Dependencies: 171
-- Name: usuarios_cod_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE usuarios_cod_usuario_seq OWNED BY usuarios.cod_usuario;


--
-- TOC entry 1890 (class 2604 OID 51075)
-- Name: cod_atributo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY atributos ALTER COLUMN cod_atributo SET DEFAULT nextval('atributos_cod_atributo_seq'::regclass);


--
-- TOC entry 1888 (class 2604 OID 51063)
-- Name: cod_cliente; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY clientes ALTER COLUMN cod_cliente SET DEFAULT nextval('clientes_cod_cliente_seq'::regclass);


--
-- TOC entry 1892 (class 2604 OID 51087)
-- Name: cod_filial; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filiais ALTER COLUMN cod_filial SET DEFAULT nextval('filiais_cod_filial_seq'::regclass);


--
-- TOC entry 1905 (class 2604 OID 51200)
-- Name: cod_foto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY fotos ALTER COLUMN cod_foto SET DEFAULT nextval('fotos_cod_foto_seq'::regclass);


--
-- TOC entry 1900 (class 2604 OID 51145)
-- Name: cod_quarto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY quartos ALTER COLUMN cod_quarto SET DEFAULT nextval('quartos_cod_quarto_seq'::regclass);


--
-- TOC entry 1903 (class 2604 OID 51178)
-- Name: cod_reserva; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reserva ALTER COLUMN cod_reserva SET DEFAULT nextval('reserva_cod_reserva_seq'::regclass);


--
-- TOC entry 1895 (class 2604 OID 51115)
-- Name: cod_tipo_quarto; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipos_de_quarto ALTER COLUMN cod_tipo_quarto SET DEFAULT nextval('tipos_de_quarto_cod_tipo_quarto_seq'::regclass);


--
-- TOC entry 1885 (class 2604 OID 51050)
-- Name: cod_usuario; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuarios ALTER COLUMN cod_usuario SET DEFAULT nextval('usuarios_cod_usuario_seq'::regclass);


--
-- TOC entry 2047 (class 0 OID 51072)
-- Dependencies: 176
-- Data for Name: atributos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO atributos VALUES (2, 2, 'Ar Condicionado', 'Ar condicionado digital', 1);
INSERT INTO atributos VALUES (3, 1, 'Café da manhã', 'Café da manhã incluso', 1);
INSERT INTO atributos VALUES (1, 1, 'Piscina e restaurante com vista para a praia.', 'Piscina semi-olímpica', 1);
INSERT INTO atributos VALUES (4, 1, 'Lareira', 'Lareira a lenha', 1);


--
-- TOC entry 2077 (class 0 OID 0)
-- Dependencies: 175
-- Name: atributos_cod_atributo_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('atributos_cod_atributo_seq', 5, false);


--
-- TOC entry 2045 (class 0 OID 51060)
-- Dependencies: 174
-- Data for Name: clientes; Type: TABLE DATA; Schema: public; Owner: postgres
--
INSERT INTO CLIENTES VALUES(1, 'Douglas Limeira', '31-12-1990', '1', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '99414695044', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(2, 'Silvio dos Santos', '31-12-1990', '1', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '81764022068', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(3, 'Clóvis Daniel Guêno', '31-12-1990', '1', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '52091721042', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(4, 'Maria da Silva', '31-12-1990', '1', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '85946141015', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(5, 'João das Neves', '31-12-1990', '1', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '35341215036', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(6, 'Izair Pereira', '31-12-1990', '0', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '38931789025', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(7, 'Marilia Pontes', '31-12-1990', '0', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '85086448093', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(8, 'Marisa Medeiros Neta', '31-12-1990', '0', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '01740754050', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(9, 'Angelida Boudrink', '31-12-1990', '2', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '75488271058', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(10, 'Assis de Noronha', '31-12-1990', '2', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '81542509009', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);
INSERT INTO CLIENTES VALUES(11, 'Pablo Estênio', '31-12-1990', '2', 'Brasileira', '(41)3333-3333', '(41)3333-3333', 'email1@email1.com.br', 'email2@email2.com.br', '73047763070', '00000', '00000', 'Rua das Flores', 89, 'Jardim das Americas', 'Curitiba', 'PR', 'qqq', '123', 1);


--
-- TOC entry 2078 (class 0 OID 0)
-- Dependencies: 173
-- Name: clientes_cod_cliente_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('clientes_cod_cliente_seq', 27, true);


--
-- TOC entry 2049 (class 0 OID 51084)
-- Dependencies: 178
-- Data for Name: filiais; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO filiais VALUES (2, 'Lazarus Floripa', 'Simples hotel com piscina no centro da capital de Santa Catarina. Próximo à várias praias e ao shopping. Rua Bocaiuva do Sul, 1234', 1, 'hotel2@abc.com', 1, 3);
INSERT INTO filiais VALUES (4, 'Lazarus Salvador', 'Um dos melhores hotéis da capital baiana com ótima localização. Ótimo para experimentar nossa culinária. Rua do Alfajor Piqui, 324', 1, 'hotel4@abc.com', 1, 5);
INSERT INTO filiais VALUES (5, 'Lazarus BH', 'Excelente hotel de 10 andares com elevador no centro da capital do Paraná. Perto de pontos turísticos.   Rua Dom Travessa Sul, 546', 1, 'hotel5@abc.com', 1, 6);
INSERT INTO filiais VALUES (1, 'Lazarus Curitiba', 'Ótima opção de hotel se você está precisando relaxar e descansar. Ótima escolha para ficar de tranquilo. Rua Almira Pinheiros, 234', 1, 'hotel1@abc.com', 1, 2);
INSERT INTO filiais VALUES (3, 'Lazarus Rio', 'Hotel com vista para o Corcovado na bela capital do Rio de Janeiro. Venha conhecer a cidade maravilhosa. Rua do Instituto Sul, 231', 1, 'hotel3@abc.com', 1, 4);


--
-- TOC entry 2079 (class 0 OID 0)
-- Dependencies: 177
-- Name: filiais_cod_filial_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('filiais_cod_filial_seq', 6, false);


--
-- TOC entry 2050 (class 0 OID 51095)
-- Dependencies: 179
-- Data for Name: filial_atributo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO filial_atributo VALUES (5, 1);
INSERT INTO filial_atributo VALUES (1, 2);
INSERT INTO filial_atributo VALUES (2, 4);
INSERT INTO filial_atributo VALUES (4, 3);


--
-- TOC entry 2060 (class 0 OID 51197)
-- Dependencies: 189
-- Data for Name: fotos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO fotos VALUES (1, '', 'quarto.jpg_2017-11-26_231934.jpg', 'quarto.jpg_2017-11-26_231934.min.jpg');
INSERT INTO fotos VALUES (2, '', 'filial.jpg_2017-11-26_232004.jpg', 'filial.jpg_2017-11-26_232004.min.jpg');
INSERT INTO fotos VALUES (3, '', 'filial.jpg_2017-11-27_002808.jpg', 'filial.jpg_2017-11-27_002808.min.jpg');
INSERT INTO fotos VALUES (4, '', 'filial.jpg_2017-11-27_003029.jpg', 'filial.jpg_2017-11-27_003029.min.jpg');
INSERT INTO fotos VALUES (5, '', 'filial.jpg_2017-11-27_003552.jpg', 'filial.jpg_2017-11-27_003552.min.jpg');
INSERT INTO fotos VALUES (6, '', 'filial.jpg_2017-11-27_004152.jpg', 'filial.jpg_2017-11-27_004152.min.jpg');
INSERT INTO fotos VALUES (7, '', 'filial.jpg_2017-11-27_004452.jpg', 'filial.jpg_2017-11-27_004452.min.jpg');
INSERT INTO fotos VALUES (8, '', 'quarto.jpg_2017-11-27_011112.jpg', 'quarto.jpg_2017-11-27_011112.min.jpg');
INSERT INTO fotos VALUES (9, '', 'quarto.jpg_2017-11-27_011429.jpg', 'quarto.jpg_2017-11-27_011429.min.jpg');
INSERT INTO fotos VALUES (10, '', 'quarto.jpg_2017-12-02_115432.jpg', 'quarto.jpg_2017-12-02_115432.min.jpg');
INSERT INTO fotos VALUES (11, '', 'quarto.jpg_2017-12-02_115508.jpg', 'quarto.jpg_2017-12-02_115508.min.jpg');
INSERT INTO fotos VALUES (12, '', 'quarto.jpg_2017-12-02_115544.jpg', 'quarto.jpg_2017-12-02_115544.min.jpg');
INSERT INTO fotos VALUES (13, '', 'quarto.jpg_2017-12-06_213049.jpg', 'quarto.jpg_2017-12-06_213049.min.jpg');
INSERT INTO fotos VALUES (14, '', 'filial.jpg_2017-12-06_213249.jpg', 'filial.jpg_2017-12-06_213249.min.jpg');


--
-- TOC entry 2080 (class 0 OID 0)
-- Dependencies: 188
-- Name: fotos_cod_foto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('fotos_cod_foto_seq', 14, true);


--
-- TOC entry 2056 (class 0 OID 51158)
-- Dependencies: 185
-- Data for Name: quarto_atributo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO quarto_atributo VALUES (4, 2);
INSERT INTO quarto_atributo VALUES (4, 3);
INSERT INTO quarto_atributo VALUES (3, 2);
INSERT INTO quarto_atributo VALUES (3, 3);
INSERT INTO quarto_atributo VALUES (2, 2);
INSERT INTO quarto_atributo VALUES (1, 2);


--
-- TOC entry 2055 (class 0 OID 51142)
-- Dependencies: 184
-- Data for Name: quartos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO quartos VALUES (4, 1, '103', 1, 1, 'Quarto elegante com sacada', 8, 1);
INSERT INTO quartos VALUES (3, 1, '222', 1, 1, 'Quarto básico com sacada', 11, 1);
INSERT INTO quartos VALUES (2, 1, '102', 1, 2, 'Quarto com vista para o lago.', 12, 1);
INSERT INTO quartos VALUES (1, 1, '101', 1, 2, 'Quarto com ótima localização.', 9, 1);


--
-- TOC entry 2081 (class 0 OID 0)
-- Dependencies: 183
-- Name: quartos_cod_quarto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('quartos_cod_quarto_seq', 6, false);


--
-- TOC entry 2082 (class 0 OID 0)
-- Dependencies: 186
-- Name: reserva_cod_reserva_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('reserva_cod_reserva_seq', 13, false);


--
-- TOC entry 2053 (class 0 OID 51125)
-- Dependencies: 182
-- Data for Name: tipo_quarto_atributo; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tipo_quarto_atributo VALUES (1, 1);


--
-- TOC entry 2052 (class 0 OID 51112)
-- Dependencies: 181
-- Data for Name: tipos_de_quarto; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tipos_de_quarto VALUES (1, 'Quarto de Solteiro Executivo', 'Quarto com uma cama de solteiro.', 1, 1, 350.00, 1);
INSERT INTO tipos_de_quarto VALUES (4, 'Econômico', 'Quarto ideal para pernoites ou curtas estadias', 1, 1, 100.00, 1);
INSERT INTO tipos_de_quarto VALUES (5, 'Tipo Supreme', 'Quarto de Luxo', 1, 1, 120.00, 1);


--
-- TOC entry 2083 (class 0 OID 0)
-- Dependencies: 180
-- Name: tipos_de_quarto_cod_tipo_quarto_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipos_de_quarto_cod_tipo_quarto_seq', 5, true);


--
-- TOC entry 2043 (class 0 OID 51047)
-- Dependencies: 172
-- Data for Name: usuarios; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO usuarios VALUES (1, 'Clovis Daniel Gueno', 1, '2017-11-16', 1, 'Brasileira', '1', '(41)3333-3333', 'email@email.com.br', '00000000191', '43141413', '3142352352', 'Estrada da Ribeira', 123, 'Imbuial', 'Colombo', '3 ', 'Casa 123', 'clovis2018', 'ny6Hf6c89jlhZpyfC5o7hQ==', 1);
INSERT INTO usuarios VALUES (3, 'Fulano Funcionario 1', 1, '1990-11-10', 1, 'Brasileira', '1', '(41)3333-3333', 'email@email.com.br', '00000000191', '43141413', '3142352352', 'Estrada da Ribeira', 123, 'Imbuial', 'Colombo', 'PR', 'Casa 123', 'fulano12018', 'fulano12017', 1);
INSERT INTO usuarios VALUES (4, 'Fulano Funcionario 2', 1, '1990-11-10', 1, 'Brasileira', '1', '(41)3333-3333', 'email@email.com.br', '00000000191', '43141413', '3142352352', 'Estrada da Ribeira', 123, 'Imbuial', 'Colombo', 'PR', 'Casa 123', 'fulano22018', 'fulano22017', 1);
INSERT INTO usuarios VALUES (2, 'Stephany Rios', 1, '2017-11-08', 1, 'Brasileira', '1', '(41)3333-3333', 'email@email.com.br', '00000000191', '43141413', '3142352352', 'Estrada da Ribeira', 123, 'Imbuial', 'Bocaiuva do Sul', '16', 'Casa 123', 'sr2018', 'ny6Hf6c89jlhZpyfC5o7hQ==', 1);


--
-- TOC entry 2084 (class 0 OID 0)
-- Dependencies: 171
-- Name: usuarios_cod_usuario_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('usuarios_cod_usuario_seq', 5, false);


--
-- TOC entry 1911 (class 2606 OID 51081)
-- Name: pk_atributos; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY atributos
    ADD CONSTRAINT pk_atributos PRIMARY KEY (cod_atributo);


--
-- TOC entry 1909 (class 2606 OID 51069)
-- Name: pk_clientes; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY clientes
    ADD CONSTRAINT pk_clientes PRIMARY KEY (cod_cliente);


--
-- TOC entry 1915 (class 2606 OID 51099)
-- Name: pk_fil_atrib; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY filial_atributo
    ADD CONSTRAINT pk_fil_atrib PRIMARY KEY (cod_filial, cod_atributo);


--
-- TOC entry 1913 (class 2606 OID 51094)
-- Name: pk_filiais; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY filiais
    ADD CONSTRAINT pk_filiais PRIMARY KEY (cod_filial);


--
-- TOC entry 1921 (class 2606 OID 51152)
-- Name: pk_quarto; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY quartos
    ADD CONSTRAINT pk_quarto PRIMARY KEY (cod_quarto);


--
-- TOC entry 1923 (class 2606 OID 51162)
-- Name: pk_quarto_atrib; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY quarto_atributo
    ADD CONSTRAINT pk_quarto_atrib PRIMARY KEY (cod_quarto, cod_atributo);


--
-- TOC entry 1925 (class 2606 OID 51184)
-- Name: pk_reserva; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY reserva
    ADD CONSTRAINT pk_reserva PRIMARY KEY (cod_reserva);


--
-- TOC entry 1917 (class 2606 OID 51124)
-- Name: pk_tipo_fil; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipos_de_quarto
    ADD CONSTRAINT pk_tipo_fil PRIMARY KEY (cod_tipo_quarto);


--
-- TOC entry 1919 (class 2606 OID 51129)
-- Name: pk_tipo_fil_atrib; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipo_quarto_atributo
    ADD CONSTRAINT pk_tipo_fil_atrib PRIMARY KEY (cod_tipo_quarto, cod_atributo);


--
-- TOC entry 1907 (class 2606 OID 51057)
-- Name: pk_usuarios; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuarios
    ADD CONSTRAINT pk_usuarios PRIMARY KEY (cod_usuario);


--
-- TOC entry 1927 (class 2606 OID 51105)
-- Name: filial_atributo_cod_atributo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filial_atributo
    ADD CONSTRAINT filial_atributo_cod_atributo_fkey FOREIGN KEY (cod_atributo) REFERENCES atributos(cod_atributo);


--
-- TOC entry 1926 (class 2606 OID 51100)
-- Name: filial_atributo_cod_filial_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY filial_atributo
    ADD CONSTRAINT filial_atributo_cod_filial_fkey FOREIGN KEY (cod_filial) REFERENCES filiais(cod_filial);


--
-- TOC entry 1932 (class 2606 OID 51168)
-- Name: quarto_atributo_cod_atributo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY quarto_atributo
    ADD CONSTRAINT quarto_atributo_cod_atributo_fkey FOREIGN KEY (cod_atributo) REFERENCES atributos(cod_atributo);


--
-- TOC entry 1931 (class 2606 OID 51163)
-- Name: quarto_atributo_cod_quarto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY quarto_atributo
    ADD CONSTRAINT quarto_atributo_cod_quarto_fkey FOREIGN KEY (cod_quarto) REFERENCES quartos(cod_quarto);


--
-- TOC entry 1930 (class 2606 OID 51153)
-- Name: quartos_cod_tipo_quarto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY quartos
    ADD CONSTRAINT quartos_cod_tipo_quarto_fkey FOREIGN KEY (cod_tipo_quarto) REFERENCES tipos_de_quarto(cod_tipo_quarto);


--
-- TOC entry 1933 (class 2606 OID 51185)
-- Name: reserva_cod_cliente_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reserva
    ADD CONSTRAINT reserva_cod_cliente_fkey FOREIGN KEY (cod_cliente) REFERENCES clientes(cod_cliente);


--
-- TOC entry 1934 (class 2606 OID 51190)
-- Name: reserva_cod_quarto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY reserva
    ADD CONSTRAINT reserva_cod_quarto_fkey FOREIGN KEY (cod_quarto) REFERENCES quartos(cod_quarto);


--
-- TOC entry 1929 (class 2606 OID 51135)
-- Name: tipo_quarto_atributo_cod_atributo_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_quarto_atributo
    ADD CONSTRAINT tipo_quarto_atributo_cod_atributo_fkey FOREIGN KEY (cod_atributo) REFERENCES atributos(cod_atributo);


--
-- TOC entry 1928 (class 2606 OID 51130)
-- Name: tipo_quarto_atributo_cod_tipo_quarto_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipo_quarto_atributo
    ADD CONSTRAINT tipo_quarto_atributo_cod_tipo_quarto_fkey FOREIGN KEY (cod_tipo_quarto) REFERENCES tipos_de_quarto(cod_tipo_quarto);


--
-- TOC entry 2067 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

