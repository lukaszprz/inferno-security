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

ALTER TABLE IF EXISTS ONLY inferno_authorization_schema.user_role DROP CONSTRAINT "FKas0229axkj9fxglu7mhbe2qev";
ALTER TABLE IF EXISTS ONLY inferno_authorization_schema.user_role DROP CONSTRAINT "FK24wua7vq4siwv5xbp3nb1seiy";
ALTER TABLE IF EXISTS ONLY inferno_authorization_schema.user_role DROP CONSTRAINT user_role_pkey;
ALTER TABLE IF EXISTS ONLY inferno_authorization_schema.inferno_roles DROP CONSTRAINT uk_nqbi2i1pc82apmu8dto91ob21;
ALTER TABLE IF EXISTS ONLY inferno_authorization_schema.inferno_users DROP CONSTRAINT uk_as31qd8m2kj6mg5a7hdkhfokk;
ALTER TABLE IF EXISTS ONLY inferno_authorization_schema.inferno_users DROP CONSTRAINT inferno_users_pkey;
ALTER TABLE IF EXISTS ONLY inferno_authorization_schema.inferno_roles DROP CONSTRAINT inferno_roles_pkey;
DROP TABLE IF EXISTS inferno_authorization_schema.user_role;
DROP TABLE IF EXISTS inferno_authorization_schema.inferno_users;
DROP TABLE IF EXISTS inferno_authorization_schema.inferno_roles;
DROP SEQUENCE IF EXISTS inferno_authorization_schema.hibernate_sequence;
DROP SCHEMA IF EXISTS inferno_authorization_schema CASCADE;
--
-- Name: inferno_authorization_schema; Type: SCHEMA; Schema: -; Owner: inferno-app
--

CREATE SCHEMA inferno_authorization_schema;


ALTER SCHEMA inferno_authorization_schema OWNER TO "inferno-app";

SET search_path = inferno_authorization_schema, pg_catalog;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: inferno_authorization_schema; Owner: inferno-app
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    
ALTER TABLE hibernate_sequence OWNER TO "inferno-app";

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: inferno_roles; Type: TABLE; Schema: inferno_authorization_schema; Owner: inferno-app
--

CREATE TABLE inferno_roles (
    role_id bigserial NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE IF EXISTS inferno_roles OWNER TO "inferno-app";

--
-- Name: inferno_users; Type: TABLE; Schema: inferno_authorization_schema; Owner: inferno-app
--

CREATE TABLE inferno_users (
    user_id bigserial NOT NULL,
    active boolean,
    created timestamp without time zone DEFAULT now() NOT NULL,
    password character varying(255),
    username character varying(255) NOT NULL,
    valid_to timestamp without time zone
);


ALTER TABLE IF EXISTS inferno_users OWNER TO "inferno-app";

--
-- Name: user_role; Type: TABLE; Schema: inferno_authorization_schema; Owner: inferno-app
--

CREATE TABLE user_role (
    user_id bigserial NOT NULL,
    role_id bigserial NOT NULL
);


ALTER TABLE IF EXISTS user_role OWNER TO "inferno-app";

--
-- Name: inferno_roles inferno_roles_pkey; Type: CONSTRAINT; Schema: inferno_authorization_schema; Owner: inferno-app
--

ALTER TABLE IF EXISTS ONLY inferno_roles
    ADD CONSTRAINT inferno_roles_pkey PRIMARY KEY (role_id);


--
-- Name: inferno_users inferno_users_pkey; Type: CONSTRAINT; Schema: inferno_authorization_schema; Owner: inferno-app
--

ALTER TABLE IF EXISTS ONLY inferno_users
    ADD CONSTRAINT inferno_users_pkey PRIMARY KEY (user_id);


--
-- Name: inferno_users uk_as31qd8m2kj6mg5a7hdkhfokk; Type: CONSTRAINT; Schema: inferno_authorization_schema; Owner: inferno-app
--

ALTER TABLE IF EXISTS ONLY inferno_users
    ADD CONSTRAINT uk_as31qd8m2kj6mg5a7hdkhfokk UNIQUE (username);


--
-- Name: inferno_roles uk_nqbi2i1pc82apmu8dto91ob21; Type: CONSTRAINT; Schema: inferno_authorization_schema; Owner: inferno-app
--

ALTER TABLE IF EXISTS ONLY inferno_roles
    ADD CONSTRAINT uk_nqbi2i1pc82apmu8dto91ob21 UNIQUE (name);


--
-- Name: user_role user_role_pkey; Type: CONSTRAINT; Schema: inferno_authorization_schema; Owner: inferno-app
--

ALTER TABLE IF EXISTS ONLY user_role
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (user_id, role_id);


--
-- Name: user_role FK24wua7vq4siwv5xbp3nb1seiy; Type: FK CONSTRAINT; Schema: inferno_authorization_schema; Owner: inferno-app
--

ALTER TABLE IF EXISTS ONLY user_role
    ADD CONSTRAINT "FK24wua7vq4siwv5xbp3nb1seiy" FOREIGN KEY (user_id) REFERENCES inferno_users(user_id);


--
-- Name: user_role FKas0229axkj9fxglu7mhbe2qev; Type: FK CONSTRAINT; Schema: inferno_authorization_schema; Owner: inferno-app
--

ALTER TABLE IF EXISTS ONLY user_role
    ADD CONSTRAINT "FKas0229axkj9fxglu7mhbe2qev" FOREIGN KEY (role_id) REFERENCES inferno_roles(role_id);


--
-- PostgreSQL database dump complete
--

