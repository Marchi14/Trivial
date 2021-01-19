package com.example.trivial;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class JuegoActivity extends AppCompatActivity {

    Bitmap bmp;
    ImageView tablero, cursor;
    int ndado=1;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        tablero = findViewById(R.id.tablero);
        cursor = findViewById(R.id.cursor);
        Button bI = findViewById(R.id.bIzquierda);
        Button bD = findViewById(R.id.bDerecha);
        bmp = ((BitmapDrawable)tablero.getDrawable()).getBitmap();
        bmp=bmp.copy(Bitmap.Config.ARGB_8888,true);
        ImageView dado = findViewById(R.id.dado);

       // dado.setOnTouchListener(new View.OnTouchListener() {
       //     @Override
       //     public boolean onTouch(View v, MotionEvent event) {
       //         dadoRNG();
       //     }
       // });
//
        bI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoverCursorIzquierda();
                GetTypeofQuestions(bI);
            }
        });

        bD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoverCursorDerecha();
                GetTypeofQuestions(bD);
            }
        });
    }

    //private void dadoRNG() {
//
    //}

    private void MoverCursorDerecha() {
        int radio = 495;
        float distancia = (float) (2*(radio * Math.sin((10*ndado)/2)));
        int cX = (int) cursor.getX();
        int cY = (int) cursor.getY();
        int fX = (int) (cX - (Math.cos(-10*ndado) * distancia));
        int fY = (int) (cY - (Math.sin(-10*ndado) * distancia));
        cursor.setX(fX);
        cursor.setY(fY);
    }

    private void MoverCursorIzquierda() {
        int radio = 495;
        float distancia = (float) (2*(radio * Math.sin((10*ndado)/2)));
        int cX = (int) cursor.getX();
        int cY = (int) cursor.getY();
        int fX = (int) (cX - (Math.cos(10*ndado) * distancia));
        int fY = (int) (cY - (Math.sin(10*ndado) * distancia));
        cursor.setX(fX);
        cursor.setY(fY);
    }

    private void GetTypeofQuestions(Button b) {
        int pixel=bmp.getPixel((int) (cursor.getX() + cursor.getWidth()/2),(int) cursor.getY() + cursor.getHeight()/2);
        b.setBackgroundColor(pixel);
    }
}