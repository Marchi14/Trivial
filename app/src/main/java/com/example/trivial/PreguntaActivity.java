package com.example.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class PreguntaActivity extends AppCompatActivity {

    TextView pregunta;
    Button res1,res2,res3,res4;
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

        //conexion base de datos
        Sqlite dbHelper = new Sqlite(this);

        Pregunta();


    }

    private void Pregunta(){
        String[] campos = new String[] {"tipo", "enunciado"};
        Cursor c=db.rawQuery("SELECT Tipo, enunciado FROM pregunta",null);

        if (c.moveToFirst()) {
                String tipo= c.getString(0);
                String enunciado = c.getString(1);
                pregunta.setText(tipo+enunciado);
        }

    }
}