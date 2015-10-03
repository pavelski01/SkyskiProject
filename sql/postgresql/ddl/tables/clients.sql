CREATE TABLE clients
(
	id integer NOT NULL DEFAULT nextval('clients_id_seq'::regclass),
	login character varying(20) NOT NULL,
	passwd character varying(20) NOT NULL,
	email character varying(20) NOT NULL,
	forename character varying(20),
	surname character varying(30),
	street character varying(20),
	postal_code character varying(10),
	city character varying(20),
	date_of_birth date,
	confirmed boolean,
	phone character varying(20),
	CONSTRAINT clients_pkey PRIMARY KEY (id),
	CONSTRAINT clients_email_clients_key UNIQUE (email),
	CONSTRAINT clients_login_clients_key UNIQUE (login)
);
