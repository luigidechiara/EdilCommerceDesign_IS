

CREATE TABLE IF NOT EXISTS categoria (
immagine			varchar(100)    not null,
nome				varchar(20)	    not null,
descrizione			varchar(50)		not null,
primary key (nome)
);


CREATE TABLE IF NOT EXISTS articolo (
codiceArticolo		char(5)			not null,
nome				varchar(50)		not null,
immagine			varchar(100)	not null,
descrizione			varchar(1000)	not null,
costo				double			not null,
nomeCategoria		varchar(20)		not null,
mediaRecensioni		double			default 0.0,
giacenza			int				not null,
primary key (codiceArticolo),
foreign key (nomeCategoria) references categoria (nome)
);


