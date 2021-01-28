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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("INSERT INTO pregunta VALUES (1, 'HISTORIA', '#5FBFCD', 'DE DONDE ES NAPOLEÓN?')");
        db.execSQL("INSERT INTO repuesta VALUES(1,1,'FRANCIA',1)");
        db.execSQL("INSERT INTO repuesta VALUES(2,1,'ALEMANIA',1)");
        db.execSQL("INSERT INTO repuesta VALUES(3,1,'BELGICA',1)");
        db.execSQL("INSERT INTO repuesta VALUES(4,1,'ITALIA',1)");
    }
}
