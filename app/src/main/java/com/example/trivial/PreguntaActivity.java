package com.example.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class PreguntaActivity extends AppCompatActivity implements View.OnClickListener{

    Jugador jugador1,jugador2;
    TextView pregunta, time;
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
        time=findViewById(R.id.time);
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

        //timer
        Intent intent = new Intent(this, JuegoActivity.class);

        new CountDownTimer(21000, 1000) {

            public void onTick(long millisUntilFinished) {
                time.setText(""+ millisUntilFinished / 1000);
                if(millisUntilFinished<=11000 && millisUntilFinished>5000){
                    time.setTextColor(Color.parseColor("#F9821F"));
                }else if(millisUntilFinished<=6000){
                    time.setTextColor(Color.parseColor("#F72211"));
                }else if(millisUntilFinished==0){
                    time.setText("0");
                }
            }
            public void onFinish() {
                time.setText("0");
                time.setTextColor(Color.parseColor("#F72211"));
                intent.putExtra("acierto",false);
                setResult(RESULT_OK, intent);
                finish();
            }
        }.start();



        for (Button b : botones)
            b.setOnClickListener(this);

        //conexion base de datos
        Sqlite dbHelper = new Sqlite(this);
        db = dbHelper.getWritableDatabase();

        Pregunta(bundle.getBoolean("final"));
    }

    private void Pregunta(boolean f){
        if (f){
            for (int i = 0; i<6; i++){
                Random rnd = new Random();
                int rng = rnd.nextInt(6);
                switch (rng){
                    case 0:
                        tema = "HISTORIA";
                        break;
                    case 1:
                        tema = "OCIO Y DEPORTE";
                        break;
                    case 2:
                        tema = "ARTE Y LITERATURA";
                        break;
                    case 3:
                        tema = "GEOGRAFÍA";
                        break;
                    case 4:
                        tema = "ENTRETENIMIENTO";
                        break;
                    case 5:
                        tema = "CIENCIAS Y NATURALEZA";
                        break;
                }
                Cursor c = db.rawQuery("SELECT id_pregunta,enunciado FROM pregunta ORDER BY RANDOM() LIMIT 1" +
                        " WHERE Tipo = '" + tema + "';", null);
            }
        }
        else {
            int rng;
            switch (tema) {
                case "Historia":
                    rng = (int) (Math.random() * (6 - 1 + 1) + 1);
                    break;
                case "Geografía":
                    rng = (int) (Math.random() * (11 - 6 + 1) + 6);
                    break;
                case "Ocio y Deporte":
                    rng = (int) (Math.random() * (16 - 11 + 1) + 11);
                    break;
                case "Ciencias y naturaleza":
                    rng = (int) (Math.random() * (21 - 16 + 1) + 16);
                    break;
                case "Arte y Literatura":
                    rng = (int) (Math.random() * (26 - 21 + 1) + 21);
                    break;
                case "Entretenimiento":
                    rng = (int) (Math.random() * (31 - 26 + 1) + 26);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + tema);
            }
            Cursor c = db.rawQuery("SELECT id_pregunta,enunciado FROM pregunta WHERE Tipo = '" + tema.toUpperCase() + "'" +
                    " AND id_pregunta = " + rng + ";", null);

            if (c.moveToFirst()) {
                String enunciado = c.getString(1);
                pregunta.setText(enunciado);
                Respuestas(c.getInt(0));
            }
        }
    }

    private void Respuestas(int id) {
        Cursor c=db.rawQuery("SELECT texto,respuestaCorrecta FROM respuesta ORDER BY RANDOM() WHERE id_preg = "+id+";",null);
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