package com.example.trivial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
   private Button addjugador, start, load;
   private ImageView avatar,avatar1;
   private EditText nombrePlayer, nombrePlayer1;
   private ConstraintLayout contenedor;

    int [] avatares= {R.drawable.hombre, R.drawable.mujer};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contenedor=(ConstraintLayout) findViewById(R.id.ContenedorP);
        addjugador=(Button) findViewById(R.id.AddJugador);
        start=(Button) findViewById(R.id.Start);
        load=(Button) findViewById(R.id.Load);
        avatar1=(ImageView) findViewById(R.id.Avatar1);
        nombrePlayer1=(EditText) findViewById(R.id.Njugador1);

        addjugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPlayer();
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
        startActivity(intent);
    }

    public void AddPlayer(){
        Random r1=new Random();
        int i1= r1.nextInt(2);

        ImageView avatar = new ImageView(this);
        EditText nombrePlayer = new EditText(this);

        avatar.setImageResource(avatares[i1]);
        avatar.setY(62);
        avatar.setX(88);
        nombrePlayer.setY(62);
        nombrePlayer.setX(176);

        contenedor.addView(avatar1,44, 45);
        contenedor.addView(nombrePlayer,140,38);
    }
}