--
-- PostgreSQL database dump
--

-- Dumped from database version 14.15 (Ubuntu 14.15-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.15 (Ubuntu 14.15-0ubuntu0.22.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: estoque; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.estoque (
    codigo_produto character varying(255) NOT NULL,
    ativo boolean,
    nome_produto character varying(255),
    preco_em_centavos integer,
    quantidade integer DEFAULT 0
);


ALTER TABLE public.estoque OWNER TO postgres;

--
-- Data for Name: estoque; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.estoque (codigo_produto, ativo, nome_produto, preco_em_centavos, quantidade) FROM stdin;
6bed66d6-ecf1-41e1-b44b-b595bf6c2f19	t	Case branca iphone 11	3500	20
95420ef9-f622-4cad-b773-4f9c27a8d33a	t	Case azul iphone 11	3500	20
\.


--
-- Name: estoque estoque_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.estoque
    ADD CONSTRAINT estoque_pkey PRIMARY KEY (codigo_produto);


--
-- PostgreSQL database dump complete
--

