CREATE TABLE public.curs_data (
	id SERIAL PRIMARY KEY NOT NULL,
	currency varchar(3) NOT NULL,
	curs numeric(5,2) NOT NULL,
	curs_date date NOT NULL
);
CREATE UNIQUE INDEX curs_data_id_idx ON public.curs_data (id);
commit