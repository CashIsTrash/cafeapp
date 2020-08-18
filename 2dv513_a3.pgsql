--
-- PostgreSQL database dump
--

-- Dumped from database version 11.7 (Ubuntu 11.7-0ubuntu0.19.10.1)
-- Dumped by pg_dump version 11.7 (Ubuntu 11.7-0ubuntu0.19.10.1)

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

--
-- Name: cafe; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA cafe;


ALTER SCHEMA cafe OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: drinks; Type: TABLE; Schema: cafe; Owner: postgres
--

CREATE TABLE cafe.drinks (
    id integer NOT NULL,
    drink_price double precision,
    drink_name character varying(255),
    drink_category character varying(255)
);


ALTER TABLE cafe.drinks OWNER TO postgres;

--
-- Name: drinks_id_seq; Type: SEQUENCE; Schema: cafe; Owner: postgres
--

CREATE SEQUENCE cafe.drinks_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cafe.drinks_id_seq OWNER TO postgres;

--
-- Name: drinks_id_seq; Type: SEQUENCE OWNED BY; Schema: cafe; Owner: postgres
--

ALTER SEQUENCE cafe.drinks_id_seq OWNED BY cafe.drinks.id;


--
-- Name: receipts; Type: TABLE; Schema: cafe; Owner: postgres
--

CREATE TABLE cafe.receipts (
    id integer NOT NULL,
    receipt_server character varying(255),
    receipt_date character varying(255),
    total_sum double precision DEFAULT '0'::double precision NOT NULL
);


ALTER TABLE cafe.receipts OWNER TO postgres;

--
-- Name: receipts_id_seq; Type: SEQUENCE; Schema: cafe; Owner: postgres
--

CREATE SEQUENCE cafe.receipts_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cafe.receipts_id_seq OWNER TO postgres;

--
-- Name: receipts_id_seq; Type: SEQUENCE OWNED BY; Schema: cafe; Owner: postgres
--

ALTER SEQUENCE cafe.receipts_id_seq OWNED BY cafe.receipts.id;


--
-- Name: receipts_tables; Type: TABLE; Schema: cafe; Owner: postgres
--

CREATE TABLE cafe.receipts_tables (
    receipt_id integer,
    tables_id integer
);


ALTER TABLE cafe.receipts_tables OWNER TO postgres;

--
-- Name: tables; Type: TABLE; Schema: cafe; Owner: postgres
--

CREATE TABLE cafe.tables (
    id integer NOT NULL,
    table_name character varying(50)
);


ALTER TABLE cafe.tables OWNER TO postgres;

--
-- Name: tables_drinks; Type: TABLE; Schema: cafe; Owner: postgres
--

CREATE TABLE cafe.tables_drinks (
    table_id integer,
    drink_id integer
);


ALTER TABLE cafe.tables_drinks OWNER TO postgres;

--
-- Name: tables_id_seq; Type: SEQUENCE; Schema: cafe; Owner: postgres
--

CREATE SEQUENCE cafe.tables_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cafe.tables_id_seq OWNER TO postgres;

--
-- Name: tables_id_seq; Type: SEQUENCE OWNED BY; Schema: cafe; Owner: postgres
--

ALTER SEQUENCE cafe.tables_id_seq OWNED BY cafe.tables.id;


--
-- Name: all_ordered_drinks; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.all_ordered_drinks AS
 SELECT tb.table_name,
    cd.id AS drink_id,
    cd.drink_name,
    cd.drink_price,
    cd.drink_category
   FROM ((cafe.tables_drinks td
     JOIN cafe.tables tb ON ((tb.id = td.table_id)))
     JOIN cafe.drinks cd ON ((cd.id = td.drink_id)));


ALTER TABLE public.all_ordered_drinks OWNER TO postgres;

--
-- Name: allreceiptsoftable; Type: VIEW; Schema: public; Owner: postgres
--

CREATE VIEW public.allreceiptsoftable AS
 SELECT td.table_id,
    td.drink_id
   FROM cafe.tables_drinks td;


ALTER TABLE public.allreceiptsoftable OWNER TO postgres;

--
-- Name: drinks id; Type: DEFAULT; Schema: cafe; Owner: postgres
--

ALTER TABLE ONLY cafe.drinks ALTER COLUMN id SET DEFAULT nextval('cafe.drinks_id_seq'::regclass);


--
-- Name: receipts id; Type: DEFAULT; Schema: cafe; Owner: postgres
--

ALTER TABLE ONLY cafe.receipts ALTER COLUMN id SET DEFAULT nextval('cafe.receipts_id_seq'::regclass);


--
-- Name: tables id; Type: DEFAULT; Schema: cafe; Owner: postgres
--

ALTER TABLE ONLY cafe.tables ALTER COLUMN id SET DEFAULT nextval('cafe.tables_id_seq'::regclass);


--
-- Data for Name: drinks; Type: TABLE DATA; Schema: cafe; Owner: postgres
--

