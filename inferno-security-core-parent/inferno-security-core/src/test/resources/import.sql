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

--
-- Name: inferno_authorization_schema; Type: SCHEMA; Schema: -; Owner: inferno_init_db_admin
--

CREATE SCHEMA "inferno_authorization_schema";


ALTER SCHEMA "inferno_authorization_schema" OWNER TO "inferno_init_db_admin";

SET search_path = "inferno_authorization_schema", pg_catalog;

--
-- Name: update_modified_timestamp_column(); Type: FUNCTION; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

CREATE FUNCTION "update_modified_timestamp_column"() RETURNS "trigger"
    LANGUAGE "plpgsql"
    AS $$

BEGIN

   NEW.last_modified_date = now(); 

   NEW.last_modified_by = current_user;

   RETURN NEW;

END;

$$;


ALTER FUNCTION "inferno_authorization_schema"."update_modified_timestamp_column"() OWNER TO "inferno_init_db_admin";

--
-- Name: inferno_address_seq; Type: SEQUENCE; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

CREATE SEQUENCE "inferno_address_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "inferno_address_seq" OWNER TO "inferno_init_db_admin";

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: inferno_address; Type: TABLE; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

CREATE TABLE "inferno_address" (
    "address_id" bigint DEFAULT "nextval"('"inferno_address_seq"'::"regclass") NOT NULL,
    "person_id" bigint,
    "type" character varying(255),
    "street" character varying(255),
    "building_nr" character varying(255),
    "appartment" character varying(255),
    "post_code" character varying(255),
    "city" character varying(255),
    "district" character varying(255),
    "created_by" "name" DEFAULT CURRENT_USER NOT NULL,
    "created_date" timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "last_modified_by" "name",
    "last_modified_date" timestamp without time zone
);


ALTER TABLE "inferno_address" OWNER TO "inferno_init_db_admin";

--
-- Name: inferno_person_seq; Type: SEQUENCE; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

CREATE SEQUENCE "inferno_person_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "inferno_person_seq" OWNER TO "inferno_init_db_admin";

--
-- Name: inferno_person; Type: TABLE; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

CREATE TABLE "inferno_person" (
    "person_id" bigint DEFAULT "nextval"('"inferno_person_seq"'::"regclass") NOT NULL,
    "user_id" bigint NOT NULL,
    "first_name" character varying(255),
    "last_name" character varying(255),
    "date_of_birth" "date",
    "created_by" "name" DEFAULT CURRENT_USER NOT NULL,
    "created_date" timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "last_modified_by" "name",
    "last_modified_date" timestamp without time zone
);


ALTER TABLE "inferno_person" OWNER TO "inferno_init_db_admin";

--
-- Name: inferno_roles_seq; Type: SEQUENCE; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

CREATE SEQUENCE "inferno_roles_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "inferno_roles_seq" OWNER TO "inferno_init_db_admin";

--
-- Name: SEQUENCE "inferno_roles_seq"; Type: COMMENT; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

COMMENT ON SEQUENCE "inferno_roles_seq" IS 'Sekwencja dla tabeli inferno_roles';


--
-- Name: inferno_roles; Type: TABLE; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

CREATE TABLE "inferno_roles" (
    "role_id" bigint DEFAULT "nextval"('"inferno_roles_seq"'::"regclass") NOT NULL,
    "name" character varying(255) NOT NULL,
    "description" character varying(255),
    "valid_to" timestamp without time zone,
    "created_by" "name" DEFAULT CURRENT_USER NOT NULL,
    "created_date" timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "last_modified_by" "name",
    "last_modified_date" timestamp without time zone
);


ALTER TABLE "inferno_roles" OWNER TO "inferno_init_db_admin";

--
-- Name: inferno_user_roles_assigment_seq; Type: SEQUENCE; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

CREATE SEQUENCE "inferno_user_roles_assigment_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "inferno_user_roles_assigment_seq" OWNER TO "inferno_init_db_admin";

--
-- Name: inferno_roles_assigment; Type: TABLE; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

