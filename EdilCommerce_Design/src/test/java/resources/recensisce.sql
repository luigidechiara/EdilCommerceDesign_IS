CREATE TABLE IF NOT EXISTS recensisce(
codiceArticolo char(5) not null,
username varchar(20) not null,
data date,
valore int,
testo text(500),
primary key (username, codiceArticolo));