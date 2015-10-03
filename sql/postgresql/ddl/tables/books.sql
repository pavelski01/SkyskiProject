CREATE TABLE books
(
	id integer NOT NULL DEFAULT nextval('books_id_seq'::regclass),
	title character varying(80) NOT NULL,
	volume integer,
	edition integer,
	place_of_publication character varying(10) NOT NULL,
	year_of_publication integer NOT NULL,
	CONSTRAINT books_pkey PRIMARY KEY (id)
);
