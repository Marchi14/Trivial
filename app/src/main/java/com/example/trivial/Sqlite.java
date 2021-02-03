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
                "values('perico', 2, 3, 1, 1, 1, 0, 0, 0, 1, 1)");

        db.execSQL( "INSERT INTO jugador (player,casillaX,casillaY,quesitoAmarillo,quesitoRosa,quesitoAzul,quesitoMorado,quesitoNaranja,quesitoVerde,turno,id_partida) " +
                "values('pablito', 5, 6, 0, 0, 0, 0, 1, 1, 0, 1)");

        db.execSQL( "INSERT INTO historial (id_par, id_player, posicion) " +
                "values(1,1,1)");
        db.execSQL( "INSERT INTO historial (id_par, id_player, posicion) " +
                "values(1,2,2)");

        //PREGUNTAS
        //HISTORIA
        db.execSQL("INSERT INTO pregunta VALUES (1, 'HISTORIA', 'DE DONDE ES NAPOLEÓN?')");
        db.execSQL("INSERT INTO respuesta VALUES(1,1,'FRANCIA',1)");
        db.execSQL("INSERT INTO respuesta VALUES(2,1,'ALEMANIA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(3,1,'BELGICA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(4,1,'ITALIA',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (2, 'HISTORIA', ' ¿ Quién fue el primer presidente de la democracia española tras el franquismo?')");
        db.execSQL("INSERT INTO respuesta VALUES(5,2,'FELIPE GONZALEZ',0)");
        db.execSQL("INSERT INTO respuesta VALUES(6,2,'ADOLFO SUÁREZ',1)");
        db.execSQL("INSERT INTO respuesta VALUES(7,2,'MIKEL ICETA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(8,2,'MARIANO RAJOY',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (3, 'HISTORIA', ' ¿La invasión de qué fortaleza por parte de los revolucionarios es considerada como el punto de inicio de la Revolución Francesa?')");
        db.execSQL("INSERT INTO respuesta VALUES(9,3,'VERSALLES',0)");
        db.execSQL("INSERT INTO respuesta VALUES(10,3,'NO SE TOMO NINGUNA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(11,3,'LA BASTILLA',1)");
        db.execSQL("INSERT INTO respuesta VALUES(12,3,'EL LOUVRE',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (4, 'HISTORIA', ' ¿Quién fue el primer presidente de Estados Unidos?')");
        db.execSQL("INSERT INTO respuesta VALUES(13,4,'GEORGE WASHINGTON',1)");
        db.execSQL("INSERT INTO respuesta VALUES(14,4,'ABRAHAM LINCOLN',0)");
        db.execSQL("INSERT INTO respuesta VALUES(15,4,'BARACK OBAMA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(16,4,'RICHARD NIXON',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (5, 'HISTORIA', ' ¿Qué carabela no volvió del viaje en el que Colón arribó a América por primera vez?')");
        db.execSQL("INSERT INTO respuesta VALUES(17,5,'LA PINTA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(18,5,'LA NIÑA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(19,5,'LA SANTA MARÍA',1)");
        db.execSQL("INSERT INTO respuesta VALUES(20,5,'VOLVIERON TODAS',0)");

        //GEOGRAFÍA
        db.execSQL("INSERT INTO pregunta VALUES (6, 'GEOGRAFÍA', ' ¿Cuál es el río más caudaloso del mundo?')");
        db.execSQL("INSERT INTO respuesta VALUES(21,6,'EL NILO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(22,6,'EL AMAZONAS',1)");
        db.execSQL("INSERT INTO respuesta VALUES(23,6,'EL RÍO AMARILLO',0)");
        db.execSQL("INSERT INTO respuesta VALUES(24,6,'EL EBRO',0)");
        //
        db.execSQL("INSERT INTO pregunta VALUES (7, 'GEOGRAFÍA', '  ¿Entre qué países podemos encontrar el Estrecho de Bering?')");
        db.execSQL("INSERT INTO respuesta VALUES(25,7,'ENTRE RUSIA Y EEUU',1)");
        db.execSQL("INSERT INTO respuesta VALUES(26,7,'ENTRE CHINA Y RUSIA',0)");
        db.execSQL("INSERT INTO respuesta VALUES(27,7,'ENTRE FRANCIA Y UK',0)");
        db.execSQL("INSERT INTO respuesta VALUES(28,7,'ENTRE ESPAÑA Y MARRUECOS',0)");
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
