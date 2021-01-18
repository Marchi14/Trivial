package com.example.trivial;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
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
    private static final int radio = 495;
    int ndado=2;

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
        int fX;
        int fY;
        int cX = (int) cursor.getX();
        int cY = (int) cursor.getY();
        if (cY<bmp.getHeight()/2)
            fX = (int) (cX + 64*Math.cos(10*ndado));
        else
            fX = (int) (cX - 64*Math.cos(10*ndado));
        if (cX<bmp.getWidth()/2)
            fY = (int) (cY - 64*Math.sin(10*ndado));
        else
            fY = (int) (cY + 64*Math.sin(10*ndado));
        cursor.setX(fX);
        cursor.setY(fY);
    }

    private void MoverCursorIzquierda() {
        int fX;
        int fY;
        int cX = (int) cursor.getX();
        int cY = (int) cursor.getY();
        if (cY<bmp.getHeight()/2)
            fX = (int) (cX - 64*Math.cos(10*ndado));
        else
            fX = (int) (cX + 64*Math.cos(10*ndado));
        if (cX<bmp.getWidth()/2)
            fY = (int) (cY + 64*Math.sin(10*ndado));
        else
            fY = (int) (cY - 64*Math.sin(10*ndado));
        cursor.setX(fX);
        cursor.setY(fY);
    }

    private void GetTypeofQuestions(Button b) {
        int pixel=bmp.getPixel((int) (cursor.getX() + (cursor.getWidth()/2)),(int) cursor.getY() + (cursor.getHeight()/2));
        b.setBackgroundColor(pixel);
    }
}