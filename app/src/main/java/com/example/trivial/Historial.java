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

        int idPartida=0;


        //conexion base de datos
        Sqlite dbHelper = new Sqlite(this);
        db = dbHelper.getWritableDatabase();

        String partida="ID partida: ";
        ArrayList<String> ranking = new ArrayList<>();

        Cursor fila = db.rawQuery("select player, id_par, id_player, posicion, activa from jugador , historial, partida where id_player=_id ", null);
        if(fila.moveToFirst()){

            idPartida= Integer.parseInt(fila.getString(1).toString());
            String situacion= fila.getString(4);
            String situa="";

            if(situacion.equals("0")){
                situa="Terminada";
            }else if(situacion.equals("1")){
                situa="Activa";
            }

            partida=partida+fila.getString(1)+  "                                   Situacion: " + situa;

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


        int finalIdPartida = idPartida;
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //llama el método cargar();
                cargar(finalIdPartida);
            }

        });



    }

    public void cargar (int idPartida) {

        Sqlite dbHelper = new Sqlite(this);
        db = dbHelper.getWritableDatabase();

        Intent intent = new Intent(this,JuegoActivity.class);




        if(db!=null){

            Cursor c = db.rawQuery("SELECT * FROM jugador where id_partida= "+idPartida+";",null);

            int i=0;

            String[] jugador = new String [10];
            String[] jugador2 = new String [10];



            if(c.moveToFirst()){
                do{
                    if(i==0){
                        jugador[0] = c.getString(1);
                        jugador[1] = c.getString(2);
                        jugador[2] = c.getString(3);
                        jugador[3] = c.getString(4);
                        jugador[4] = c.getString(5);
                        jugador[5] = c.getString(6);
                        jugador[6] = c.getString(7);
                        jugador[7] = c.getString(8);
                        jugador[8] = c.getString(9);
                        jugador[9] = c.getString(10);

                        intent.putExtra("player1", jugador[0]);
                        intent.putExtra("posX", jugador[1]);
                        intent.putExtra("posY", jugador[2]);
                        intent.putExtra("quesoA", jugador[3]);
                        intent.putExtra("quesoR", jugador[4]);
                        intent.putExtra("quesoAz", jugador[5]);
                        intent.putExtra("quesoM", jugador[6]);
                        intent.putExtra("quesoN", jugador[7]);
                        intent.putExtra("quesoV", jugador[8]);
                        intent.putExtra("turno", jugador[9]);


                        i++;
                    }else {
                        jugador2[0] = c.getString(1);;
                        jugador2[1] = c.getString(2);
                        jugador2[2] = c.getString(3);
                        jugador2[3] = c.getString(4);
                        jugador2[4] = c.getString(5);
                        jugador2[5] = c.getString(6);
                        jugador2[6] = c.getString(7);
                        jugador2[7] = c.getString(8);
                        jugador2[8] = c.getString(9);
                        jugador[9] = c.getString(10);

                        intent.putExtra("player2", jugador2[0]);
                        intent.putExtra("2posX", jugador2[1]);
                        intent.putExtra("2posY", jugador2[2]);
                        intent.putExtra("2quesoA", jugador2[3]);
                        intent.putExtra("2quesoR", jugador2[4]);
                        intent.putExtra("2quesoAz", jugador2[5]);
                        intent.putExtra("2quesoM", jugador2[6]);
                        intent.putExtra("2quesoN", jugador2[7]);
                        intent.putExtra("2quesoV", jugador2[8]);
                        intent.putExtra("2turno", jugador2[9]);
                    }

                }while(c.moveToNext());

            }
            startActivity(intent);

        }

    }
}