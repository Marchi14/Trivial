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
    float angulo = ((360/6)/6.5f);
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

    /*private void Derecha(){
        if (cY < bmp.getHeight()/2){
            fX = (float) (cX - (Math.cos(10*ndado) * distancia));
            newX = centerX + (point2x-centerX)*Math.cos(x) - (point2y-centerY)*Math.sin(x);

newY = centerY + (point2x-centerX)*Math.sin(x) + (point2y-centerY)*Math.cos(x);
        }
    }
    */

    private void MoverCursorDerecha() {
        int cX = (int) cursor.getX();
        int cY = (int) cursor.getY();
        float fX;
        float fY;
        if (cY < bmp.getHeight()/2)
            fX = (float) ((cX-540)*Math.cos(Math.toRadians(angulo))-(cY-540)*Math.sin(Math.toRadians(angulo)));
        else
            fX = (float) ((cX-540)*Math.cos(Math.toRadians(angulo))+(cY-540)*Math.sin(Math.toRadians(angulo)));
        if(cX < bmp.getWidth()/2)
            fY = (float) (((cX-540)*(-Math.sin(Math.toRadians(angulo))))+(cY-540)*Math.cos(Math.toRadians(angulo)));
        else
            fY = (float) ((cX-540)*Math.sin(Math.toRadians(angulo))+(cY-540)*Math.cos(Math.toRadians(angulo)));
        cursor.setX(fX + 540);
        cursor.setY(fY + 540);
    }

    private void MoverCursorIzquierda() {
        int cX = (int) cursor.getX();
        int cY = (int) cursor.getY();
        float fX;
        float fY;
        if (cY < bmp.getHeight()/2)
            fX = (float) ((cX-540)*Math.cos(Math.toRadians(angulo))+(cY-540)*Math.sin(Math.toRadians(angulo)));
        else
            fX = (float) ((cX-540)*Math.cos(Math.toRadians(angulo))-(cY-540)*Math.sin(Math.toRadians(angulo)));
        if(cX < bmp.getWidth()/2)
            fY = (float) ((cX-540)*Math.sin(Math.toRadians(angulo))+(cY-540)*Math.cos(Math.toRadians(angulo)));
        else
            fY = (float) (((cX-540)*(-Math.sin(Math.toRadians(angulo))))+(cY-540)*Math.cos(Math.toRadians(angulo)));
        cursor.setX(fX + 540);
        cursor.setY(fY + 540);
    }

    private void GetTypeofQuestions(Button b) {
        int pixel=bmp.getPixel((int) (cursor.getX() + cursor.getWidth()/2),(int) cursor.getY() + cursor.getHeight()/2);
        b.setBackgroundColor(pixel);
    }
}
