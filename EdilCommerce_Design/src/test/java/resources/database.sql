CREATE TABLE IF NOT EXISTS categoria (
immagine varchar(100) not null,
nome varchar(20) not null,
descrizione varchar(50) not null,
primary key (nome));

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

CREATE TABLE IF NOT EXISTS ordine (
numeroOrdine int not null,
data date not null,
username varchar(20) not null,
importo double not null,
primary key (numeroOrdine)
);

CREATE TABLE IF NOT EXISTS infoFatturazione (
numeroOrdine int not null,
nome varchar(20) not null,
cognome varchar(20) not null,
email varchar(50) not null,
telefono varchar(20) not null,
indirizzo varchar(100) not null,
citta varchar(20) not null,
stato varchar(20) not null,
cap varchar(20) not null,
primary key (numeroOrdine)
);

CREATE TABLE IF NOT EXISTS contrassegno (
numeroOrdine int not null,
primary key (numeroOrdine));

CREATE TABLE IF NOT EXISTS carta (
numeroOrdine int  not null,
numero varchar(100) not null,
intestatario varchar(100) not null,
dataScadenza char(7) not null,
cvv varchar(10) not null,
primary key (numeroOrdine)
);

CREATE TABLE IF NOT EXISTS compone (
numeroOrdine int not null,
codiceArticolo char(5) not null,
quantita int not null,
primary key (numeroOrdine, codiceArticolo));

CREATE TABLE IF NOT EXISTS ruoloUser (
username varchar(20) not null,
nome varchar(20) not null,
primary key (username, nome));





