CREATE TABLE currency
(
	id integer NOT NULL DEFAULT nextval('currency_rate_id_seq'::regclass),
	code character(3),
	rate double precision,
	symbol character varying,
	locale character varying,
	noun character varying,
	adjective character varying,
	CONSTRAINT currency_pkey PRIMARY KEY (id),
	CONSTRAINT currency_code_currency_key UNIQUE (code)
);
