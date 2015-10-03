CREATE TABLE merchandise_pl
(
	id integer NOT NULL DEFAULT nextval('merchandise_pl_id_seq'::regclass),
	name character varying(50),
	CONSTRAINT merchandise_pl_pkey PRIMARY KEY (id),
	CONSTRAINT merchandise_pl_id_fkey FOREIGN KEY (id)
		REFERENCES merchandise (id) MATCH SIMPLE
			ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT merchandise_pl_name_key UNIQUE (name)
);
