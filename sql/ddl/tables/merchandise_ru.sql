CREATE TABLE merchandise_ru
(
	id integer NOT NULL DEFAULT nextval('merchandise_ru_id_seq'::regclass),
	name character varying(50),
	CONSTRAINT merchandise_ru_pkey PRIMARY KEY (id),
	CONSTRAINT merchandise_ru_id_fkey FOREIGN KEY (id)
		REFERENCES merchandise (id) MATCH SIMPLE
			ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT merchandise_ru_name_key UNIQUE (name)
);
