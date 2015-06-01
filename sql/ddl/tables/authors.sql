CREATE TABLE authors
(
	id integer NOT NULL DEFAULT nextval('authors_id_seq'::regclass),
	surname character varying(20) NOT NULL,
	forename character varying(10) NOT NULL,
	CONSTRAINT authors_pkey PRIMARY KEY (id)
);
