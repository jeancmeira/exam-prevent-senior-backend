CREATE DATABASE exam_prevent_senior
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Portuguese_Brazil.1252'
       LC_CTYPE = 'Portuguese_Brazil.1252'
       CONNECTION LIMIT = -1;


CREATE SEQUENCE public.seq_log
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.seq_log
  OWNER TO postgres;


CREATE TABLE public.log
(
  id bigint NOT NULL,
  date timestamp with time zone NOT NULL,
  ip character varying(255) NOT NULL,
  request character varying(255) NOT NULL,
  status integer NOT NULL,
  user_agent character varying(255) NOT NULL,
  CONSTRAINT log_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.log
  OWNER TO postgres;