COPY cafe.drinks (id, drink_price, drink_name, drink_category) FROM stdin;
1	100	Cosmopolitan	Cocktail
2	105	Mojito	Cocktail
3	95	Sex on the beach	Cocktail
4	95	Bloody Mary	Cocktail
5	80	Aperol Spritz	Cocktail
6	80	Guinness	Beer
7	70	Budweiser	Beer
8	75	Corona	Beer
9	65	Lone Star Beer	Beer
10	85	Erdinger	Beer
11	65	Zajecarsko	Beer
\.


--
-- Data for Name: receipts; Type: TABLE DATA; Schema: cafe; Owner: postgres
--

COPY cafe.receipts (id, receipt_server, receipt_date, total_sum) FROM stdin;
16	Administrator	2020/08/17 00:25:58	77
17	Administrator	2020/08/17 00:26:30	50
18	Administrator	2020/08/17 00:48:31	160
20	Administrator	2020/08/17 16:46:02	280
21	Administrator	2020/08/17 16:49:05	280
22	Administrator	2020/08/17 16:51:39	200
\.


--
-- Data for Name: receipts_tables; Type: TABLE DATA; Schema: cafe; Owner: postgres
--

COPY cafe.receipts_tables (receipt_id, tables_id) FROM stdin;
17	58
20	60
21	60
22	112
\.


--
-- Data for Name: tables; Type: TABLE DATA; Schema: cafe; Owner: postgres
--

COPY cafe.tables (id, table_name) FROM stdin;
57	Table 2
58	Table 3
60	Table 5
61	Table 6
62	Table 7
77	Table 8
78	Table 9
96	Table 10
98	Table 12
100	Table 14
112	Table 5
113	Table 5
114	Table 13
115	Table 14
116	Table 15
\.


--
-- Data for Name: tables_drinks; Type: TABLE DATA; Schema: cafe; Owner: postgres
--

COPY cafe.tables_drinks (table_id, drink_id) FROM stdin;
60	2
60	4
60	5
112	1
112	1
57	7
57	8
57	5
57	6
57	9
57	9
\.


--
-- Name: drinks_id_seq; Type: SEQUENCE SET; Schema: cafe; Owner: postgres
--

SELECT pg_catalog.setval('cafe.drinks_id_seq', 11, true);


--
-- Name: receipts_id_seq; Type: SEQUENCE SET; Schema: cafe; Owner: postgres
--

SELECT pg_catalog.setval('cafe.receipts_id_seq', 22, true);


--
-- Name: tables_id_seq; Type: SEQUENCE SET; Schema: cafe; Owner: postgres
--

SELECT pg_catalog.setval('cafe.tables_id_seq', 116, true);


--
-- Name: drinks drinks_pkey; Type: CONSTRAINT; Schema: cafe; Owner: postgres
--

ALTER TABLE ONLY cafe.drinks
    ADD CONSTRAINT drinks_pkey PRIMARY KEY (id);


--
-- Name: receipts receipts_pkey; Type: CONSTRAINT; Schema: cafe; Owner: postgres
--

ALTER TABLE ONLY cafe.receipts
    ADD CONSTRAINT receipts_pkey PRIMARY KEY (id);


--
-- Name: tables tables_pkey; Type: CONSTRAINT; Schema: cafe; Owner: postgres
--

ALTER TABLE ONLY cafe.tables
    ADD CONSTRAINT tables_pkey PRIMARY KEY (id);


--
-- Name: receipts_tables receipts_tables_receipt_id_fkey; Type: FK CONSTRAINT; Schema: cafe; Owner: postgres
--

ALTER TABLE ONLY cafe.receipts_tables
    ADD CONSTRAINT receipts_tables_receipt_id_fkey FOREIGN KEY (receipt_id) REFERENCES cafe.receipts(id) ON DELETE CASCADE;


--
-- Name: receipts_tables receipts_tables_tables_id_fkey; Type: FK CONSTRAINT; Schema: cafe; Owner: postgres
--

ALTER TABLE ONLY cafe.receipts_tables
    ADD CONSTRAINT receipts_tables_tables_id_fkey FOREIGN KEY (tables_id) REFERENCES cafe.tables(id) ON DELETE CASCADE;


--
-- Name: tables_drinks tables_drinks_drink_id_fkey; Type: FK CONSTRAINT; Schema: cafe; Owner: postgres
--

ALTER TABLE ONLY cafe.tables_drinks
    ADD CONSTRAINT tables_drinks_drink_id_fkey FOREIGN KEY (drink_id) REFERENCES cafe.drinks(id);


--
-- Name: tables_drinks tables_drinks_table_id_fkey; Type: FK CONSTRAINT; Schema: cafe; Owner: postgres
--

ALTER TABLE ONLY cafe.tables_drinks
    ADD CONSTRAINT tables_drinks_table_id_fkey FOREIGN KEY (table_id) REFERENCES cafe.tables(id);


--
-- PostgreSQL database dump complete
--

