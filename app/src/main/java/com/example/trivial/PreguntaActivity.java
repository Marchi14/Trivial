package com.example.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
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
    boolean quesito,f;
    int nacertadas = 0;
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
        f = bundle.getBoolean("final");

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

        Pregunta(f);
    }

    private void Pregunta(boolean f){
        if (f) {
            Random rnd = new Random();
            int rng = rnd.nextInt(6);
            switch (rng) {
                case 0:
                    tema = "Historia";
                    break;
                case 1:
                    tema = "Ocio y Deporte";
                    break;
                case 2:
                    tema = "Arte y Literatura";
                    break;
                case 3:
                    tema = "Geografía";
                    break;
                case 4:
                    tema = "Entretenimiento";
                    break;
                case 5:
                    tema = "Ciencias y naturaleza";
                    break;
            }
        }
        int rng2;
        switch (tema) {
            case "Historia":
                rng2 = (int) (Math.random() * (10 - 1 + 1) + 1);
                break;
            case "Geografía":
                rng2 = (int) (Math.random() * (20 - 11 + 1) + 11);
                break;
            case "Ocio y Deporte":
                rng2 = (int) (Math.random() * (30 - 21 + 1) + 21);
                break;
            case "Ciencias y naturaleza":
                rng2 = (int) (Math.random() * (40 - 31 + 1) + 31);
                break;
            case "Arte y Literatura":
                rng2 = (int) (Math.random() * (50 - 41 + 1) + 41);
                break;
            case "Entretenimiento":
                rng2 = (int) (Math.random() * (60 - 51 + 1) + 51);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tema);
        }
        Cursor c = db.rawQuery("SELECT id_pregunta,enunciado FROM pregunta WHERE Tipo = '" + tema.toUpperCase() + "'" +
                " AND id_pregunta = " + rng2 + ";", null);

        if (c.moveToFirst()) {
            String enunciado = c.getString(1);
            pregunta.setText(enunciado);
            Respuestas(c.getInt(0));
        }
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
        time.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(this, JuegoActivity.class);
        if (boton.getText().toString().equals(resp_correcta)){
            if (f && nacertadas!=6){
                nacertadas++;
                Pregunta(f);
            }
            else if (f && nacertadas==6){
                Intent intent1 = new Intent(this, SplashFinalActivity.class);
                intent1.putExtra("Ganador",jugador1.isTurno() ? jugador1.getNombre() : jugador2.getNombre());
            }
            boton.setBackgroundColor(Color.GREEN);
            pregunta.setTextColor((Color.parseColor("#11F725")));
            pregunta.setText("CORRECTO");
            intent.putExtra("acierto",true);
        }
        else{
            boton.setBackgroundColor(Color.RED);
            pregunta.setTextColor((Color.parseColor("#F72211")));
            pregunta.setText("INCORRECTO");
            intent.putExtra("acierto",false);
        }
        setResult(RESULT_OK, intent);

        //esperar 2 segundos antes de cerrar para ver las respuestas correctas.
        new Handler().postDelayed(new Runnable(){
            public void run(){

                //----------------------------
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                finish();
                //----------------------------

            }
        }, 1000); //1000 millisegundos = 1 segundo.

    }
}