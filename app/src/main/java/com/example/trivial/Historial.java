package com.example.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

            partida=partida+fila.getString(1)+  "                                   Situacion: " + situacion;

            do{
                partida=partida+"\nJugador: "+fila.getString(0) +  "       Ranking: " + fila.getString(3);

            }while(fila.moveToNext());

            ranking.add( partida);


        }else if(!fila.moveToFirst()){
            Toast.makeText(this, "No hay registros. Comienza una Nueva Partida.",Toast.LENGTH_SHORT).show();
        }

        db.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ranking);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //llama el método cargar();
                cargar();
            }

        });



    }

    public void cargar () {

        Sqlite dbHelper = new Sqlite(this);
        db = dbHelper.getWritableDatabase();

        Intent intent = new Intent(this,JuegoActivity.class);
        String n1= "pepito";
        String n2= "pablito";
        intent.putExtra("player1", n1);
        intent.putExtra("player2", n2);
        startActivity(intent);

        /*if(db!=null){

            Cursor c = db.rawQuery("SELECT * FROM Notas ORDER BY Primero DESC LIMIT 5",null);
            int cantidad = c.getCount();
            int i=0;
            String[] arreglo = new String [cantidad];


            if(c.moveToFirst()){
                do{

                    String linea = c.getInt(0)+"                   "+ c.getInt(1)+" Clicks";

                    arreglo[i] = linea;

                    i++;
                }while(c.moveToNext());

            }

        }*/

    }
}