CREATE TABLE "inferno_roles_assigment" (
    "user_role_id" bigint DEFAULT "nextval"('"inferno_user_roles_assigment_seq"'::"regclass") NOT NULL,
    "user_user_id" bigint,
    "assigned_role_id" bigint,
    "authority" character varying(255) NOT NULL,
    "valid_to" timestamp without time zone,
    "created_by" "name" DEFAULT CURRENT_USER NOT NULL,
    "created_date" timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "last_modified_by" "name",
    "last_modified_date" timestamp without time zone
);


ALTER TABLE "inferno_roles_assigment" OWNER TO "inferno_init_db_admin";

--
-- Name: inferno_users_seq; Type: SEQUENCE; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

CREATE SEQUENCE "inferno_users_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "inferno_users_seq" OWNER TO "inferno_init_db_admin";

--
-- Name: SEQUENCE "inferno_users_seq"; Type: COMMENT; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

COMMENT ON SEQUENCE "inferno_users_seq" IS 'Sekwencja dla tabeli inferno_users';


--
-- Name: inferno_users; Type: TABLE; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

CREATE TABLE "inferno_users" (
    "user_id" bigint DEFAULT "nextval"('"inferno_users_seq"'::"regclass") NOT NULL,
    "username" character varying(255) NOT NULL,
    "password" character varying(255) NOT NULL,
    "enabled" boolean NOT NULL,
    "is_locked" boolean NOT NULL,
    "is_expired" boolean NOT NULL,
    "credentials_expired" boolean NOT NULL,
    "expires" timestamp without time zone,
    "created_by" "name" DEFAULT CURRENT_USER NOT NULL,
    "created_date" timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "last_modified_by" "name",
    "last_modified_date" timestamp without time zone
);


ALTER TABLE "inferno_users" OWNER TO "inferno_init_db_admin";

--
-- Data for Name: inferno_address; Type: TABLE DATA; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

