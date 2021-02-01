package com.example.trivial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
   private Button addjugador, start, load, deletejugador;
   private ImageView avatar1,avatar2, avatar3, avatar4,avatar5,avatar6;
   public EditText nombrePlayer1, nombrePlayer2, nombrePlayer3,nombrePlayer4,nombrePlayer5,nombrePlayer6;
   private ConstraintLayout contenedor;
    SQLiteDatabase db;

    int [] avatares= {R.drawable.hombre, R.drawable.mujer};
    int contadorP=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contenedor = findViewById(R.id.ContenedorP);
        addjugador = findViewById(R.id.AddJugador);
        deletejugador = findViewById(R.id.DeletePlayer);
        start = findViewById(R.id.Start);
        load = findViewById(R.id.Load);
        avatar1 = findViewById(R.id.Avatar1);
        avatar2 = findViewById(R.id.Avatar2);
        avatar3 = findViewById(R.id.Avatar3);
        avatar4 = findViewById(R.id.Avatar4);
        avatar5 = findViewById(R.id.Avatar5);
        avatar6 = findViewById(R.id.Avatar6);
        nombrePlayer1 = findViewById(R.id.Njugador1);
        nombrePlayer2 = findViewById(R.id.Njugador2);
        nombrePlayer3 = findViewById(R.id.Njugador3);
        nombrePlayer4 = findViewById(R.id.Njugador4);
        nombrePlayer5 = findViewById(R.id.Njugador5);
        nombrePlayer6 = findViewById(R.id.Njugador6);

        Ocultarjugadores();

        Sqlite dbHelper = new Sqlite(this);
        db = dbHelper.getWritableDatabase();

        //CREACIÓN DE NUEVOS JUGADORES
        addjugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               switch (contadorP){
                   case 2:
                       avatar3.setVisibility(View.VISIBLE);
                       nombrePlayer3.setVisibility(View.VISIBLE);
                       deletejugador.setVisibility(View.VISIBLE);
                       deletejugador.setEnabled(true);
                       contadorP++;
                       break;
                   case 3:
                       avatar4.setVisibility(View.VISIBLE);
                       nombrePlayer4.setVisibility(View.VISIBLE);
                       deletejugador.setEnabled(true);
                       contadorP++;
                       break;
                   case 4:
                       avatar5.setVisibility(View.VISIBLE);
                       nombrePlayer5.setVisibility(View.VISIBLE);
                       deletejugador.setEnabled(true);
                       contadorP++;
                       break;
                   case 5:
                       avatar6.setVisibility(View.VISIBLE);
                       nombrePlayer6.setVisibility(View.VISIBLE);
                       deletejugador.setEnabled(true);
                       addjugador.setEnabled(false);
                       contadorP++;
                       break;
                   case 6:
                       break;
               }

            }
        });

        //BORRADO DE JUGADORES
        deletejugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (contadorP){
                    case 2:
                        addjugador.setEnabled(true);
                        deletejugador.setEnabled(false);
                        break;
                    case 3:
                        avatar3.setVisibility(View.INVISIBLE);
                        nombrePlayer3.setVisibility(View.INVISIBLE);
                        addjugador.setEnabled(true);
                        deletejugador.setEnabled(false);
                        contadorP--;
                        break;
                    case 4:
                        avatar4.setVisibility(View.INVISIBLE);
                        nombrePlayer4.setVisibility(View.INVISIBLE);
                        addjugador.setEnabled(true);
                        deletejugador.setEnabled(true);
                        contadorP--;
                        break;
                    case 5:
                        avatar5.setVisibility(View.INVISIBLE);
                        nombrePlayer5.setVisibility(View.INVISIBLE);
                        deletejugador.setEnabled(true);
                        addjugador.setEnabled(true);
                        contadorP--;
                        break;
                    case 6:
                        avatar6.setVisibility(View.INVISIBLE);
                        nombrePlayer6.setVisibility(View.INVISIBLE);
                        deletejugador.setEnabled(true);
                        addjugador.setEnabled(true);
                        contadorP--;
                        break;
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciarPartida();
            }
        });


    }

    private void IniciarPartida() {
        Intent intent = new Intent(this,JuegoActivity.class);
        Bitmap a1 =((BitmapDrawable)avatar1.getDrawable()).getBitmap();
        Bitmap a2 =((BitmapDrawable)avatar2.getDrawable()).getBitmap();
        Bitmap a3 =((BitmapDrawable)avatar3.getDrawable()).getBitmap();
        Bitmap a4 =((BitmapDrawable)avatar4.getDrawable()).getBitmap();
        Bitmap a5 =((BitmapDrawable)avatar5.getDrawable()).getBitmap();
        Bitmap a6 =((BitmapDrawable)avatar6.getDrawable()).getBitmap();

        //RECOGEMOS LOS NOMBRES DE LOS JUGADORES PARA MANDARLOS AL JUEGO ACTIVITY
        String n1= String.valueOf(nombrePlayer1.getText());
        String n2= String.valueOf(nombrePlayer2.getText());
        intent.putExtra("player1", n1);
        intent.putExtra("player2", n2);


        startActivity(intent);

    }


    //METODO QUE OCULTA LOS JUGADORES 3-6 Y DESHABILITA EL BOTÓN ELIMINAR JUGADOR AL INICIO DE ESTA ACTIVITY
    private void Ocultarjugadores(){
        avatar3.setVisibility(View.INVISIBLE);
        nombrePlayer3.setVisibility(View.INVISIBLE);

        avatar4.setVisibility(View.INVISIBLE);
        nombrePlayer4.setVisibility(View.INVISIBLE);

        avatar5.setVisibility(View.INVISIBLE);
        nombrePlayer5.setVisibility(View.INVISIBLE);

        avatar6.setVisibility(View.INVISIBLE);
        nombrePlayer6.setVisibility(View.INVISIBLE);

        deletejugador.setEnabled(false);
    }

}