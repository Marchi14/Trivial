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

        String partida="ID partida: ";
        ArrayList<String> ranking = new ArrayList<>();

        Cursor fila = db.rawQuery("select player, id_par, id_player, posicion, activa from jugador , historial, partida where id_player=_id ", null);
        if(fila.moveToFirst()){

            String situacion= fila.getString(4);

            if(situacion.equals("0")){
                situacion="Terminada";
            }else if(situacion.equals("1")){
                situacion="Activa";
            }

            partida=partida+fila.getString(1)+  "    Situacion: " + situacion;

            do{
                partida=partida+"\n-Jugador: "+fila.getString(0) +  "\n -Posición: " + fila.getString(3);

            }while(fila.moveToNext());

            ranking.add(partida);

        }else if(!fila.moveToFirst()){
            Toast.makeText(this, "No hay registros. Comienza una Nueva Partida.",Toast.LENGTH_SHORT).show();
        }

        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ranking);
        list.setAdapter(adapter);

    }
}