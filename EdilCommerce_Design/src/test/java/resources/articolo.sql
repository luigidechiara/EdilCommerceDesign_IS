
CREATE TABLE IF NOT EXISTS articolo (
codiceArticolo char(5) not null,
nome varchar(50) not null,
immagine varchar(100) not null,
descrizione varchar(1000) not null,
costo double not null,
nomeCategoria varchar(20) not null,
mediaRecensioni double not null,
giacenza int not null,
primary key (codiceArticolo));