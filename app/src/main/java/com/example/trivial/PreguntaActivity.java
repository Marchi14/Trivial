package com.example.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PreguntaActivity extends AppCompatActivity {

    TextView pregunta;
    Button res1,res2,res3,res4;
    Boolean[] respuestas = new Boolean[4];
    Button[] botones = new Button[4];
    SQLiteDatabase db;
    int resp_correcta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta);

        pregunta=findViewById(R.id.TxPregunta);
        res1=findViewById(R.id.bRes1);
        res2=findViewById(R.id.bRes2);
        res3=findViewById(R.id.bRes3);
        res4=findViewById(R.id.bRes4);
        botones = new Button[]{res1, res2, res3, res4};

        //conexion base de datos
        Sqlite dbHelper = new Sqlite(this);
        db = dbHelper.getWritableDatabase();

        int pregunta=Pregunta();
        Respuestas(pregunta);

    }

    private int Pregunta(){
        Cursor c=db.rawQuery("SELECT id_pregunta,enunciado FROM pregunta",null);

        if (c.moveToFirst()) {
                String enunciado = c.getString(1);
                pregunta.setText(enunciado);
                return c.getInt(0);
        }
        return 0;
    }

    private void Respuestas(int id) {
        Cursor c=db.rawQuery("SELECT id_respuesta,texto,respuestaCorrecta FROM respuesta WHERE id_preg = "+id+";",null);
        if (c.moveToFirst()) {
            int i=0;
            do {
                    botones[i].setText(c.getString(1));
                    if(c.getInt(2)==1)
                        respuestas[c.getInt(0)-1-(4*(id-1))]=true;
                    else
                        respuestas[c.getInt(0)-1-(4*(id-1))]=false;
                    i++;
            }while(c.moveToNext());
        }
    }
}