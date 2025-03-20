-- drop database futbol_americano;
create database futbol_americano;
use futbol_americano;

create table usuario (
nom varchar (50),
contrasenia varchar (50));

create table equipo (
cod_equi char(3) primary key,
nombre_equipo varchar (50) not null);

create table jugador (
dni char(9) primary key,
nombre varchar(50) not null,
apellido varchar (50) not null,
dorsal int not null check (dorsal between 0 and 99),
posicion enum ("Quarterback", "Tackle", "Running", "Guard"),
cod_equi char(3),
foreign key(cod_equi) references equipo (cod_equi) on delete cascade on update cascade);

create table competicion (
cod_comp char(3) primary key,
nombre_competicion varchar (50));

create table partido (
cod_part int primary key auto_increment,
equipo_local char (3),
equipo_visitante char (3),
ganador char (3),
fecha datetime,
cod_comp char (3),
foreign key (equipo_local) references equipo (cod_equi) on delete cascade on update cascade,
foreign key (equipo_visitante) references equipo (cod_equi) on delete cascade on update cascade,
foreign key (cod_comp) references competicion (cod_comp) on delete cascade on update cascade);

insert into usuario values ("admin","admin"), ("standard","standard");
insert into equipo values ("EAG","Philadelphia Eagles"),("COM","Washington Commanders"),
							("COW","Dallas Cowboys"),("GIA","New York Giants"),
							("BIL","Buffalo Bills"),("DOL","Miami Dolphins"),("JET","New York Jet"),
                            ("PAT","New England Patriots");
insert into jugador values ("12345678Z","Jalen","Hurts","1","Quarterback","Eag"),
							("87654321M","Leroy","Jenkins","84","Running","Eag"),
                            ("34567890H","Quinyon","Mitchel","27","Guard","Eag");
insert into jugador values ("90817263T","Deebo","Samuel","69","Tackle","Com"),
							("74125896K","Jeremy","Chinn","11","Guard","Com"),
                            ("15935728Q","Haggai","Ndubuisi","68","Tackle","Com");
insert into jugador values ("2648391X","Brandin","Cooks","3","Running","Cow"),
							("58741236M","Trevon","Diggs","7","Quarterback","Cow"),
                            ("69874521L","Adam","Mukuamu","24","Quarterback","Cow");
insert into jugador values ("3698524Y","Eric","Gray","3","Running","GIA"),
							("89012345D","Nic","Jones","70","Guard","GIA"),
                            ("78901234F","Matthew","Adams","24","Guard","GIA");
insert into jugador values ("67890123G","Shane","Buchele","6","Quarterback","Bil"),
							("99088812M","Mike","White","14","Quarterback","Bil"),
                            ("10393210Q","Larry","Ogunjobi","69","Tackle","Bil");
insert into jugador values ("55313730H","Andrew","Meyer","60","Guard","DOL"),
							("28300703P","Isaiah","Wynn","77","Tackle","DOL"),
                            ("15185857S","Jordyn","Brooks","20","Guard","DOL");
insert into jugador values ("32191640N","John","Simpson","76","Guard","JET"),
							("66274056Q","Breece","Hall","20","Running","JET"),
                            ("21425583W","Jalen","Mills","35","Quarterback","JET");
insert into jugador values ("90337416V","Morgan","Moses","76","Tackle","PAT"),
							("13836189X","Michael","Jordan","74","Quarterback","PAT"),
                            ("29015060P","Monty","Rice","45","Running","PAT");
insert into competicion values ("Nac", "Nacional"), ("Ame","Americana");
insert into partido values (1,"EAG","COM","EAG","2025-03-19 18:00:00","NAC"), (2,"COW","GIA","COW","2025-03-19 20:00:00","NAC"), (3,"GIA","EAG","EAG","2025-03-20 12:30:00","NAC"),
(4,"COM","COW","COM","2025-03-20 15:00:00","NAC"), (5,"BIL","DOL","DOL","2025-03-20 18:00:00","AME"), (6,"JET","PAT","PAT","2025-03-20 20:00:00","AME"),
(7,"PAT","BIL","PAT","2025-03-21 12:30:00","AME"), (8,"DOL","JET","JET","2025-03-21 16:00:00","AME");

