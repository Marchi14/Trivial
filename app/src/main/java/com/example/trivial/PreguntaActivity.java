package com.example.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PreguntaActivity extends AppCompatActivity implements View.OnClickListener{

    Jugador jugador1,jugador2;
    TextView pregunta;
    boolean quesito;
    Button res1,res2,res3,res4;
    Button[] botones = new Button[4];
    String tema;
    String resp_correcta;
    SQLiteDatabase db;
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
        Bundle bundle = getIntent().getExtras();
        jugador1 = (Jugador) bundle.get("jugador 1");
        jugador2 = (Jugador) bundle.get("jugador 2");
        tema = bundle.getString("Tema");
        quesito = bundle.getBoolean("queso");

        for (Button b : botones)
            b.setOnClickListener(this);

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
        Cursor c=db.rawQuery("SELECT texto,respuestaCorrecta FROM respuesta WHERE id_preg = "+id+";",null);
        if (c.moveToFirst()) {
            int i=0;
            do {
                    botones[i].setText(c.getString(0));
                    if(c.getInt(1)==1)
                        resp_correcta = c.getString(0);
                    i++;
            }while(c.moveToNext());
        }
    }

    @Override
    public void onClick(View v) {
        Button boton = (Button) v;
        Intent intent = new Intent(this, JuegoActivity.class);
        if (boton.getText().toString().equals(resp_correcta)){
            boton.setBackgroundColor(Color.GREEN);
            intent.putExtra("acierto",true);
        }
        else{
            boton.setBackgroundColor(Color.RED);
            intent.putExtra("acierto",false);
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}