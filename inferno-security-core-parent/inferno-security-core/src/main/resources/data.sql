--
-- PostgreSQL database dump
--

-- Dumped from database version 10.2 (Debian 10.2-1.pgdg80+1)
-- Dumped by pg_dump version 10.2 (Debian 10.2-1.pgdg80+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = "inferno_authorization_schema", pg_catalog;

--
-- Data for Name: inferno_users; Type: TABLE DATA; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

INSERT INTO "inferno_users" ("is_expired", "is_locked", "credentials_expired", "enabled", "expires", "password", "username") VALUES (false, false, false, true, NULL, '$2a$10$npSqupAAOoj8T6pF7eQnr.c5ieuTC/j859Kynno.iUrjsSe9O0e7C', 'inferno_app_admin');


--
-- Data for Name: inferno_person; Type: TABLE DATA; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

INSERT INTO "inferno_person" ("date_of_birth", "first_name", "last_name", "user_id") VALUES ('1977-11-27', 'INIT', 'USER', 1);


--
-- Data for Name: inferno_address; Type: TABLE DATA; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

INSERT INTO "inferno_address" ("appartment", "building_nr", "city", "district", "post_code", "street", "type", "person_id") VALUES ('00', '0', 'Warszawa', 'mazowieckie', '03-242', 'testowa', 'MAIN', 1);
INSERT INTO "inferno_address" ("appartment", "building_nr", "city", "district", "post_code", "street", "type", "person_id") VALUES ('00', '0', 'Warszawa', 'mazowieckie', '03-242', 'testowa', 'CORRESPONDENCE', 1);


--
-- Data for Name: inferno_roles; Type: TABLE DATA; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

INSERT INTO "inferno_roles" ("description", "name", "valid_to") VALUES ('Internal role for applications.', 'ROLE_APPLICATION', NULL);
INSERT INTO "inferno_roles" ("description", "name", "valid_to") VALUES ('API role.', 'ROLE_API', NULL);
INSERT INTO "inferno_roles" ("description", "name", "valid_to") VALUES ('Technical role for technical users and maintenance purposes, ie. initialization of the database.', 'ROLE_TECH', NULL);
INSERT INTO "inferno_roles" ("description", "name", "valid_to") VALUES ('Administration role.', 'ROLE_ADMIN', NULL);


--
-- Data for Name: inferno_roles_assigment; Type: TABLE DATA; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

INSERT INTO "inferno_roles_assigment" ("authority", "valid_to", "assigned_role_id", "user_user_id") VALUES ('ROLE_TECH', NULL, 3, 1);


--
-- Name: inferno_address_seq; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

SELECT pg_catalog.setval('"inferno_address_seq"', 1, false);


--
-- Name: inferno_person_seq; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

SELECT pg_catalog.setval('"inferno_person_seq"', 1, false);


--
-- Name: inferno_roles_seq; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

SELECT pg_catalog.setval('"inferno_roles_seq"', 1, false);


--
-- Name: inferno_user_roles_assigment_seq; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

SELECT pg_catalog.setval('"inferno_user_roles_assigment_seq"', 1, false);


--
-- Name: inferno_users_seq; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

SELECT pg_catalog.setval('"inferno_users_seq"', 1, true);


--
-- PostgreSQL database dump complete
--

