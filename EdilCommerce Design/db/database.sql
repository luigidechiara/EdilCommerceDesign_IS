DROP DATABASE IF EXISTS edil_commerce_design_is;
CREATE DATABASE edil_commerce_design_is;

DROP USER IF EXISTS 'edil_user'@'localhost';
CREATE USER 'edil_user'@'localhost'identified BY 'edil_user';
grant ALL ON edil_commerce_design_is.* to 'edil_user'@'localhost'; 

USE edil_commerce_design_is;

DROP TABLE IF EXISTS categoria;
CREATE TABLE categoria (
immagine			varchar(100)    not null,
nome				varchar(20)	    not null,
descrizione			varchar(50)		not null,
primary key (nome)
);

DROP TABLE IF EXISTS articolo;
CREATE TABLE articolo (
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

DROP TABLE IF EXISTS user;
CREATE TABLE user (
username   	    varchar(20)   	not null,
nome			varchar(20)		not null,
cognome			varchar(20)		not null,
email			varchar(20)		not null,
telefono		varchar(15)		not null,
indirizzo		varchar(20)		not null,
citta			varchar(20)		not null,
stato			varchar(20)		not null,
cap				varchar(20)		not null,
userPassword	varchar(20)		not null,
primary key (username)
);

DROP TABLE IF EXISTS ordine;
CREATE TABLE ordine (
numeroOrdine 		int auto_increment		not null,
data				date 					not null,	
username			varchar(20)				not null,
primary key (numeroOrdine),
foreign key (username) references user (username) ON UPDATE CASCADE
)    auto_increment=1;

DROP TABLE IF EXISTS pagamento;
CREATE TABLE pagamento (
numeroPagamento		int auto_increment		not null,
numeroOrdine		int						not null,
importo				double					not null,
primary key (numeroPagamento),
foreign key (numeroOrdine) references ordine (numeroOrdine)
) auto_increment=1;

DROP TABLE IF EXISTS infoFatturazione;
CREATE TABLE infoFatturazione (
numeroPagamento      int 					not null,
nome				varchar(20)				not null,
cognome				varchar(20)				not null,
email				varchar(50)				not null,
telefono			varchar(20)				not null,
indirizzo			varchar(100)			not null,
cittaÂ 			    varchar(20)				not null,
stato				varchar(20)				not null,
cap					varchar(20)				not null,
primary key (numeroPagamento),
foreign key (numeroPagamento) references pagamento (numeroPagamento)
);

DROP TABLE IF EXISTS contrassegno;
CREATE TABLE contrassegno (
numeroPagamento      int 					not null,
primary key (numeroPagamento),
foreign key (numeroPagamento) references pagamento (numeroPagamento)
);

DROP TABLE IF EXISTS carta;
CREATE TABLE carta (
numeroPagamento      int 					not null,
numero				varchar(100)			not null,
intestatario		varchar(100)			not null,
dataScadenza        char(7)					not null,
cvv					varchar(10)				not null,
primary key (numeroPagamento),
foreign key (numeroPagamento) references pagamento (numeroPagamento)
);

DROP TABLE IF EXISTS ruolo;
CREATE TABLE ruolo (
nome			varchar(20)		not null,
primary key (nome)
);

DROP TABLE IF EXISTS compone;
CREATE TABLE compone (
numeroOrdine			int		not null,
codiceArticolo    		char(5)		not null,
quantita				int			not null,
primary key (numeroOrdine, codiceArticolo),
foreign key (numeroOrdine) references ordine (numeroOrdine),
foreign key (codiceArticolo) references articolo (codiceArticolo)
);

DROP TABLE IF EXISTS recensisce;
CREATE TABLE recensisce (
codiceArticolo    		char(5)						not null,
username				varchar(20) 				not null,
data					date						not null,
valore					enum ("1","2","3","4","5")	not null,
testo					text(500)					not null,
primary key (username, codiceArticolo),
foreign key (username) references user (username) ON UPDATE CASCADE,
foreign key (codiceArticolo) references articolo (codiceArticolo)
);

DROP TABLE IF EXISTS ruoloUser;
CREATE TABLE ruoloUser (
username   	    varchar(20)   	not null,
nome			varchar(20)		not null,
primary key (username, nome),
foreign key (username) references user (username) ON UPDATE CASCADE,
foreign key (nome) references ruolo (nome)
);

DROP TABLE IF EXISTS carrello;
CREATE TABLE carrello (
username   	    	varchar(20)   	not null,
codiceArticolo    		char(6)		not null,
quantita			int				not null,
primary key (username, codiceArticolo),
foreign key (username) references user (username) ON UPDATE CASCADE,
foreign key (codiceArticolo) references articolo (codiceArticolo)
);

INSERT INTO ruolo(nome) VALUES ("user");
INSERT INTO ruolo(nome) VALUES ("admin");
INSERT INTO ruolo(nome) VALUES ("catalogo");
INSERT INTO ruolo(nome) VALUES ("magazzino");

INSERT INTO user VALUES ("admin", "nome", "cognome", "admin@email.com", "telefono", "indirizzo", "citta", "stato", "84085", "admin");
INSERT INTO user VALUES ("Gcatalogo", "nome", "cognome", "admin@email.com", "telefono", "indirizzo", "citta", "stato", "84085", "admin");

INSERT INTO ruoloUser VALUES ("admin", "user");
INSERT INTO ruoloUser VALUES ("admin", "admin");
INSERT INTO ruoloUser VALUES ("Gcatalogo", "catalogo");
INSERT INTO ruoloUser VALUES ("Gcatalogo", "user");

INSERT INTO user VALUES ("mario", "Mario", "Rossi", "marioRossi@email.com", "089788998", "via Roma", "Fisciano", "Italia", "84085", "rossi");
INSERT INTO ruoloUser VALUES ("mario", "user");

insert into categoria values
("/EdilCommerce_Design/img/categoria/arredamento_interno.jpg", "Arredamento interno", "");
insert into categoria values
("/EdilCommerce_Design/img/categoria/esterno.jpg", "Arredamento esterno", "");
insert into categoria values
("/EdilCommerce_Design/img/categoria/rivestimento.jpg", "Rivestimento", "");
insert into categoria values
("/EdilCommerce_Design/img/categoria/vernici.jpg", "Vernici", "");
insert into categoria values
("/EdilCommerce_Design/img/categoria/ferramenta.jpg", "Ferramenta", "");
insert into categoria values
("/EdilCommerce_Design/img/categoria/utensileria.jpg", "Utensileria", "");
insert into categoria values
("/EdilCommerce_Design/img/categoria/materiali.jpg", "Materiali", "");
insert into categoria values
("/EdilCommerce_Design/img/categoria/copertura.jpg", "Copertura", "");
insert into categoria values
("/EdilCommerce_Design/img/categoria/strutture.jpg", "Struttura", "");

# arredamento Interno
insert into articolo values 
("ART00", "poltrona bianca", "/EdilCommerce_Design/img/categoria/ArredamentoInterno/poltrona.jpg", "poltrona in pelle bianca, dotata di un ottimo comfort ed eleganza (90x150)", "79.99", "Arredamento interno",0,20);
insert into articolo values 
("ART01", "tavolo", "/EdilCommerce_Design/img/categoria/ArredamentoInterno/tavolo.jpg", "tavolo in stile classico in legno di faggio (100x200)", "144.90", "Arredamento interno",0,20);
insert into articolo values
("ART02", "tavolino", "/EdilCommerce_Design/img/categoria/ArredamentoInterno/tavolino.jpg", "tavolino da salotto in stile moderno ed elegante (70x90)", "99.90", "Arredamento interno",0,20);
insert into articolo values
("ART25", "divano con penisola", "/EdilCommerce_Design/img/categoria/ArredamentoInterno/divanopenisola.jpg", "Divano con penisola reversibile con pianetto L.cm 250 P.cm 160 completamente sfoderabile, poggiatesta reclinabili. Disponibile in vari tessuti.", "250.50", "Arredamento interno",0,20);
insert into articolo values
("ART26", "divano letto", "/EdilCommerce_Design/img/categoria/ArredamentoInterno/divanoletto.jpg", "Divano-letto 3 posti in tessuto grigio ardesia Duke", "200.00", "Arredamento interno",0,20);
insert into articolo values
("ART27", "cassettoni da cucina", "/EdilCommerce_Design/img/categoria/ArredamentoInterno/cassettonicucina.jpg", "Cassettoni da cucina funzionali, silenziosi e scorrevoli con guide a estrazione totale per aperture comode e chiusure rallentate e morbide, perfettamente ammortizzate.", "152.39", "Arredamento interno",0,20);
insert into articolo values
("ART28", "sedie moderne", "/EdilCommerce_Design/img/categoria/ArredamentoInterno/sediemoderne.jpg", "stile ed eleganza con il nostro nuovo set di sedie per sala da pranzo. Dotata di solida struttura in metallo", "89.90", "Arredamento interno",0,20);
insert into articolo values
("ART29", "sedie classiche", "/EdilCommerce_Design/img/categoria/ArredamentoInterno/sedieclassiche.jpg", "Questo set classico composto da 6 sedie per sala da pranzo, con dettagli in ghisa, aggiungerÃƒÂƒÃ‚ÂƒÃƒÂ‚Ã‚ÂƒÃƒÂƒÃ‚Â‚ÃƒÂ‚Ã‚Â  un tocco di eleganza all'arredamento della vostra casa! ", "95.30", "Arredamento interno",0,20);
insert into articolo values
("ART30", "lampada moderna", "/EdilCommerce_Design/img/categoria/ArredamentoInterno/lampadamoderna.jpg", "lampada a sospensione a led oro 80cm / 60cm / 40cm Anello a 3 luci cerchio oro spazzolato opaco finiture verniciate alluminio dimmerabile con telecomando", "204.95", "Arredamento interno",0,20);

#arredamento Esterno
insert into articolo values
("ART03", "sdraio", "/EdilCommerce_Design/img/categoria/ArredamentoEsterno/sdraio.jpg", "sdraio per coppie dotato di copertuta per il sole (200x190)", "339.90", "Arredamento esterno",0,20);
insert into articolo values
("ART04", "barbecue", "/EdilCommerce_Design/img/categoria/ArredamentoEsterno/barbecue.jpg", "barbecue moderno adatto a tutte le fasce di etaÂ  e facile da utlizzare", "289.99", "Arredamento esterno",0,20);
insert into articolo values
("ART31", "gazebo", "/EdilCommerce_Design/img/categoria/ArredamentoEsterno/gazebo.jpg", "Struttura in acciaio, copertura in poliestere da 180 g/m L'ampia superficie lo rende ideale per riparare dal sole un tavolo o un coffee set", "179.90", "Arredamento esterno",0,20);
insert into articolo values
("ART32", "tavolo esterno", "/EdilCommerce_Design/img/categoria/ArredamentoEsterno/tavoloesterno.jpg", "Tavolo da giardino rettangolare alluminio, 160/240X 90", "399.00", "Arredamento esterno",0,20);
insert into articolo values
("ART33", "sedie esterno", "/EdilCommerce_Design/img/categoria/ArredamentoEsterno/sedieesterno.jpg", "Comode sedie che invitano al relax. Grazie al peso ridotto possono essere trasportate con facilita con i comodissimi cuscini in poliestere", "79.00", "Arredamento esterno",0,20);
insert into articolo values
("ART34", "sdraio esterno", "/EdilCommerce_Design/img/categoria/ArredamentoEsterno/sdraioesterno.jpg", "Set 2 Poltroncine da Giardino Reclinabili con Poggiapiedi e Tavolino in PE Rattan Marrone", "599.95", "Arredamento esterno",0,20);

#Rivestimento
insert into articolo values 
("ART05", "paraschizzi da cucina", "/EdilCommerce_Design/img/categoria/Rivestimento/paraschizzi.jpg", "paraschizzi in marmo di calcare binaco con rifinture grigie", "29.90", "Rivestimento",0,20);
insert into articolo values 
("ART06", "paraschizzi per bagni", "/EdilCommerce_Design/img/categoria/Rivestimento/paraschizziBagno.jpg", "elegante paraschizzi di intarsio grigio", "24.90", "Rivestimento",0,20);
insert into articolo values
("ART37", "rivestimento in pietra naturale", "/EdilCommerce_Design/img/categoria/Rivestimento/rivestimentipietra.jpg", "Se si punta su un design ricercato, una delle idee piu affascinanti, quello della pietra naturale e un materiale che offre vantaggi relativi alla facile adattabilitaÂ  ed usabilitaÂ , ma che allo stesso tempo riesce ad essere molto elegante.", "34.90", "Rivestimento",0,20);
insert into articolo values
("ART38", "rivestimento in pietra di Stone Age", "/EdilCommerce_Design/img/categoria/Rivestimento/stoneage.jpg", "rivestimento in pietra di Stone Age 15X60 in pietra naturale quarzite bianca", "59.90", "Rivestimento",0,20);
insert into articolo values
("ART39", "Rivestimento Natstone Black", "/EdilCommerce_Design/img/categoria/Rivestimento/stoneblack.jpg", "Rivestimento Natstone Black 15X60 Pietra Naturale Ardesia Nera", "59.60", "Rivestimento",0,20);
insert into articolo values
("ART40", "Rivestimento Stone Age Taupe", "/EdilCommerce_Design/img/categoria/Rivestimento/rivnaturale.jpg", "Rivestimento Stone Age Taupe 15X60 Pietra Naturale Marmo Tortora", "69.90", "Rivestimento",0,20);

# Vernici
insert into articolo values
("ART07", "vernice verde", "/EdilCommerce_Design/img/categoria/Vernici/verde.jpg", "vernice adatta a qualsiasi tipo di superficie", "14.90", "Vernici",0,20);
insert into articolo values
("ART08", "vernice rossa", "/EdilCommerce_Design/img/categoria/Vernici/rosso.jpg", "vernice adatta a qualsiasi tipo di superficie", "14.90", "Vernici",0,20);
insert into articolo values
("ART19", "vernice gialla", "/EdilCommerce_Design/img/categoria/Vernici/giallo.jpg", "vernice adatta a qualsiasi tipo di superficie", "14.90", "Vernici",0,20);
insert into articolo values
("ART20", "vernice arancione", "/EdilCommerce_Design/img/categoria/Vernici/arancione.jpg", "vernice adatta a qualsiasi tipo di superficie", "14.90", "Vernici",0,20);
insert into articolo values
("ART21", "vernice marrone", "/EdilCommerce_Design/img/categoria/Vernici/marrone.jpg", "vernice adatta a qualsiasi tipo di superficie", "14.90", "Vernici",0,20);
insert into articolo values
("ART22", "vernice nera", "/EdilCommerce_Design/img/categoria/Vernici/nero.jpg", "vernice adatta a qualsiasi tipo di superficie", "9.90", "Vernici",0,20);
insert into articolo values
("ART23", "vernice grigia", "/EdilCommerce_Design/img/categoria/Vernici/grigio.jpg", "vernice adatta a qualsiasi tipo di superficie", "9.90", "Vernici",0,20);
insert into articolo values
("ART24", "vernice blu", "/EdilCommerce_Design/img/categoria/Vernici/blu.jpg", "vernice adatta a qualsiasi tipo di superficie", "14.90", "Vernici",0,20);

# Ferramenta
insert into articolo values
("ART09", "chiodo liscio", "/EdilCommerce_Design/img/categoria/Ferramenta/chiodoLiscio.jpg", "chiodo liscio da 9 cm con testina piatta", "20.00", "Ferramenta",0,20);
insert into articolo values
("ART10", "chiodo elicoidale", "/EdilCommerce_Design/img/categoria/Ferramenta/chiodoelicoidale.jpg", "chiodo elicoidale da 11 cm", "19.90", "Ferramenta",0,20);
insert into articolo values
("ART42", "bulloni esagonali", "/EdilCommerce_Design/img/categoria/Ferramenta/bullone.jpg", "bulloni esagonali M30", "2.90", "Ferramenta",0,20);
insert into articolo values
("ART43", "bulloni esagonali in acciao zincato 8.8", "/EdilCommerce_Design/img/categoria/Ferramenta/bullone8.jpg", "bulloni esagonali in acciao zincato 8.8", "3.90", "Ferramenta",0,20);
insert into articolo values
("ART55", "viti svasate truciolari", "/EdilCommerce_Design/img/categoria/Ferramenta/viti.jpg", "viti svasate truciolari", "4.50", "Ferramenta",0,20);
insert into articolo values
("ART44", "viti per legno", "/EdilCommerce_Design/img/categoria/Ferramenta/vitilegno.jpg", "Vite per fissaggi di Staffe, collari e supporti per tubi, Questa specifico tipo di vite e indicata per realizzare fissaggi di collari, staffe, ...", "3.50", "Ferramenta",0,20);

# Utensileria
insert into articolo values
("ART11", "trapano a percussione", "/EdilCommerce_Design/img/categoria/Utensileria/trapanopercussione.jpg", "trapano a percussione adatto a materiali piu resistenti", "136.04", "Utensileria",0,20);
insert into articolo values
("ART12", "trapano a batterie", "/EdilCommerce_Design/img/categoria/Utensileria/trapanobatteria.jpg", "trapano a batteria per fissaggi di qualunque tipo", "59.90", "Utensileria",0,20);
insert into articolo values
("ART46", "smerigliatrice", "/EdilCommerce_Design/img/categoria/Utensileria/smerigliatrice.jpg", "Smerigliatrice Angolare, 230 V, 850 W, Diametro disco 125 mm", "26.90", "Utensileria",0,20);
insert into articolo values
("ART47", "pinza per tubi", "/EdilCommerce_Design/img/categoria/Utensileria/pinzatubi.jpg", "Pinza regolabile per tubi e dadi (400 mm) 87 01 400", "50.75", "Utensileria",0,20);
insert into articolo values
("ART48", "set di chiavi", "/EdilCommerce_Design/img/categoria/Utensileria/chiavi.jpg", "Set Chiavi Fisse e Ad Anello da 10 Pezzi con Cricchetto, 8-19 mm, Acciaio al Cromo Vanadio, Borsa da Trasporto", "115.00", "Utensileria",0,20);
insert into articolo values
("ART49", "set pezzi avvitatore esagonale", "/EdilCommerce_Design/img/categoria/Utensileria/avvitatore.jpg", "10 Pezzi Adattatore Esagonale Chiavi Bussola Sostituibile Rapido in Inch1/4 Metrico 6mm - 15mm per Trapano Avvitatore Pistola Pneumatica", "16.99", "Utensileria",0,20);
insert into articolo values
("ART50", "pistola pneumatica", "/EdilCommerce_Design/img/categoria/Utensileria/pistolapneumatica.jpg", "Pistola pneumatica reversibile Beta Tools 1927P super potente", "261.70", "Utensileria",0,20);


# Materiali
insert into articolo values
("ART13", "mattoni", "/EdilCommerce_Design/img/categoria/Materiali/mattone.jpg", "mattone di dimensione 15x18", "2.99", "Materiali",0,20);
insert into articolo values
("ART14", "cemento", "/EdilCommerce_Design/img/categoria/Materiali/cemento.jpg", "cemento da 25 kg", "11.90", "Materiali",0,20);
insert into articolo values
("ART52", "calcestruzzo", "/EdilCommerce_Design/img/categoria/Materiali/calce.jpg", "La vendita di questo prodotto e possibile solo su richiesta", "12.00", "Materiali",0,20);
insert into articolo values
("ART53", "marmo", "/EdilCommerce_Design/img/categoria/Materiali/marmo.jpg", "La vendita di questo prodotto e possibile solo su richiesta", "11.50", "Materiali",0,20);
insert into articolo values
("ART54", "granito", "/EdilCommerce_Design/img/categoria/Materiali/granito.jpg", "La vendita di questo prodotto e possibile solo su richiesta", "14.90", "Materiali",0,20);

# Copertura 
insert into articolo values
("ART15", "tegole", "/EdilCommerce_Design/img/categoria/Copertura/tegola.jpg", "tegola da 12x17", "3.00", "Copertura",0,20);
insert into articolo values
("ART16", "pannelli", "/EdilCommerce_Design/img/categoria/Copertura/pannello.jpg", "panello da 15x19", "4.00", "Copertura",0,20);
insert into articolo values
("ART58", "copertura per terrazzi", "/EdilCommerce_Design/img/categoria/Copertura/coperturaterrazzo.jpg", "copertura da esterni elegante e ben raffinato in legno di betulla", "339.00", "Copertura",0,20);
insert into articolo values
("ART59", "copertura in vetro", "/EdilCommerce_Design/img/categoria/Copertura/coperturavetro.jpg", "copertura in vetro poliestere per resistere ad alte temperatura estive e rigide temperature invernali, cosi da garantire sicurezza e affidabilitaÂ ", "359.90", "Copertura",0,20);
insert into articolo values
("ART60", "tendone automatico", "/EdilCommerce_Design/img/categoria/Copertura/tendone.jpg", "tendone automatico utile per garantire una giornata all'aperto e proteggersi dal calore", "235.00", "Copertura",0,20);

#Struttura
insert into articolo values
("ART17", "puntello", "/EdilCommerce_Design/img/categoria/Struttura/puntello.jpg", "IÂ puntelli vengono usati prevalentemente per sostenere le casseforme per solai. Molti dettagli pratici ne accelerano l'impiego e ne aumentano la sicurezza: dalla forma della ghiera di regolazione, che indica il corretto verso di rotazione, alla spina di sicurezza ergonomica, a forma di G, progettata per non incepparsi. Inoltre, la zincatura a caldo garantisce una lunga durata. Nel caso in cui i puntelli vengano comunque danneggiati dalle rigide condizioni d'impiego in cantiere, il tubo esterno e quello interno possono essere sostituiti separatamente.", "17.99", "Struttura",0,20);
insert into articolo values
("ART18", "trave", "/EdilCommerce_Design/img/categoria/Struttura/trave.jpg", "La trave per casseforme ha una capacitaÂ di carico e resistenza a flessione elevata. Abbinata a casseforme per pareti, pilastri e solai oppure a casseforme speciali, permette di ridurre sia la quantitaÂ  di attrezzatura necessaria che i tempi di lavoro.", "14.90", "Struttura",0,20);

INSERT INTO recensisce VALUES ('ART03','admin',NOW() ,4,'bella e comoda');
UPDATE (select codiceArticolo, count(codiceArticolo) count from articolo NATURAL JOIN recensisce WHERE codiceArticolo = 'ART03' group by codiceArticolo) a, articolo SET mediaRecensioni = ((mediaRecensioni * (count - 1)) + 4)/count WHERE articolo.codiceArticolo = 'ART03';

INSERT INTO recensisce VALUES ('ART31','admin',NOW() ,3,'ottimi materiali');
UPDATE (select codiceArticolo, count(codiceArticolo) count from articolo NATURAL JOIN recensisce WHERE codiceArticolo = 'ART31' group by codiceArticolo) a, articolo SET mediaRecensioni = ((mediaRecensioni * (count - 1)) + 3)/count WHERE articolo.codiceArticolo = 'ART31';

INSERT INTO recensisce VALUES ('ART32','admin',NOW() ,2,'elegante');
 UPDATE (select codiceArticolo, count(codiceArticolo) count from articolo NATURAL JOIN recensisce WHERE codiceArticolo = 'ART32' group by codiceArticolo) a, articolo SET mediaRecensioni = ((mediaRecensioni * (count - 1)) + 2)/count WHERE articolo.codiceArticolo = 'ART32';
 
 INSERT INTO ordine (data, username) VALUES (NOW(),'admin');
 INSERT INTO compone VALUES (1,'ART03',1);
 INSERT INTO compone VALUES (1,'ART31',1);
 INSERT INTO compone VALUES (1,'ART10',1);
 INSERT INTO pagamento (numeroOrdine, importo) VALUES (1,539.6999999999999);
 INSERT INTO infoFatturazione VALUES (1,'nome','cognome','admin@email.com','089893889','indirizzo','citta','stato','84085');
 INSERT INTO contrassegno VALUES (1);
 INSERT INTO ordine (data, username) VALUES (NOW(),'admin');
 INSERT INTO compone VALUES (2,'ART07',1);
 INSERT INTO compone VALUES (2,'ART19',1);
 INSERT INTO compone VALUES (2,'ART21',1);
 INSERT INTO pagamento (numeroOrdine, importo) VALUES (2,44.7);
 INSERT INTO infoFatturazione VALUES (2,'nome','cognome','admin@email.com','089893889','indirizzo','citta','stato','84085');
 INSERT INTO carta VALUES (2,'1234123412341234','nome','01/2022','352');
 
INSERT INTO recensisce VALUES ('ART00','mario',NOW() ,5,'solida e resistente');
UPDATE (select codiceArticolo, count(codiceArticolo) count from articolo NATURAL JOIN recensisce WHERE codiceArticolo = 'ART00' group by codiceArticolo) a, articolo SET mediaRecensioni = ((mediaRecensioni * (count - 1)) + 5)/count WHERE articolo.codiceArticolo = 'ART00';
INSERT INTO recensisce VALUES ('ART25','mario',NOW() ,3,'adattabile\n');
UPDATE (select codiceArticolo, count(codiceArticolo) count from articolo NATURAL JOIN recensisce WHERE codiceArticolo = 'ART25' group by codiceArticolo) a, articolo SET mediaRecensioni = ((mediaRecensioni * (count - 1)) + 3)/count WHERE articolo.codiceArticolo = 'ART25';
INSERT INTO recensisce VALUES ('ART02','mario',NOW() ,2,'utile e grande');
UPDATE (select codiceArticolo, count(codiceArticolo) count from articolo NATURAL JOIN recensisce WHERE codiceArticolo = 'ART02' group by codiceArticolo) a, articolo SET mediaRecensioni = ((mediaRecensioni * (count - 1)) + 2)/count WHERE articolo.codiceArticolo = 'ART02';
INSERT INTO ordine (data, username) VALUES (NOW(),'mario');
INSERT INTO compone VALUES (3,'ART00',1);
INSERT INTO compone VALUES (3,'ART25',1);
INSERT INTO compone VALUES (3,'ART02',1);
INSERT INTO pagamento (numeroOrdine, importo) VALUES (3,430.39);
INSERT INTO infoFatturazione VALUES (3,'Mario','Rossi','marioRossi@email.com','089788998','via Roma','Fisciano','Italia','84085');
INSERT INTO contrassegno VALUES (3);

/*
insert into articolo values
("ART65", "", "/EdilCommerce_Design/img/categoria/Struttura/", "", "", "Struttura");
insert into articolo values
("ART66", "", "/EdilCommerce_Design/img/categoria/Struttura/", "", "", "Struttura");
insert into articolo values
("ART67", "", "/EdilCommerce_Design/img/categoria/Struttura/", "", "", "Struttura");
insert into articolo values
("ART68", "", "/EdilCommerce_Design/img/categoria/Struttura/", "", "", "Struttura");
insert into articolo values
("ART69", "", "/EdilCommerce_Design/img/categoria/Struttura/", "", "", "Struttura");
insert into articolo values
("ART70", "", "/EdilCommerce_Design/img/categoria/Struttura/", "", "", "Struttura");
*/