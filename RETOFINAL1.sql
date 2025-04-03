drop database futbol_americano;
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
ganador char(3),
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
insert into jugador values ("12345678Z","Jalen","Hurts","1","Quarterback","EAG"),
							("87654321M","Leroy","Jenkins","84","Running","EAG"),
                            ("34567890H","Quinyon","Mitchel","27","Guard","EAG");
insert into jugador values ("90817263T","Deebo","Samuel","69","Tackle","COM"),
							("74125896K","Jeremy","Chinn","11","Guard","COM"),
                            ("15935728Q","Haggai","Ndubuisi","68","Tackle","COM");
insert into jugador values ("2648391X","Brandin","Cooks","3","Running","COW"),
							("58741236M","Trevon","Diggs","7","Quarterback","COW"),
                            ("69874521L","Adam","Mukuamu","24","Quarterback","COW");
insert into jugador values ("3698524Y","Eric","Gray","3","Running","GIA"),
							("89012345D","Nic","Jones","70","Guard","GIA"),
                            ("78901234F","Matthew","Adams","24","Guard","GIA");
insert into jugador values ("67890123G","Shane","Buchele","6","Quarterback","BIL"),
							("99088812M","Mike","White","14","Quarterback","BIL"),
                            ("10393210Q","Larry","Ogunjobi","69","Tackle","BIL");
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

-- funciones

-- 1) Contar el total de jugadores de la base de datos

DELIMITER //
CREATE FUNCTION ContarJugadores() returns int reads sql data
begin
declare total int;
select count(dni) into total from jugador;
return total;
end //

select ContarJugadores() "Total De Jugadores";

-- 2)Contar el total de equipos de una competición

DELIMITER //
CREATE FUNCTION ContarEquiposCompeticion(nomcompe varchar(50)) returns int reads sql data
begin
declare total int;

select count(equipo_local) into total from partido where cod_comp = (select cod_comp from competicion where nombre_competicion = nomcompe);
return total;
end //

select ContarEquiposCompeticion("Nacional") Mensaje;

-- Procedimientos

-- 3)Modificar el nombre de un equipo atraves de su codigo:
DELIMITER //
CREATE PROCEDURE MODIFICAREQUIPO ( IN J_cod CHAR(3), IN J_nombre VARCHAR(50))
BEGIN
    DECLARE CUANTOS INT;
    SELECT COUNT(*) INTO CUANTOS FROM equipo WHERE cod_equi = J_cod;
    IF CUANTOS = 1 THEN
        UPDATE equipo
        SET nombre_equipo = J_nombre
        WHERE cod_equi = J_cod;
    ELSE
        SELECT CONCAT ('ERROR');
    END IF;
END //

call MODIFICAREQUIPO("COW","Real Madrid"); 

-- 4) Modificar jugador a través del dni:
DELIMITER //
CREATE PROCEDURE MODIFICARJUGADOR (
    IN J_dni CHAR(9), IN J_nombre VARCHAR(50), IN J_apellido VARCHAR(50), IN J_dorsal INT, IN J_posicion ENUM("Quarterback", "Tackle", "Running", "Guard"), IN J_cod_equi CHAR(3))
BEGIN
    DECLARE CUANTOS INT;
    SELECT COUNT(*) INTO CUANTOS FROM jugador WHERE dni = J_dni;
    IF CUANTOS = 1 THEN
        UPDATE jugador
        SET nombre = J_nombre, apellido = J_apellido, dorsal = J_dorsal, posicion = J_posicion, cod_equi = J_cod_equi
        WHERE dni = J_dni;
    ELSE
        SELECT CONCAT ('ERROR');
    END IF;
END //

CALL MODIFICARJUGADOR("29015060P","Paquito","Rice","45","Running","PAT");

-- 5) Borrar jugador a través del DNI:
CREATE PROCEDURE BORRARJUGADOR (IN p_dni CHAR(9))
BEGIN 
    DECLARE CUANTOS INT;
    SELECT COUNT(*) INTO CUANTOS FROM jugador WHERE dni = p_dni;
    IF CUANTOS = 1 THEN
                DELETE FROM jugador WHERE dni = p_dni;
    ELSE     
        SELECT CONCAT ('ERROR');
    END IF;
END // 

CALL BORRARJUGADOR("66274056Q");

-- 6)Buscar competición a través del código de la competición
DELIMITER //
CREATE PROCEDURE BUSCARCOMPETICION (IN c_cod CHAR(9))
BEGIN
    SELECT * FROM competicion WHERE cod_comp = c_cod;
END //

CALL BUSCARCOMPETICION("Nac");

-- 7)Buscar un equipo atraves del dni del jugador:
DELIMITER //
CREATE PROCEDURE BUSCAREQUIPO ( dni_jugador CHAR(9))
BEGIN
    DECLARE nom VARCHAR(50);
    SELECT nombre_equipo INTO nom FROM equipo WHERE cod_equi = (SELECT cod_equi FROM jugador WHERE dni = dni_jugador);
    SELECT CONCAT("El equipo al que pertenece el jugador con DNI: ", dni_jugador, " es: ", nom) AS Mensaje;
END //

CALL BUSCAREQUIPO("32191640N");

-- 8)Buscar jugador a través de su dni:
DELIMITER //
CREATE PROCEDURE BUSCARJUGADOR (IN nom_equi VARCHAR(50))
BEGIN
    DECLARE dni_jugador CHAR(9);
    DECLARE nom_jugador VARCHAR(50);
    DECLARE pos_jugador VARCHAR(50);
    DECLARE dorsal_jugador INT;
    DECLARE fin BOOL DEFAULT 0;
    DECLARE c CURSOR FOR 
        SELECT dni, nombre, dorsal, posicion 
        FROM jugador 
        WHERE cod_equi = (SELECT cod_equi FROM equipo WHERE nombre_equipo = nom_equi);
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET fin = 1;
    OPEN c;
    FETCH c INTO dni_jugador, nom_jugador, pos_jugador, dorsal_jugador;
    WHILE fin = 0 DO
        SELECT CONCAT('DNI: ', dni_jugador, ' Nombre: ', nom_jugador, ' Posición: ', pos_jugador, ' Dorsal: ', dorsal_jugador);
        FETCH c INTO dni_jugador, nom_jugador, pos_jugador, dorsal_jugador;
    END WHILE;
    CLOSE c;
END //

CALL BUSCARJUGADOR("Cowboys");