INSERT INTO "inferno_address" ("address_id", "person_id", "type", "street", "building_nr", "appartment", "post_code", "city", "district", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (1, 1, 'MAIN', 'testowa', '0', '00', '03-242', 'Warszawa', 'mazowieckie', 'postgres', '2018-02-28 16:38:09.684441', NULL, NULL);
INSERT INTO "inferno_address" ("address_id", "person_id", "type", "street", "building_nr", "appartment", "post_code", "city", "district", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (2, 1, 'CORRESPONDENCE', 'testowa', '0', '00', '03-242', 'Warszawa', 'mazowieckie', 'postgres', '2018-02-28 16:38:09.688723', NULL, NULL);


--
-- Data for Name: inferno_person; Type: TABLE DATA; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

INSERT INTO "inferno_person" ("person_id", "user_id", "first_name", "last_name", "date_of_birth", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (1, 1, 'INIT', 'USER', '1977-11-27', 'postgres', '2018-02-28 16:38:09.680734', NULL, NULL);
INSERT INTO "inferno_person" ("person_id", "user_id", "first_name", "last_name", "date_of_birth", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (2, 2, 'tester', 'tester', NULL, 'postgres', '2018-02-28 16:53:57.956306', NULL, NULL);


--
-- Data for Name: inferno_roles; Type: TABLE DATA; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

INSERT INTO "inferno_roles" ("role_id", "name", "description", "valid_to", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (1, 'ROLE_APPLICATION', 'Internal role for applications.', NULL, 'postgres', '2018-02-28 16:38:09.691449', NULL, NULL);
INSERT INTO "inferno_roles" ("role_id", "name", "description", "valid_to", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (2, 'ROLE_API', 'API role.', NULL, 'postgres', '2018-02-28 16:38:09.694353', NULL, NULL);
INSERT INTO "inferno_roles" ("role_id", "name", "description", "valid_to", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (3, 'ROLE_TECH', 'Technical role for technical users and maintenance purposes, ie. initialization of the database.', NULL, 'postgres', '2018-02-28 16:38:09.697038', NULL, NULL);
INSERT INTO "inferno_roles" ("role_id", "name", "description", "valid_to", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (4, 'ROLE_ADMIN', 'Administration role.', NULL, 'postgres', '2018-02-28 16:38:09.699622', NULL, NULL);
INSERT INTO "inferno_roles" ("role_id", "name", "description", "valid_to", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (5, 'ROLE_USER', 'User role.', NULL, 'postgres', '2018-02-28 16:56:44.788503', NULL, NULL);


--
-- Data for Name: inferno_roles_assigment; Type: TABLE DATA; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

INSERT INTO "inferno_roles_assigment" ("user_role_id", "user_user_id", "assigned_role_id", "authority", "valid_to", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (1, 1, 3, 'ROLE_TECH', NULL, 'postgres', '2018-02-28 16:38:09.702096', NULL, NULL);
INSERT INTO "inferno_roles_assigment" ("user_role_id", "user_user_id", "assigned_role_id", "authority", "valid_to", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (4, 2, 5, 'ROLE_USER', NULL, 'postgres', '2018-02-28 16:57:19.035573', NULL, NULL);


--
-- Data for Name: inferno_users; Type: TABLE DATA; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

INSERT INTO "inferno_users" ("user_id", "username", "password", "enabled", "is_locked", "is_expired", "credentials_expired", "expires", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (1, 'inferno_app_admin', '$2a$10$YLCsu3Wstvl7dOVi3ifycO55zDp6UIQhOLjstEybzKiB.HcnyU76a', true, false, false, false, NULL, 'postgres', '2018-02-28 16:38:09.677182', NULL, NULL);
INSERT INTO "inferno_users" ("user_id", "username", "password", "enabled", "is_locked", "is_expired", "credentials_expired", "expires", "created_by", "created_date", "last_modified_by", "last_modified_date") VALUES (2, 'inferno_test', 'cc03e747a6afbbcbf8be7668acfebee5', true, false, false, false, NULL, 'postgres', '2018-02-28 16:50:20.144545', 'postgres', '2018-02-28 16:51:37.094859');


--
-- Name: inferno_address_seq; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

SELECT pg_catalog.setval('"inferno_address_seq"', 1, false);


--
-- Name: inferno_person_seq; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

SELECT pg_catalog.setval('"inferno_person_seq"', 2, true);


--
-- Name: inferno_roles_seq; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

SELECT pg_catalog.setval('"inferno_roles_seq"', 5, true);


--
-- Name: inferno_user_roles_assigment_seq; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

SELECT pg_catalog.setval('"inferno_user_roles_assigment_seq"', 4, true);


--
-- Name: inferno_users_seq; Type: SEQUENCE SET; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

SELECT pg_catalog.setval('"inferno_users_seq"', 2, true);


--
-- Name: inferno_address inferno_address_pkey; Type: CONSTRAINT; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

ALTER TABLE ONLY "inferno_address"
    ADD CONSTRAINT "inferno_address_pkey" PRIMARY KEY ("address_id");


--
-- Name: inferno_person inferno_person_pkey; Type: CONSTRAINT; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

ALTER TABLE ONLY "inferno_person"
    ADD CONSTRAINT "inferno_person_pkey" PRIMARY KEY ("person_id");


--
-- Name: inferno_roles_assigment inferno_roles_assigment_pkey; Type: CONSTRAINT; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

ALTER TABLE ONLY "inferno_roles_assigment"
    ADD CONSTRAINT "inferno_roles_assigment_pkey" PRIMARY KEY ("user_role_id");


--
-- Name: inferno_roles inferno_roles_pkey; Type: CONSTRAINT; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

ALTER TABLE ONLY "inferno_roles"
    ADD CONSTRAINT "inferno_roles_pkey" PRIMARY KEY ("role_id");


--
-- Name: inferno_users inferno_users_pkey; Type: CONSTRAINT; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

ALTER TABLE ONLY "inferno_users"
    ADD CONSTRAINT "inferno_users_pkey" PRIMARY KEY ("user_id");


--
-- Name: inferno_users uk_as31qd8m2kj6mg5a7hdkhfokk; Type: CONSTRAINT; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

ALTER TABLE ONLY "inferno_users"
    ADD CONSTRAINT "uk_as31qd8m2kj6mg5a7hdkhfokk" UNIQUE ("username");


--
-- Name: inferno_roles uk_nqbi2i1pc82apmu8dto91ob21; Type: CONSTRAINT; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

ALTER TABLE ONLY "inferno_roles"
    ADD CONSTRAINT "uk_nqbi2i1pc82apmu8dto91ob21" UNIQUE ("name");


--
-- Name: inferno_address update_inferno_address_modification; Type: TRIGGER; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

CREATE TRIGGER "update_inferno_address_modification" BEFORE UPDATE ON "inferno_address" FOR EACH ROW EXECUTE PROCEDURE "update_modified_timestamp_column"();


--
-- Name: inferno_person update_inferno_person_modification; Type: TRIGGER; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

CREATE TRIGGER "update_inferno_person_modification" BEFORE UPDATE ON "inferno_person" FOR EACH ROW EXECUTE PROCEDURE "update_modified_timestamp_column"();


--
-- Name: inferno_roles_assigment update_inferno_roles_assigment_modification; Type: TRIGGER; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

CREATE TRIGGER "update_inferno_roles_assigment_modification" BEFORE UPDATE ON "inferno_roles_assigment" FOR EACH ROW EXECUTE PROCEDURE "update_modified_timestamp_column"();


--
-- Name: inferno_roles update_inferno_roles_modification; Type: TRIGGER; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

CREATE TRIGGER "update_inferno_roles_modification" BEFORE UPDATE ON "inferno_roles" FOR EACH ROW EXECUTE PROCEDURE "update_modified_timestamp_column"();


--
-- Name: inferno_users update_inferno_users_modification; Type: TRIGGER; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

CREATE TRIGGER "update_inferno_users_modification" BEFORE UPDATE ON "inferno_users" FOR EACH ROW EXECUTE PROCEDURE "update_modified_timestamp_column"();


--
-- Name: inferno_address FK45uctwmpfedlp9tf5l22nepgh; Type: FK CONSTRAINT; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

ALTER TABLE ONLY "inferno_address"
    ADD CONSTRAINT "FK45uctwmpfedlp9tf5l22nepgh" FOREIGN KEY ("person_id") REFERENCES "inferno_person"("person_id");


--
-- Name: inferno_person inferno_person_user_id_fkey; Type: FK CONSTRAINT; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

ALTER TABLE ONLY "inferno_person"
    ADD CONSTRAINT "inferno_person_user_id_fkey" FOREIGN KEY ("user_id") REFERENCES "inferno_users"("user_id");


--
-- Name: inferno_roles_assigment inferno_roles_assigment_assigned_role_id_fkey; Type: FK CONSTRAINT; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

ALTER TABLE ONLY "inferno_roles_assigment"
    ADD CONSTRAINT "inferno_roles_assigment_assigned_role_id_fkey" FOREIGN KEY ("assigned_role_id") REFERENCES "inferno_roles"("role_id");


--
-- Name: inferno_roles_assigment inferno_roles_assigment_authority_fkey; Type: FK CONSTRAINT; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

ALTER TABLE ONLY "inferno_roles_assigment"
    ADD CONSTRAINT "inferno_roles_assigment_authority_fkey" FOREIGN KEY ("authority") REFERENCES "inferno_roles"("name");


--
-- Name: inferno_roles_assigment inferno_roles_assigment_user_user_id_fkey; Type: FK CONSTRAINT; Schema: inferno_authorization_schema; Owner: inferno_init_db_admin
--

ALTER TABLE ONLY "inferno_roles_assigment"
    ADD CONSTRAINT "inferno_roles_assigment_user_user_id_fkey" FOREIGN KEY ("user_user_id") REFERENCES "inferno_users"("user_id");


--
-- PostgreSQL database dump complete
--

