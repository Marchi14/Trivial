package com.example.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Historial extends AppCompatActivity {

    SQLiteDatabase db;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        list=(ListView)findViewById(R.id.listview);


        //conexion base de datos
        Sqlite dbHelper = new Sqlite(this);
        db = dbHelper.getWritableDatabase();


        ArrayList<String> ranking = new ArrayList<>();

        Cursor fila = db.rawQuery("select player, id_par, id_player, posicion from jugador , historial where id_player=_id ", null);
        if(fila.moveToFirst()){
            do{
                ranking.add("ID Partida: " + fila.getString(1)+"\n-Jugador: "+fila.getString(0) +  "\n -Posición: " + fila.getString(3));
            }while(fila.moveToNext());

        }else if(!fila.moveToFirst()){
            Toast.makeText(this, "No hay registros. Comienza una Nueva Partida.",Toast.LENGTH_SHORT).show();
        }

        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ranking);
        list.setAdapter(adapter);

    }
}