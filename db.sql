-- Table: accounts

-- DROP TABLE accounts;

CREATE TABLE accounts
(
  id integer NOT NULL DEFAULT nextval('accounts_seq'::regclass),
  user_id integer NOT NULL,
  account_prefix character varying(4) NOT NULL,
  account_number character varying(10) NOT NULL,
  bank_code character varying(4),
  CONSTRAINT accounts_pkey PRIMARY KEY (id),
  CONSTRAINT accounts_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT accounts_account_number_key UNIQUE (account_number)
)
WITH (
  OIDS=FALSE
);

-- Index: fki_accounts_user_id_fkey

-- DROP INDEX fki_accounts_user_id_fkey;

CREATE INDEX fki_accounts_user_id_fkey
  ON accounts
  USING btree
  (user_id);

  -- Sequence: accounts_seq

-- DROP SEQUENCE accounts_seq;

CREATE SEQUENCE accounts_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE accounts_seq
  OWNER TO postgres;

-- Table: books

-- DROP TABLE books;

CREATE TABLE books
(
  id integer NOT NULL DEFAULT nextval('books_seq'::regclass),
  user_id integer NOT NULL,
  title character varying(255),
  description text,
  CONSTRAINT books_pkey PRIMARY KEY (id),
  CONSTRAINT user_fk FOREIGN KEY (user_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (
  OIDS=FALSE
);

-- Index: fki_user_fk

-- DROP INDEX fki_user_fk;

CREATE INDEX fki_user_fk
  ON books
  USING btree
  (user_id);

-- Sequence: books_seq

-- DROP SEQUENCE books_seq;

CREATE SEQUENCE books_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE books_seq
  OWNER TO postgres;

-- Table: users

-- DROP TABLE users;

CREATE TABLE users
(
  id integer NOT NULL DEFAULT nextval('users_seq'::regclass),
  name character varying(255),
  password character varying(255),
  username character varying(255),
  CONSTRAINT users_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO postgres;

-- Sequence: users_seq

-- DROP SEQUENCE users_seq;

CREATE SEQUENCE users_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE users_seq
  OWNER TO postgres;
