--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
-- Dumped by pg_dump version 9.6.5

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = inferno_authorization_schema, pg_catalog;

--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno-app
--

SELECT pg_catalog.setval('hibernate_sequence', 4, true);


--
-- Data for Name: inferno_roles; Type: TABLE DATA; Schema: inferno_authorization_schema; Owner: inferno-app
--

INSERT INTO inferno_roles VALUES (1, 'ROLE_ADMIN');
INSERT INTO inferno_roles VALUES (2, 'ROLE_USER');


--
-- Name: inferno_roles_role_id_seq; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno-app
--

SELECT pg_catalog.setval('inferno_roles_role_id_seq', 1, false);


--
-- Data for Name: inferno_users; Type: TABLE DATA; Schema: inferno_authorization_schema; Owner: inferno-app
--

INSERT INTO inferno_users VALUES (3, true, '2017-11-06 08:44:02.148', '$2a$10$BV17qYNsgCT3WlK8nserz.Re2XiEgxT9nnhgY44km0Yuir73wYAWO', 'inferno-admin', NULL);
INSERT INTO inferno_users VALUES (4, true, '2017-11-06 08:44:02.215', '$2a$10$Yus5FoBSKHmf4udUeQa1f.Ap9ozUnLJn0kW52DjaWqWteQWPsHk.m', 'inferno-user', '2017-12-06 08:44:02.3');


--
-- Name: inferno_users_user_id_seq; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno-app
--

SELECT pg_catalog.setval('inferno_users_user_id_seq', 1, false);


--
-- Data for Name: user_role; Type: TABLE DATA; Schema: inferno_authorization_schema; Owner: inferno-app
--

INSERT INTO user_role VALUES (3, 1);
INSERT INTO user_role VALUES (4, 2);


--
-- Name: user_role_role_id_seq; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno-app
--

SELECT pg_catalog.setval('user_role_role_id_seq', 1, false);


--
-- Name: user_role_user_id_seq; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno-app
--

SELECT pg_catalog.setval('user_role_user_id_seq', 1, false);


--
-- PostgreSQL database dump complete
--

