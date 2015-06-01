CREATE TABLE transactions
(
	id integer NOT NULL DEFAULT nextval('transactions_id_seq'::regclass),
	client_id integer NOT NULL,
	merchandise_id integer NOT NULL,
	amount double precision NOT NULL,
	CONSTRAINT transactions_pkey PRIMARY KEY (id),
	CONSTRAINT transactions_client_id_fkey FOREIGN KEY (client_id)
		REFERENCES clients (id) MATCH SIMPLE
			ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT transactions_merchandise_id_fkey FOREIGN KEY (merchandise_id)
		REFERENCES merchandise (id) MATCH SIMPLE
			ON UPDATE NO ACTION ON DELETE NO ACTION
);
