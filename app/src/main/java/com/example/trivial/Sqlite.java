package com.example.trivial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sqlite extends SQLiteOpenHelper{
    private static final String TABLE_JUGADOR_CREATE="CREATE TABLE jugador(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "player TEXT, id_partida NUMBER)";

    private static final String TABLE_PARTIDA_CREATE="CREATE TABLE partida(id_partid REFERENCES id_partida, id_jugador INTEGER REFERENCES _id, " +
            "player TEXT, casilla NUMBER, quesitoAmarillo BOOLEAN, quesitoRosa BOOLEAN," +
            "quesitoAzul BOOLEAN,quesitoMorado BOOLEAN, quesitoNaranja BOOLEAN,quesitoVerde BOOLEAN,turno BOOLEAN)";

    private static final String TABLE_HISTORIAL_CREATE="CREATE TABLE historial(id_par INTEGER REFERENCES id_partida, " +
            "id_player INTEGER REFERENCES _id, quesitos_conseguidos NUMBER)";

    private static final String TABLE_PREGUNTA_CREATE="CREATE TABLE pregunta(id_pregunta INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "color TEXT)";

    private static final String TABLE_RESPUESTA_CREATE="CREATE TABLE respuesta(id_respuesta INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "id_preg INTEGER REFERENCES id_pregunta, texto TEXT, respuestaCorrecta BOOLEAN)";

    private static final String DB_NAME="trivial.sqlite";
    private static final int DB_VERSION=1;
    public Sqlite(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_JUGADOR_CREATE);
        db.execSQL(TABLE_PARTIDA_CREATE);
        db.execSQL(TABLE_HISTORIAL_CREATE);
        db.execSQL(TABLE_PREGUNTA_CREATE);
        db.execSQL(TABLE_RESPUESTA_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
