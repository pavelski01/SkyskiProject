CREATE TABLE authors_books
(
	author_id integer NOT NULL,
	book_id integer NOT NULL,
	CONSTRAINT authors_books_pkey PRIMARY KEY (author_id, book_id),
	CONSTRAINT authors_books_author_id_fkey FOREIGN KEY (author_id)
		REFERENCES authors (id) MATCH SIMPLE
			ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT authors_books_book_id_fkey FOREIGN KEY (book_id)
		REFERENCES books (id) MATCH SIMPLE
			ON UPDATE NO ACTION ON DELETE NO ACTION
);
