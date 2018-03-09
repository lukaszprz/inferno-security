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

ALTER TABLE ONLY "inferno_authorization_schema"."inferno_roles_assigment" DROP CONSTRAINT "inferno_roles_assigment_user_user_id_fkey";
ALTER TABLE ONLY "inferno_authorization_schema"."inferno_roles_assigment" DROP CONSTRAINT "inferno_roles_assigment_authority_fkey";
ALTER TABLE ONLY "inferno_authorization_schema"."inferno_roles_assigment" DROP CONSTRAINT "inferno_roles_assigment_assigned_role_id_fkey";
ALTER TABLE ONLY "inferno_authorization_schema"."inferno_person" DROP CONSTRAINT "inferno_person_user_id_fkey";
ALTER TABLE ONLY "inferno_authorization_schema"."inferno_address" DROP CONSTRAINT "FK45uctwmpfedlp9tf5l22nepgh";
DROP TRIGGER "update_inferno_users_modification" ON "inferno_authorization_schema"."inferno_users";
DROP TRIGGER "update_inferno_roles_modification" ON "inferno_authorization_schema"."inferno_roles";
DROP TRIGGER "update_inferno_roles_assigment_modification" ON "inferno_authorization_schema"."inferno_roles_assigment";
DROP TRIGGER "update_inferno_person_modification" ON "inferno_authorization_schema"."inferno_person";
DROP TRIGGER "update_inferno_address_modification" ON "inferno_authorization_schema"."inferno_address";
ALTER TABLE ONLY "inferno_authorization_schema"."inferno_roles" DROP CONSTRAINT "uk_nqbi2i1pc82apmu8dto91ob21";
ALTER TABLE ONLY "inferno_authorization_schema"."inferno_users" DROP CONSTRAINT "uk_as31qd8m2kj6mg5a7hdkhfokk";
ALTER TABLE ONLY "inferno_authorization_schema"."inferno_users" DROP CONSTRAINT "inferno_users_pkey";
ALTER TABLE ONLY "inferno_authorization_schema"."inferno_roles" DROP CONSTRAINT "inferno_roles_pkey";
ALTER TABLE ONLY "inferno_authorization_schema"."inferno_roles_assigment" DROP CONSTRAINT "inferno_roles_assigment_pkey";
ALTER TABLE ONLY "inferno_authorization_schema"."inferno_person" DROP CONSTRAINT "inferno_person_pkey";
ALTER TABLE ONLY "inferno_authorization_schema"."inferno_address" DROP CONSTRAINT "inferno_address_pkey";
DROP TABLE "inferno_authorization_schema"."inferno_users";
DROP SEQUENCE "inferno_authorization_schema"."inferno_users_seq";
DROP TABLE "inferno_authorization_schema"."inferno_roles_assigment";
DROP SEQUENCE "inferno_authorization_schema"."inferno_user_roles_assigment_seq";
DROP TABLE "inferno_authorization_schema"."inferno_roles";
DROP SEQUENCE "inferno_authorization_schema"."inferno_roles_seq";
DROP TABLE "inferno_authorization_schema"."inferno_person";
DROP SEQUENCE "inferno_authorization_schema"."inferno_person_seq";
DROP TABLE "inferno_authorization_schema"."inferno_address";
DROP SEQUENCE "inferno_authorization_schema"."inferno_address_seq";
DROP TABLE "inferno_authorization_schema"."encryption_tes";
DROP FUNCTION "inferno_authorization_schema"."update_modified_timestamp_column"();
DROP SCHEMA "inferno_authorization_schema";
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

SET default_tablespace = '';

SET default_with_oids = true;

--
-- Name: encryption_tes; Type: TABLE; Schema: inferno_authorization_schema; Owner: postgres
--

CREATE TABLE "encryption_tes" (
    "string_token" "text"
);


ALTER TABLE "encryption_tes" OWNER TO "postgres";

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

