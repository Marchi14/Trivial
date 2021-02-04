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
        db.execSQL("DROP TABLE IF EXISTS partida");
        db.execSQL("DROP TABLE IF EXISTS jugador");
        db.execSQL("DROP TABLE IF EXISTS historial");
        db.execSQL("DROP TABLE IF EXISTS pregunta");
        db.execSQL("DROP TABLE IF EXISTS respuesta");
        db.execSQL(TABLE_PARTIDA_CREATE);
        db.execSQL(TABLE_JUGADOR_CREATE);
        db.execSQL(TABLE_HISTORIAL_CREATE);
        db.execSQL(TABLE_PREGUNTA_CREATE);
        db.execSQL(TABLE_RESPUESTA_CREATE);

        db.execSQL( "INSERT INTO partida (id_partida, activa) " +
                "values(1,0)");

        db.execSQL( "INSERT INTO jugador (player,casillaX,casillaY,quesitoAmarillo,quesitoRosa,quesitoAzul,quesitoMorado,quesitoNaranja,quesitoVerde,turno,id_partida) " +
                "values('perico', 2, 3, 0, 1, 1, 1, 1, 1, 1, 1)");

        db.execSQL( "INSERT INTO jugador (player,casillaX,casillaY,quesitoAmarillo,quesitoRosa,quesitoAzul,quesitoMorado,quesitoNaranja,quesitoVerde,turno,id_partida) " +
                "values('pablito', 5, 6, 1, 1, 1, 0, 1, 1, 0, 1)");

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
        //
        db.execSQL("INSERT INTO pregunta VALUES (6, 'HISTORIA', '¿Cómo se apellidaban los dos exploradores que dieron la primera vuelta al mundo?')");
        db.execSQL("INSERT INTO respuesta VALUES(21,6,'COLÓN-CORTÉS',0)");
        db.execSQL("INSERT INTO respuesta VALUES(22,6,'MAGALLANES-ELCANO',1)");
        db.execSQL("INSERT INTO respuesta VALUES(23,6,'GUCCI-PTLOMEO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(24,6,'GARCÍA-JIMENEZ',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (7, 'HISTORIA', '¿Qué filósofo de la Antigua Grecia creía que el elemento del que están compuestas todas las cosas es el agua?')");
        db.execSQL("INSERT INTO respuesta VALUES(25,7,'PLATÓN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(26,7,'ARISTÓTELES',0)");
        db.execSQL("INSERT INTO respuesta VALUES(27,7,'SÓCRATES',0)");
        db.execSQL("INSERT INTO respuesta VALUES(28,7,'TALES DE MILETO',1)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (8, 'HISTORIA', '¿Quién era el gran ministro británico cuando la India Británica fue sacudida por la hambruna de Bengala?')");
        db.execSQL("INSERT INTO respuesta VALUES(29,8,'WINSTON CHURCHILL',1)");
        db.execSQL("INSERT INTO respuesta VALUES(30,8,'ISAAC NEWTON',0)");
        db.execSQL("INSERT INTO respuesta VALUES(31,8,'OLIVER CROMWELL',0)");
        db.execSQL("INSERT INTO respuesta VALUES(32,8,'HORATIO NELSON',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (9, 'HISTORIA', '¿Para qué religión es especialmente importante el rey Haile Selassie I?')");
        db.execSQL("INSERT INTO respuesta VALUES(33,9,'CRISTIANISMO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(34,9,'CATOLICISMO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(35,9,'RASTAFARI',1)");
        db.execSQL("INSERT INTO respuesta VALUES(36,9,'MUSULMANA',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (10, 'HISTORIA', '¿Qué emperador romano es conocido entre otras cosas por haber intentado someterse a operaciones de cambio de sexo?')");
        db.execSQL("INSERT INTO respuesta VALUES(37,10,'MARCO URELIO AUGUSTO',1)");
        db.execSQL("INSERT INTO respuesta VALUES(38,10,'JULIO CÉSAR',0)");
        db.execSQL("INSERT INTO respuesta VALUES(39,10,'NERÓN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(40,10,'TRAJANO',0)");
        //

        //GEOGRAFÍA
        db.execSQL("INSERT INTO pregunta VALUES (11, 'GEOGRAFÍA', '¿Cuál es el río más caudaloso del mundo?')");
        db.execSQL("INSERT INTO respuesta VALUES(41,11,'EL NILO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(42,11,'EL AMAZONAS',1)");
        db.execSQL("INSERT INTO respuesta VALUES(43,11,'EL RÍO AMARILLO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(44,11,'EL EBRO',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (12, 'GEOGRAFÍA', '¿Entre qué países podemos encontrar el Estrecho de Bering?')");
        db.execSQL("INSERT INTO respuesta VALUES(45,12,'ENTRE RUSIA Y EEUU',1)");
        db.execSQL("INSERT INTO respuesta VALUES(46,12,'ENTRE CHINA Y RUSIA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(47,12,'ENTRE FRANCIA Y UK',0)");
        db.execSQL("INSERT INTO respuesta VALUES(48,12,'ENTRE ESPAÑA Y MARRUECOS',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (13, 'GEOGRAFÍA', '¿Cuál es la lengua más hablada del mundo?')");
        db.execSQL("INSERT INTO respuesta VALUES(49,13,'CHINO CANTONES',0)");
        db.execSQL("INSERT INTO respuesta VALUES(50,13,'ESPAÑOL',0)");
        db.execSQL("INSERT INTO respuesta VALUES(51,13,'INGLES',0)");
        db.execSQL("INSERT INTO respuesta VALUES(52,13,'CHINO MANDARIN',1)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (14, 'GEOGRAFÍA', '¿Cuál es la capital de Brasil?')");
        db.execSQL("INSERT INTO respuesta VALUES(53,14,'RIO DE JANEIRO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(54,14,'BRASILIA',1)");
        db.execSQL("INSERT INTO respuesta VALUES(55,14,'SAO PAULO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(56,14,'PERIQUES',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (15, 'GEOGRAFÍA', '¿Dónde podemos encontrar la Casa Rosada?')");
        db.execSQL("INSERT INTO respuesta VALUES(57,15,'ESTADOS UNIDOS',0)");
        db.execSQL("INSERT INTO respuesta VALUES(58,15,'PERU',0)");
        db.execSQL("INSERT INTO respuesta VALUES(59,15,'PAKISTAN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(60,15,'ARGENTINA',1)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (16, 'GEOGRAFÍA', '¿Cómo se llama la línea vertical imaginaria a partir del cual se miden las longitudes y que divide el mundo en dos mitades?')");
        db.execSQL("INSERT INTO respuesta VALUES(61,16,'MERIDIANO DE GREENWICH',1)");
        db.execSQL("INSERT INTO respuesta VALUES(62,16,'MERIDIANO DE MADRID',0)");
        db.execSQL("INSERT INTO respuesta VALUES(63,16,'MERIDIANO DE ROMA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(64,16,'MERIDIANO DE PARÍS',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (17, 'GEOGRAFÍA', '¿Cuál es el país de mayor tamaño del mundo?')");
        db.execSQL("INSERT INTO respuesta VALUES(65,17,'CHINA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(66,17,'EEUU',0)");
        db.execSQL("INSERT INTO respuesta VALUES(67,17,'RUSIA',1)");
        db.execSQL("INSERT INTO respuesta VALUES(68,17,'BRASIL',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (18, 'GEOGRAFÍA', '¿Cuál es la capital de Nueva Zelanda?')");
        db.execSQL("INSERT INTO respuesta VALUES(69,18,'MELBOURNE',0)");
        db.execSQL("INSERT INTO respuesta VALUES(70,18,'AUCKLAND',1)");
        db.execSQL("INSERT INTO respuesta VALUES(71,18,'SIDNEY',0)");
        db.execSQL("INSERT INTO respuesta VALUES(72,18,'HON-KONG',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (19, 'GEOGRAFÍA', ' ¿Qué cordillera separa Europa de Asia?')");
        db.execSQL("INSERT INTO respuesta VALUES(73,19,'MONTES URALES',1)");
        db.execSQL("INSERT INTO respuesta VALUES(74,19,'LOS PIRINEOS',0)");
        db.execSQL("INSERT INTO respuesta VALUES(75,19,'LOS ALPES',0)");
        db.execSQL("INSERT INTO respuesta VALUES(76,19,'EL HIMALAYA',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (20, 'GEOGRAFÍA', ' ¿Cuál es la capital de Letonia?')");
        db.execSQL("INSERT INTO respuesta VALUES(77,20,'JELGAVA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(78,20,'RIGA',1)");
        db.execSQL("INSERT INTO respuesta VALUES(79,20,'VALMIERA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(80,20,'VENTSPILS',0)");
        //


        //OCIO Y DEPORTE
        db.execSQL("INSERT INTO pregunta VALUES (21, 'OCIO Y DEPORTE', '¿Cómo se llama el videojuego de estrategia cuyos torneos tienen un seguimiento masivo en Corea del Sur desde finales de los 90?')");
        db.execSQL("INSERT INTO respuesta VALUES(81,21,'OVERWATCH',0)");
        db.execSQL("INSERT INTO respuesta VALUES(82,21,'STARCRAFT',1)");
        db.execSQL("INSERT INTO respuesta VALUES(83,21,'STARCRAFT II',0)");
        db.execSQL("INSERT INTO respuesta VALUES(84,21,'DOTA 2',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (22, 'OCIO Y DEPORTE', '¿Quién fue Cobi?')");
        db.execSQL("INSERT INTO respuesta VALUES(85,22,'MASCOTA DE LOS JJOO DE BARCELONA',1)");
        db.execSQL("INSERT INTO respuesta VALUES(86,22,'MASCOTA DE LOS ANGELES CLIPERS',0)");
        db.execSQL("INSERT INTO respuesta VALUES(87,22,'JUGADOR DE LA LIGA ITALIANA DE VOLLEYBALL',0)");
        db.execSQL("INSERT INTO respuesta VALUES(88,22,'MASCOTA DEL MUNDIAL DE BALONMANO DE 2018',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (23, 'OCIO Y DEPORTE', '¿Qué atleta tiene el record plusmarca de velocidad en los 100 metros lisos?')");
        db.execSQL("INSERT INTO respuesta VALUES(89,23,'RICHARD THOMPSON',0)");
        db.execSQL("INSERT INTO respuesta VALUES(90,23,'TYSON GAY',0)");
        db.execSQL("INSERT INTO respuesta VALUES(91,23,'USAIN BOLT',1)");
        db.execSQL("INSERT INTO respuesta VALUES(92,23,'FLORENCE GRIFFITH',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (24, 'OCIO Y DEPORTE', '¿Cuál es el sobrenombre con el que se conoce al jugador profesional de Super Smash Bros. Jason Zimmerman?')");
        db.execSQL("INSERT INTO respuesta VALUES(93,24,'FAKER',0)");
        db.execSQL("INSERT INTO respuesta VALUES(94,24,'JUSTIN WONG',0)");
        db.execSQL("INSERT INTO respuesta VALUES(95,24,'HUNGRYBOX',0)");
        db.execSQL("INSERT INTO respuesta VALUES(96,24,'MEW2KING',1)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (25, 'OCIO Y DEPORTE', '¿Cada cuántos años se celebran los Juegos Olímpicos?')");
        db.execSQL("INSERT INTO respuesta VALUES(97,25,'4 AÑOS',0)");
        db.execSQL("INSERT INTO respuesta VALUES(98,25,'5 AÑOS',0)");
        db.execSQL("INSERT INTO respuesta VALUES(99,25,'3 AÑOS',0)");
        db.execSQL("INSERT INTO respuesta VALUES(100,25,'2 AÑOS',1)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (26, 'OCIO Y DEPORTE', '¿Quién es considerado el mejor jugador de baloncesto de todos los tiempos?')");
        db.execSQL("INSERT INTO respuesta VALUES(101,26,'LARRY BIRD',0)");
        db.execSQL("INSERT INTO respuesta VALUES(102,26,'MAGIC JHONSON',0)");
        db.execSQL("INSERT INTO respuesta VALUES(103,26,'MICHAEL JORDAN',1)");
        db.execSQL("INSERT INTO respuesta VALUES(104,26,'LEBRON JAMES',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (27, 'OCIO Y DEPORTE', ' ¿Qué equipo de fútbol ha ganado más Mundiales hasta el momento?')");
        db.execSQL("INSERT INTO respuesta VALUES(105,27,'BRASIL',1)");
        db.execSQL("INSERT INTO respuesta VALUES(106,27,'ARGENTINA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(107,27,'ALEMANIA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(108,27,'ITALIA',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (28, 'OCIO Y DEPORTE', ' ¿Qué ajedrecista cubano fue campeón mundial de esta disciplina entre 1921 y 1927?')");
        db.execSQL("INSERT INTO respuesta VALUES(109,28,'KASPAROV',0)");
        db.execSQL("INSERT INTO respuesta VALUES(110,28,'RAÚL CAPABLANCA',1)");
        db.execSQL("INSERT INTO respuesta VALUES(111,28,'SPASKI',0)");
        db.execSQL("INSERT INTO respuesta VALUES(112,28,'ALEKHINE',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (29, 'OCIO Y DEPORTE', ' ¿En qué ciudad española está el estadio de fútbol de Mestalla?')");
        db.execSQL("INSERT INTO respuesta VALUES(113,29,'MADRID',0)");
        db.execSQL("INSERT INTO respuesta VALUES(114,29,'VALLADOLID',0)");
        db.execSQL("INSERT INTO respuesta VALUES(115,29,'VALENCIA',1)");
        db.execSQL("INSERT INTO respuesta VALUES(116,29,'MÁLAGA',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (30, 'OCIO Y DEPORTE', ' ¿Qué deporte jugado con un bate y una pelota es el más popular en la India?')");
        db.execSQL("INSERT INTO respuesta VALUES(117,30,'BASEBALL',0)");
        db.execSQL("INSERT INTO respuesta VALUES(118,30,'POLO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(119,30,'HOCKEY',0)");
        db.execSQL("INSERT INTO respuesta VALUES(120,31,'CRÍQUET',1)");
        //

        //CIENCIAS Y NATURALEZA
        db.execSQL("INSERT INTO pregunta VALUES (31, 'CIENCIAS Y NATURALEZA', '¿Por qué fue famosa Marie Curie?')");
        db.execSQL("INSERT INTO respuesta VALUES(121,31,'DESCUBRIR LA PENICILINA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(122,31,'DESCUBRIR LA RADIACTIVIDAD',1)");
        db.execSQL("INSERT INTO respuesta VALUES(123,31,'INVENCION DEL TELEFONO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(124,31,'INVENCION DEL AUTOMOVIL',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (32, 'CIENCIAS Y NATURALEZA', '¿Cómo se llama la planta a partir de la cual suele ser elaborado el tequila?')");
        db.execSQL("INSERT INTO respuesta VALUES(125,32,'AGAVE',1)");
        db.execSQL("INSERT INTO respuesta VALUES(126,32,'HIERBABUENA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(127,32,'ENEBRO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(128,32,'MARGARITA',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (33, 'CIENCIAS Y NATURALEZA', '¿Cuál es el nombre técnico del miedo o fobia a las alturas?')");
        db.execSQL("INSERT INTO respuesta VALUES(129,33,'ARACNOFOBIA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(130,33,'TRIPOFOBIA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(131,33,'ACROFOBIA',1)");
        db.execSQL("INSERT INTO respuesta VALUES(132,33,'AILUROFOBIA',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (34, 'CIENCIAS Y NATURALEZA', '¿Cuál es el ave de mayor envergadura que sigue viva actualmente?')");
        db.execSQL("INSERT INTO respuesta VALUES(133,34,'AVESTRUZ',0)");
        db.execSQL("INSERT INTO respuesta VALUES(134,34,'ALBATROS',1)");
        db.execSQL("INSERT INTO respuesta VALUES(135,34,'HALCON AUSTRALIANO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(136,34,'CUERVO',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (35, 'CIENCIAS Y NATURALEZA', '¿Cuál es el principal tipo de célula que forma parte del sistema nervioso de humanos y otros animales?')");
        db.execSQL("INSERT INTO respuesta VALUES(137,35,'MACROFAGOS',0)");
        db.execSQL("INSERT INTO respuesta VALUES(138,35,'FIBROBLASTOS',0)");
        db.execSQL("INSERT INTO respuesta VALUES(139,35,'MIELINA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(140,35,'NEURONA',1)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (36, 'CIENCIAS Y NATURALEZA', '¿Qué gas nos protege de la radiación solar, concretamente de la radiación ultravioleta, formando una capa en la atmósfera?')");
        db.execSQL("INSERT INTO respuesta VALUES(141,36,'OXIGENO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(142,36,'OZONO',1)");
        db.execSQL("INSERT INTO respuesta VALUES(143,36,'NITRÓGENO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(144,36,'HIDRÓGENO',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (37, 'CIENCIAS Y NATURALEZA', '¿Cómo se llama el ave rapaz que se alimenta fundamentalmente de huesos?')");
        db.execSQL("INSERT INTO respuesta VALUES(145,37,'QUEBRANTAHUESOS',1)");
        db.execSQL("INSERT INTO respuesta VALUES(146,37,'ÁGUILA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(147,37,'ALCÓN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(148,37,'CERNÍCALO',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (38, 'CIENCIAS Y NATURALEZA', '¿Qué especie de anfibio es conocida por su increíble capacidad para regenerar partes de su cuerpo que han sido dañadas o amputadas?')");
        db.execSQL("INSERT INTO respuesta VALUES(149,38,'SALAMANDRA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(150,38,'LAGARTIJA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(151,38,'AJOLOTE',1)");
        db.execSQL("INSERT INTO respuesta VALUES(152,38,'RANA',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (39, 'CIENCIAS Y NATURALEZA', '¿Alrededor de qué planeta orbitan los satélites Ganímedes, Calisto, Ío y Europa?')");
        db.execSQL("INSERT INTO respuesta VALUES(153,39,'SATURNO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(154,39,'JÚPITER',1)");
        db.execSQL("INSERT INTO respuesta VALUES(155,39,'URANO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(156,39,'MARTE',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (40, 'CIENCIAS Y NATURALEZA', '¿Qué período de la era paleozoica tuvo lugar entre el Devónico y el Pérmico?')");
        db.execSQL("INSERT INTO respuesta VALUES(157,40,'CARBONÍFERO',1)");
        db.execSQL("INSERT INTO respuesta VALUES(158,40,'MESOZOICO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(159,40,'SILÚRICO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(160,40,'ORDOVÍDICO',0)");
        //

        //ARTE Y LITERATURA
        db.execSQL("INSERT INTO pregunta VALUES (41, 'ARTE Y LITERATURA', '¿Quién pintó el “Guernica”?')");
        db.execSQL("INSERT INTO respuesta VALUES(161,41,'PABLO PICASSO',1)");
        db.execSQL("INSERT INTO respuesta VALUES(162,41,'MURILLO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(163,41,'LUIS BUÑUEL',0)");
        db.execSQL("INSERT INTO respuesta VALUES(164,41,'JUAN DE AUSTRIA',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (42, 'ARTE Y LITERATURA', '¿Cómo se llama el libro en el que se basa la película “Blade Runner”?')");
        db.execSQL("INSERT INTO respuesta VALUES(165,42,'BLADE RUNNER',0)");
        db.execSQL("INSERT INTO respuesta VALUES(166,42,'¿SUEÑAN LOS ANDROIDES CON OVEJAS ELECTRICAS?',1)");
        db.execSQL("INSERT INTO respuesta VALUES(167,42,'DEUS EX MACHINA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(168,42,'FUTURO DEL MAÑANA',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (43, 'ARTE Y LITERATURA', '¿Quién escribió la Ilíada y la Odisea?')");
        db.execSQL("INSERT INTO respuesta VALUES(169,43,'PLATON',0)");
        db.execSQL("INSERT INTO respuesta VALUES(170,43,'SOCRATES',0)");
        db.execSQL("INSERT INTO respuesta VALUES(171,43,'ARISTOTELES',0)");
        db.execSQL("INSERT INTO respuesta VALUES(172,43,'HOMERO',1)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (44, 'ARTE Y LITERATURA', '¿De qué estilo arquitectónico es la catedral de Notre Dame?')");
        db.execSQL("INSERT INTO respuesta VALUES(173,44,'BARROCO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(174,44,'FAUVISMO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(175,44,'GOTICO',1)");
        db.execSQL("INSERT INTO respuesta VALUES(176,44,'CLASICISMO',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (45, 'ARTE Y LITERATURA', '¿Quién escribió “La colmena”?')");
        db.execSQL("INSERT INTO respuesta VALUES(177,45,'ARTURO PEREZ REVERTE',0)");
        db.execSQL("INSERT INTO respuesta VALUES(178,45,'CAMILA JOSE CELA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(179,45,'MIGUEL DELIBES',0)");
        db.execSQL("INSERT INTO respuesta VALUES(180,45,'NINGUNO DE LOS ANTERIORES',1)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (46, 'ARTE Y LITERATURA', '¿Qué gran artista es conocido por haber pintado la Capilla Sixtina?')");
        db.execSQL("INSERT INTO respuesta VALUES(181,46,'LEONARDO DA VINCI',0)");
        db.execSQL("INSERT INTO respuesta VALUES(182,46,'MIGUEL ÁNGEL',1)");
        db.execSQL("INSERT INTO respuesta VALUES(183,46,'EL BOSCO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(184,46,'GOYA',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (47, 'ARTE Y LITERATURA', '¿De qué obra de Shakespeare forma parte el soliloquio “Ser o no ser, esa es la cuestión”?')");
        db.execSQL("INSERT INTO respuesta VALUES(185,47,'ROMEO Y JULIETA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(186,47,'EL QUIJOTE',0)");
        db.execSQL("INSERT INTO respuesta VALUES(187,47,'HAMLET',1)");
        db.execSQL("INSERT INTO respuesta VALUES(188,47,'OTELO',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (48, 'ARTE Y LITERATURA', '¿Qué nombre tenía el caballo de Don Quijote de la Mancha?')");
        db.execSQL("INSERT INTO respuesta VALUES(189,48,'SARDINILLA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(190,48,'GALÁN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(191,48,'IMPETUOSO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(192,48,'ROCINANTE',1)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (49, 'ARTE Y LITERATURA', '¿En qué país fue considerado Gulliver un gigante durante sus viajes?')");
        db.execSQL("INSERT INTO respuesta VALUES(193,49,'LILIPUT',1)");
        db.execSQL("INSERT INTO respuesta VALUES(194,49,'LA TIERRA MEDIA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(195,49,'ARNELIA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(196,49,'DERSIRÁN',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (50, 'ARTE Y LITERATURA', '¿Qué escritor hispanoparlante recibió el apodo de “el manco de Lepanto”?')");
        db.execSQL("INSERT INTO respuesta VALUES(197,50,'MIGUEL DELIBES',0)");
        db.execSQL("INSERT INTO respuesta VALUES(198,50,'A.P.REVERTE',0)");
        db.execSQL("INSERT INTO respuesta VALUES(199,50,'MIGUEL DE CERVANTES',1)");
        db.execSQL("INSERT INTO respuesta VALUES(200,50,'LOPE DE VEGA',0)");
        //

        //ENTRETENIMIENTO
        db.execSQL("INSERT INTO pregunta VALUES (51, 'ENTRETENIMIENTO', '¿A qué banda de música metal pertenece el disco Master of Puppets?')");
        db.execSQL("INSERT INTO respuesta VALUES(201,51,'IRON MAIDEN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(202,51,'METALLICA',1)");
        db.execSQL("INSERT INTO respuesta VALUES(203,51,'AC/DC',0)");
        db.execSQL("INSERT INTO respuesta VALUES(204,51,'EUROPE',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (52, 'ENTRETENIMIENTO', 'Nombre del protagonista de Darkstalkers')");
        db.execSQL("INSERT INTO respuesta VALUES(205,52,'DEMITRI',1)");
        db.execSQL("INSERT INTO respuesta VALUES(206,52,'MORRIGAN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(207,52,'FELICIA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(208,52,'ANAKIRIS',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (53, 'ENTRETENIMIENTO', '¿Cómo se llama el protagonista de la serie de animación japonesa “Cowboy Bebop”?')");
        db.execSQL("INSERT INTO respuesta VALUES(209,53,'SPIKE',1)");
        db.execSQL("INSERT INTO respuesta VALUES(210,53,'KANEKI',0)");
        db.execSQL("INSERT INTO respuesta VALUES(211,53,'EIN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(212,53,'KIRITO',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (54, 'ENTRETENIMIENTO', '¿Quién es el guionista de la novela gráfica “Watchmen”?')");
        db.execSQL("INSERT INTO respuesta VALUES(213,54,'GRANT MORRISON',0)");
        db.execSQL("INSERT INTO respuesta VALUES(214,54,'NEIL GAIMAN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(215,54,'FRANK MILLER',0)");
        db.execSQL("INSERT INTO respuesta VALUES(216,54,'ALAN MOORE',1)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (55, 'ENTRETENIMIENTO', '¿Cómo se llama la madre de Simba en la película de Disney “El Rey León”?')");
        db.execSQL("INSERT INTO respuesta VALUES(217,55,'SARAFINA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(218,55,'NALA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(219,55,'SARABI',1)");
        db.execSQL("INSERT INTO respuesta VALUES(220,55,'MULAN',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (56, 'ENTRETENIMIENTO', '¿A quién interpretaba John Travolta en “Grease”?')");
        db.execSQL("INSERT INTO respuesta VALUES(221,56,'DANNY ZUKO',1)");
        db.execSQL("INSERT INTO respuesta VALUES(222,56,'STEVE JHONSON',0)");
        db.execSQL("INSERT INTO respuesta VALUES(223,56,'MCENROY',0)");
        db.execSQL("INSERT INTO respuesta VALUES(224,56,'ROY KANE',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (57, 'ENTRETENIMIENTO', ' ¿Quién fue el famoso cantante del grupo musical Queen?')");
        db.execSQL("INSERT INTO respuesta VALUES(225,57,'KURT COBAIN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(226,57,'ELTON JOHN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(227,57,'FREDDIE MERCURY',1)");
        db.execSQL("INSERT INTO respuesta VALUES(228,57,'STING',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (58, 'ENTRETENIMIENTO', ' ¿De qué grupo es la canción “Smells like a teen spirit”?')");
        db.execSQL("INSERT INTO respuesta VALUES(229,58,'QUEEN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(230,58,'IRON MAIDEN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(231,58,'METALLICA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(232,58,'NIRVANA',1)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (59, 'ENTRETENIMIENTO', ' ¿De qué grupo es la canción “Smells like a teen spirit”?')");
        db.execSQL("INSERT INTO respuesta VALUES(233,59,'QUEEN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(234,59,'IRON MAIDEN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(235,59,'METALLICA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(236,59,'NIRVANA',1)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (60, 'ENTRETENIMIENTO', '  ¿A qué saga de películas pertenece el personaje conocido como Jack Sparrow?')");
        db.execSQL("INSERT INTO respuesta VALUES(237,60,'PIRATAS DEL CARIBE',1)");
        db.execSQL("INSERT INTO respuesta VALUES(238,60,'HARRY POTTER',0)");
        db.execSQL("INSERT INTO respuesta VALUES(239,60,'EL SEÑOR DE LOS ANILLOS',0)");
        db.execSQL("INSERT INTO respuesta VALUES(240,60,'LOS JUEGOS DEL HAMBRE',0)");
        //
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
