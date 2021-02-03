package com.example.trivial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlite extends SQLiteOpenHelper{
    private static final String TABLE_JUGADOR_CREATE="CREATE TABLE jugador(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "player TEXT, casillaX NUMBER, casillaY NUMBER,"+
            "quesitoAmarillo BOOLEAN, quesitoRosa BOOLEAN," +
            "quesitoAzul BOOLEAN,quesitoMorado BOOLEAN, quesitoNaranja BOOLEAN,quesitoVerde BOOLEAN, turno BOOLEAN, " +
            "id_partida INTEGER  REFERENCES id_partida)";

    private static final String TABLE_PARTIDA_CREATE="CREATE TABLE partida(id_partida INTEGER PRIMARY KEY, activa BOOLEAN)";

    private static final String TABLE_HISTORIAL_CREATE="CREATE TABLE historial(id_par INTEGER REFERENCES id_partida, " +
            "id_player INTEGER REFERENCES _id, posicion NUMBER)";

    private static final String TABLE_PREGUNTA_CREATE="CREATE TABLE pregunta(id_pregunta INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Tipo TEXT, enunciado TEXT)";

    private static final String TABLE_RESPUESTA_CREATE="CREATE TABLE respuesta(id_respuesta INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_preg INTEGER REFERENCES id_pregunta, texto TEXT, respuestaCorrecta BOOLEAN)";

    private static final String DB_NAME="trivial.sqlite";
    private static final int DB_VERSION=1;
    public Sqlite(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_PARTIDA_CREATE);
        db.execSQL(TABLE_JUGADOR_CREATE);
        db.execSQL(TABLE_HISTORIAL_CREATE);
        db.execSQL(TABLE_PREGUNTA_CREATE);
        db.execSQL(TABLE_RESPUESTA_CREATE);

        db.execSQL( "INSERT INTO partida (id_partida, activa) " +
                "values(1,0)");

        db.execSQL( "INSERT INTO jugador (player,casillaX,casillaY,quesitoAmarillo,quesitoRosa,quesitoAzul,quesitoMorado,quesitoNaranja,quesitoVerde,turno,id_partida) " +
                "values('perico', 2, 3, 1, 1, 1, 0, 0, 0, 1, 1)");

        db.execSQL( "INSERT INTO jugador (player,casillaX,casillaY,quesitoAmarillo,quesitoRosa,quesitoAzul,quesitoMorado,quesitoNaranja,quesitoVerde,turno,id_partida) " +
                "values('pablito', 5, 6, 0, 0, 0, 0, 1, 1, 0, 1)");

        db.execSQL( "INSERT INTO historial (id_par, id_player, posicion) " +
                "values(1,1,1)");
        db.execSQL( "INSERT INTO historial (id_par, id_player, posicion) " +
                "values(1,2,2)");

        //PREGUNTAS
        //HISTORIA
        db.execSQL("INSERT INTO pregunta VALUES (1, 'HISTORIA', '¿De que pais es Napoleón?')");
        db.execSQL("INSERT INTO respuesta VALUES(1,1,'FRANCIA',1)");
        db.execSQL("INSERT INTO respuesta VALUES(2,1,'ALEMANIA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(3,1,'BELGICA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(4,1,'ITALIA',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (2, 'HISTORIA', ' ¿Quién fue el primer presidente de la democracia española tras el franquismo?')");
        db.execSQL("INSERT INTO respuesta VALUES(5,2,'FELIPE GONZALEZ',0)");
        db.execSQL("INSERT INTO respuesta VALUES(6,2,'ADOLFO SUÁREZ',1)");
        db.execSQL("INSERT INTO respuesta VALUES(7,2,'MIKEL ICETA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(8,2,'MARIANO RAJOY',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (3, 'HISTORIA', '¿La invasión de qué fortaleza por parte de los revolucionarios es considerada como el punto de inicio de la Revolución Francesa?')");
        db.execSQL("INSERT INTO respuesta VALUES(9,3,'VERSALLES',0)");
        db.execSQL("INSERT INTO respuesta VALUES(10,3,'NO SE TOMO NINGUNA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(11,3,'LA BASTILLA',1)");
        db.execSQL("INSERT INTO respuesta VALUES(12,3,'EL LOUVRE',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (4, 'HISTORIA', '¿Quién fue el primer presidente de Estados Unidos?')");
        db.execSQL("INSERT INTO respuesta VALUES(13,4,'GEORGE WASHINGTON',1)");
        db.execSQL("INSERT INTO respuesta VALUES(14,4,'ABRAHAM LINCOLN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(15,4,'BARACK OBAMA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(16,4,'RICHARD NIXON',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (5, 'HISTORIA', '¿Qué carabela no volvió del viaje en el que Colón arribó a América por primera vez?')");
        db.execSQL("INSERT INTO respuesta VALUES(17,5,'LA PINTA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(18,5,'LA NIÑA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(19,5,'LA SANTA MARÍA',1)");
        db.execSQL("INSERT INTO respuesta VALUES(20,5,'VOLVIERON TODAS',0)");

        //GEOGRAFÍA
        db.execSQL("INSERT INTO pregunta VALUES (6, 'GEOGRAFÍA', '¿Cuál es el río más caudaloso del mundo?')");
        db.execSQL("INSERT INTO respuesta VALUES(21,6,'EL NILO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(22,6,'EL AMAZONAS',1)");
        db.execSQL("INSERT INTO respuesta VALUES(23,6,'EL RÍO AMARILLO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(24,6,'EL EBRO',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (7, 'GEOGRAFÍA', '¿Entre qué países podemos encontrar el Estrecho de Bering?')");
        db.execSQL("INSERT INTO respuesta VALUES(25,7,'ENTRE RUSIA Y EEUU',1)");
        db.execSQL("INSERT INTO respuesta VALUES(26,7,'ENTRE CHINA Y RUSIA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(27,7,'ENTRE FRANCIA Y UK',0)");
        db.execSQL("INSERT INTO respuesta VALUES(28,7,'ENTRE ESPAÑA Y MARRUECOS',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (8, 'GEOGRAFÍA', '¿Cuál es la lengua más hablada del mundo?')");
        db.execSQL("INSERT INTO respuesta VALUES(29,8,'CHINO CANTONES',0)");
        db.execSQL("INSERT INTO respuesta VALUES(30,8,'ESPAÑOL',0)");
        db.execSQL("INSERT INTO respuesta VALUES(31,8,'INGLES',0)");
        db.execSQL("INSERT INTO respuesta VALUES(32,8,'CHINO MANDARIN',1)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (9, 'GEOGRAFÍA', '¿Cuál es la capital de Brasil?')");
        db.execSQL("INSERT INTO respuesta VALUES(33,9,'RIO DE JANEIRO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(34,9,'BRASILIA',1)");
        db.execSQL("INSERT INTO respuesta VALUES(35,9,'SAO PAULO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(36,9,'PERIQUES',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (10, 'GEOGRAFÍA', '¿Dónde podemos encontrar la Casa Rosada?')");
        db.execSQL("INSERT INTO respuesta VALUES(37,10,'ESTADOS UNIDOS',0)");
        db.execSQL("INSERT INTO respuesta VALUES(38,10,'PERU',0)");
        db.execSQL("INSERT INTO respuesta VALUES(39,10,'PAKISTAN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(40,10,'ARGENTINA',1)");

        //OCIO Y DEPORTE
        db.execSQL("INSERT INTO pregunta VALUES (11, 'OCIO Y DEPORTE', '¿Cómo se llama el videojuego de estrategia cuyos torneos tienen un seguimiento masivo en Corea del Sur desde finales de los 90?')");
        db.execSQL("INSERT INTO respuesta VALUES(41,11,'OVERWATCH',0)");
        db.execSQL("INSERT INTO respuesta VALUES(42,11,'STARCRAFT',1)");
        db.execSQL("INSERT INTO respuesta VALUES(43,11,'STARCRAFT II',0)");
        db.execSQL("INSERT INTO respuesta VALUES(44,11,'DOTA 2',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (12, 'OCIO Y DEPORTE', '¿Quién fue Cobi?')");
        db.execSQL("INSERT INTO respuesta VALUES(45,12,'MASCOTA DE LOS JJOO DE BARCELONA',1)");
        db.execSQL("INSERT INTO respuesta VALUES(46,12,'MASCOTA DE LOS ANGELES CLIPERS',0)");
        db.execSQL("INSERT INTO respuesta VALUES(47,12,'JUGADOR DE LA LIGA ITALIANA DE VOLLEYBALL',0)");
        db.execSQL("INSERT INTO respuesta VALUES(48,12,'MASCOTA DEL MUNDIAL DE BALONMANO DE 2018',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (13, 'OCIO Y DEPORTE', '¿Qué atleta tiene el record plusmarca de velocidad en los 100 metros lisos?')");
        db.execSQL("INSERT INTO respuesta VALUES(49,13,'RICHARD THOMPSON',0)");
        db.execSQL("INSERT INTO respuesta VALUES(50,13,'TYSON GAY',0)");
        db.execSQL("INSERT INTO respuesta VALUES(51,13,'USAIN BOLT',1)");
        db.execSQL("INSERT INTO respuesta VALUES(52,13,'FLORENCE GRIFFITH',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (14, 'OCIO Y DEPORTE', '¿Cuál es el sobrenombre con el que se conoce al jugador profesional de Super Smash Bros. Jason Zimmerman?')");
        db.execSQL("INSERT INTO respuesta VALUES(53,14,'FAKER',0)");
        db.execSQL("INSERT INTO respuesta VALUES(54,14,'JUSTIN WONG',0)");
        db.execSQL("INSERT INTO respuesta VALUES(55,14,'HUNGRYBOX',0)");
        db.execSQL("INSERT INTO respuesta VALUES(56,14,'MEW2KING',1)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (15, 'OCIO Y DEPORTE', '¿Cada cuántos años se celebran los Juegos Olímpicos?')");
        db.execSQL("INSERT INTO respuesta VALUES(57,15,'4 AÑOS',0)");
        db.execSQL("INSERT INTO respuesta VALUES(58,15,'5 AÑOS',0)");
        db.execSQL("INSERT INTO respuesta VALUES(59,15,'3 AÑOS',0)");
        db.execSQL("INSERT INTO respuesta VALUES(60,15,'2 AÑOS',1)");

        //CIENCIAS Y NATURALEZA
        db.execSQL("INSERT INTO pregunta VALUES (16, 'CIENCIAS Y NATURALEZA', '¿Por qué fue famosa Marie Curie?')");
        db.execSQL("INSERT INTO respuesta VALUES(61,16,'DESCUBRIR LA PENICILINA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(62,16,'DESCUBRIR LA RADIACTIVIDAD',1)");
        db.execSQL("INSERT INTO respuesta VALUES(63,16,'INVENCION DEL TELEFONO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(64,16,'INVENCION DEL AUTOMOVIL',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (17, 'CIENCIAS Y NATURALEZA', '¿Cómo se llama la planta a partir de la cual suele ser elaborado el tequila?')");
        db.execSQL("INSERT INTO respuesta VALUES(65,17,'AGAVE',1)");
        db.execSQL("INSERT INTO respuesta VALUES(66,17,'HIERBABUENA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(67,17,'ENEBRO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(68,17,'MARGARITA',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (18, 'CIENCIAS Y NATURALEZA', '¿Cuál es el nombre técnico del miedo o fobia a las alturas?')");
        db.execSQL("INSERT INTO respuesta VALUES(69,18,'ARACNOFOBIA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(70,18,'TRIPOFOBIA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(71,18,'ACROFOBIA',1)");
        db.execSQL("INSERT INTO respuesta VALUES(72,18,'AILUROFOBIA',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (19, 'CIENCIAS Y NATURALEZA', '¿Cuál es el ave de mayor envergadura que sigue viva actualmente?')");
        db.execSQL("INSERT INTO respuesta VALUES(73,19,'AVESTRUZ',0)");
        db.execSQL("INSERT INTO respuesta VALUES(74,19,'ALBATROS',1)");
        db.execSQL("INSERT INTO respuesta VALUES(75,19,'HALCON AUSTRALIANO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(76,19,'CUERVO',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (20, 'CIENCIAS Y NATURALEZA', '¿Cuál es el principal tipo de célula que forma parte del sistema nervioso de humanos y otros animales?')");
        db.execSQL("INSERT INTO respuesta VALUES(77,20,'MACROFAGOS',0)");
        db.execSQL("INSERT INTO respuesta VALUES(78,20,'FIBROBLASTOS',0)");
        db.execSQL("INSERT INTO respuesta VALUES(79,20,'MIELINA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(80,20,'NEURONA',1)");

        //ARTE Y LITERATURA
        db.execSQL("INSERT INTO pregunta VALUES (21, 'ARTE Y LITERATURA', '¿Quién pintó el “Guernica”?')");
        db.execSQL("INSERT INTO respuesta VALUES(81,21,'PABLO PICASSO',1)");
        db.execSQL("INSERT INTO respuesta VALUES(82,21,'MURILLO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(83,21,'LUIS BUÑUEL',0)");
        db.execSQL("INSERT INTO respuesta VALUES(84,21,'JUAN DE AUSTRIA',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (22, 'ARTE Y LITERATURA', '¿Cómo se llama el libro en el que se basa la película “Blade Runner”?')");
        db.execSQL("INSERT INTO respuesta VALUES(85,22,'BLADE RUNNER',0)");
        db.execSQL("INSERT INTO respuesta VALUES(86,22,'¿SUEÑAN LOS ANDROIDES CON OVEJAS ELECTRICAS?',1)");
        db.execSQL("INSERT INTO respuesta VALUES(87,22,'DEUS EX MACHINA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(88,22,'FUTURO DEL MAÑANA',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (23, 'ARTE Y LITERATURA', '¿Quién escribió la Ilíada y la Odisea?')");
        db.execSQL("INSERT INTO respuesta VALUES(89,23,'PLATON',0)");
        db.execSQL("INSERT INTO respuesta VALUES(90,23,'SOCRATES',0)");
        db.execSQL("INSERT INTO respuesta VALUES(91,23,'ARISTOTELES',0)");
        db.execSQL("INSERT INTO respuesta VALUES(92,23,'HOMERO',1)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (24, 'ARTE Y LITERATURA', '¿De qué estilo arquitectónico es la catedral de Notre Dame?')");
        db.execSQL("INSERT INTO respuesta VALUES(93,24,'BARROCO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(94,24,'FAUVISMO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(95,24,'GOTICO',1)");
        db.execSQL("INSERT INTO respuesta VALUES(96,24,'CLASICISMO',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (25, 'ARTE Y LITERATURA', '¿Quién escribió “La colmena”?')");
        db.execSQL("INSERT INTO respuesta VALUES(97,25,'ARTURO PEREZ REVERTE',0)");
        db.execSQL("INSERT INTO respuesta VALUES(98,25,'CAMILA JOSE CELA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(99,25,'MIGUEL DELIBES',0)");
        db.execSQL("INSERT INTO respuesta VALUES(100,25,'NINGUNO DE LOS ANTERIORES',1)");

        //ENTRETENIMIENTO
        db.execSQL("INSERT INTO pregunta VALUES (26, 'ENTRETENIMIENTO', '¿A qué banda de música metal pertenece el disco Master of Puppets?')");
        db.execSQL("INSERT INTO respuesta VALUES(101,26,'IRON MAIDEN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(102,26,'METALLICA',1)");
        db.execSQL("INSERT INTO respuesta VALUES(103,26,'AC/DC',0)");
        db.execSQL("INSERT INTO respuesta VALUES(104,26,'EUROPE',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (27, 'ENTRETENIMIENTO', 'Nombre del protagonista de Darkstalkers')");
        db.execSQL("INSERT INTO respuesta VALUES(105,27,'DEMITRI',1)");
        db.execSQL("INSERT INTO respuesta VALUES(106,27,'MORRIGAN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(107,27,'FELICIA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(108,27,'ANAKIRIS',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (28, 'ENTRETENIMIENTO', '¿Cómo se llama el protagonista de la serie de animación japonesa “Cowboy Bebop”?')");
        db.execSQL("INSERT INTO respuesta VALUES(109,28,'SPIKE',1)");
        db.execSQL("INSERT INTO respuesta VALUES(110,28,'KANEKI',0)");
        db.execSQL("INSERT INTO respuesta VALUES(111,28,'EIN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(112,28,'KIRITO',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (29, 'ENTRETENIMIENTO', '¿Quién es el guionista de la novela gráfica “Watchmen”?')");
        db.execSQL("INSERT INTO respuesta VALUES(113,29,'GRANT MORRISON',0)");
        db.execSQL("INSERT INTO respuesta VALUES(114,29,'NEIL GAIMAN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(115,29,'FRANK MILLER',0)");
        db.execSQL("INSERT INTO respuesta VALUES(116,29,'ALAN MOORE',1)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (30, 'ENTRETENIMIENTO', '¿Cómo se llama la madre de Simba en la película de Disney “El Rey León”?')");
        db.execSQL("INSERT INTO respuesta VALUES(117,30,'SARAFINA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(118,30,'NALA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(119,30,'SARABI',1)");
        db.execSQL("INSERT INTO respuesta VALUES(120,30,'MULAN',0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS partida");
        db.execSQL("DROP TABLE IF EXISTS jugador");
        db.execSQL("DROP TABLE IF EXISTS historial");
        db.execSQL("DROP TABLE IF EXISTS pregunta");
        db.execSQL("DROP TABLE IF EXISTS respuesta");
        onCreate(db);
    }
}
