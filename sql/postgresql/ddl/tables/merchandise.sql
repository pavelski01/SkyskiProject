CREATE TABLE merchandise
(
	id integer NOT NULL DEFAULT nextval('merchandise_id_seq'::regclass),
	name character varying(50),
	countable boolean,
	amount double precision,
	price double precision,
	CONSTRAINT merchandise_pkey PRIMARY KEY (id),
	CONSTRAINT merchandise_name_merchandise_key UNIQUE (name)
);
