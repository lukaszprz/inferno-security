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
-- Data for Name: encryption_tes; Type: TABLE DATA; Schema: inferno_authorization_schema; Owner: postgres
--

INSERT INTO "encryption_tes" ("string_token") VALUES ('\303\015\004\007\003\002\245\267y\371\214\366\316\210m\322;\001\345\360[\023\314\205\370\250H\271z>\227\261\340\245$\224\354\255\240E\337T\321\376A\256\217\270\3466C\353\231S\033&@\370\311\011e\234\0277c\343U\304\3668\306>\004\232C\316');


--
-- Data for Name: inferno_users; Type: TABLE DATA; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

INSERT INTO "inferno_users" ("user_id", "username", "password", "enabled", "is_locked", "is_expired", "credentials_expired", "expires", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (1, 'inferno_app_admin', '$2a$10$0Me.QPqh9Shj1nXdFt1TRuKdcBwGDiUoZdwiKE2OaO1bOvw82WXTy', true, false, false, false, NULL, 'postgres', '2018-03-02 17:39:04.436', 'inferno_init_db_admin', '2018-03-09 01:42:21.623509');
INSERT INTO "inferno_users" ("user_id", "username", "password", "enabled", "is_locked", "is_expired", "credentials_expired", "expires", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (2, 'inferno_test', '$2a$10$GLghkhF8fespeQ0XQlwsLugfP7fnasMm8yUQhmmEFqXpFoXsTkBfW', true, false, false, false, '2018-04-02 09:13:16.495721', 'postgres', '2018-03-03 09:13:16.495', 'inferno_init_db_admin', '2018-03-09 01:55:22.123618');


--
-- Data for Name: inferno_person; Type: TABLE DATA; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

INSERT INTO "inferno_person" ("person_id", "user_id", "first_name", "last_name", "date_of_birth", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (1, 1, 'INIT', 'USER', '1977-11-27', 'postgres', '2018-03-02 17:39:04.439245', NULL, NULL);
INSERT INTO "inferno_person" ("person_id", "user_id", "first_name", "last_name", "date_of_birth", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (2, 2, 'Tester', 'Testowy', '1988-03-03', 'postgres', '2018-03-03 09:16:27.708922', NULL, NULL);


--
-- Data for Name: inferno_address; Type: TABLE DATA; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

INSERT INTO "inferno_address" ("address_id", "person_id", "type", "street", "building_nr", "appartment", "post_code", "city", "district", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (1, 1, 'MAIN', 'testowa', '0', '00', '03-242', 'Warszawa', 'mazowieckie', 'postgres', '2018-03-02 17:39:04.44276', NULL, NULL);
INSERT INTO "inferno_address" ("address_id", "person_id", "type", "street", "building_nr", "appartment", "post_code", "city", "district", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (2, 1, 'CORRESPONDENCE', 'testowa', '0', '00', '03-242', 'Warszawa', 'mazowieckie', 'postgres', '2018-03-02 17:39:04.445977', NULL, NULL);
INSERT INTO "inferno_address" ("address_id", "person_id", "type", "street", "building_nr", "appartment", "post_code", "city", "district", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (3, 2, 'MAIN', 'Testowa', '1', '2', '01-001', 'Warszawa', 'mazowieckie', 'postgres', '2018-03-03 09:18:37.418182', NULL, NULL);
INSERT INTO "inferno_address" ("address_id", "person_id", "type", "street", "building_nr", "appartment", "post_code", "city", "district", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (4, 2, 'CORRESPONDENCE', 'Testowa', '1', '2', '01-001', 'Warszawa', 'mazowieckie', 'postgres', '2018-03-03 09:56:31.718046', NULL, NULL);


--
-- Data for Name: inferno_roles; Type: TABLE DATA; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

INSERT INTO "inferno_roles" ("role_id", "name", "description", "valid_to", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (1, 'ROLE_APPLICATION', 'Internal role for applications.', NULL, 'postgres', '2018-03-02 17:39:04.448379', NULL, NULL);
INSERT INTO "inferno_roles" ("role_id", "name", "description", "valid_to", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (2, 'ROLE_API', 'API role.', NULL, 'postgres', '2018-03-02 17:39:04.450578', NULL, NULL);
INSERT INTO "inferno_roles" ("role_id", "name", "description", "valid_to", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (3, 'ROLE_TECH', 'Technical role for technical users and maintenance purposes, ie. initialization of the database.', NULL, 'postgres', '2018-03-02 17:39:04.452816', NULL, NULL);
INSERT INTO "inferno_roles" ("role_id", "name", "description", "valid_to", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (4, 'ROLE_ADMIN', 'Administration role.', NULL, 'postgres', '2018-03-02 17:39:04.454757', NULL, NULL);
INSERT INTO "inferno_roles" ("role_id", "name", "description", "valid_to", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (5, 'ROLE_USER', 'User role.', NULL, 'postgres', '2018-03-02 22:07:14.604292', NULL, NULL);
INSERT INTO "inferno_roles" ("role_id", "name", "description", "valid_to", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (6, 'ROLE_TEST', 'Test role.', '2018-04-02 09:14:25.206674', 'postgres', '2018-03-03 09:14:25.206674', NULL, NULL);


--
-- Data for Name: inferno_roles_assigment; Type: TABLE DATA; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

INSERT INTO "inferno_roles_assigment" ("user_role_id", "user_user_id", "assigned_role_id", "authority", "valid_to", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (1, 1, 3, 'ROLE_TECH', NULL, 'postgres', '2018-03-02 17:39:04.456906', NULL, NULL);
INSERT INTO "inferno_roles_assigment" ("user_role_id", "user_user_id", "assigned_role_id", "authority", "valid_to", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (2, 1, 5, 'ROLE_USER', NULL, 'postgres', '2018-03-02 22:07:52.857508', NULL, NULL);
INSERT INTO "inferno_roles_assigment" ("user_role_id", "user_user_id", "assigned_role_id", "authority", "valid_to", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (3, 1, 4, 'ROLE_ADMIN', NULL, 'postgres', '2018-03-02 22:14:59.160289', NULL, NULL);
INSERT INTO "inferno_roles_assigment" ("user_role_id", "user_user_id", "assigned_role_id", "authority", "valid_to", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (4, 2, 6, 'ROLE_TEST', '2018-02-28 09:59:27.321631', 'postgres', '2018-03-03 09:59:27.321631', NULL, NULL);
INSERT INTO "inferno_roles_assigment" ("user_role_id", "user_user_id", "assigned_role_id", "authority", "valid_to", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (9, 2, 5, 'ROLE_USER', NULL, 'postgres', '2018-03-03 10:13:16.74694', NULL, NULL);


--
-- Name: inferno_address_seq; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

SELECT pg_catalog.setval('"inferno_address_seq"', 4, true);


--
-- Name: inferno_person_seq; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

SELECT pg_catalog.setval('"inferno_person_seq"', 2, true);


--
-- Name: inferno_roles_seq; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

SELECT pg_catalog.setval('"inferno_roles_seq"', 6, true);


--
-- Name: inferno_user_roles_assigment_seq; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

SELECT pg_catalog.setval('"inferno_user_roles_assigment_seq"', 10, true);


--
-- Name: inferno_users_seq; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

SELECT pg_catalog.setval('"inferno_users_seq"', 2, true);


--
-- PostgreSQL database dump complete
--

