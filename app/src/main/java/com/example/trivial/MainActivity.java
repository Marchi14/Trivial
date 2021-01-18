package com.example.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
   private Button addjugador, start, load;
   private ImageView avatar;
   private EditText nombrePlayer;

    int [] avatares= {R.drawable.hombre, R.drawable.mujer};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addjugador=(Button) findViewById(R.id.AddJugador);
        start=(Button) findViewById(R.id.Start);
        load=(Button) findViewById(R.id.Load);
        avatar=(ImageView) findViewById(R.id.Avatar);
        nombrePlayer=(EditText) findViewById(R.id.Njugador);

        addjugador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddPlayer();
            }
        });
    }

    public void AddPlayer(){
        Random r1=new Random();
        int i1= r1.nextInt(2);

        ImageView avatar1 = new ImageView(this);
        EditText nombrePlayer = new EditText(this);

        avatar.setImageResource(avatares[i1]);
    }

    public void IniciarPartida(View v){
        Intent intent = new Intent(this, JuegoActivity.class);
        startActivity(intent);
    }
}


