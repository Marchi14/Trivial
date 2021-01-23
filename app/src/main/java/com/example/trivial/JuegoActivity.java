package com.example.trivial;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

public class JuegoActivity extends AppCompatActivity {

    Bitmap bmp;
    ImageView tablero, cursor;
    Button bI,bD;
    int cX,cY;
    Point[] points = new Point[2];
    int angulo = ((360/6)/7);
    int ndado=1;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        tablero = findViewById(R.id.tablero);
        cursor = findViewById(R.id.cursor);
        bI = findViewById(R.id.bIzquierda);
        bD = findViewById(R.id.bDerecha);
        bmp = ((BitmapDrawable)tablero.getDrawable()).getBitmap();
        bmp=bmp.copy(Bitmap.Config.ARGB_8888,true);
        ImageView dado = findViewById(R.id.dado);

        dado.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        bI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoverIzquierda();
                DetectarCasillas(bI,bD);
            }
        });

        bD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoverDerecha();
                DetectarCasillas(bI,bD);
            }
        });
    }

    private void MoverIzquierda() {
        cursor.setX(points[0].x);
        cursor.setY(points[0].y);
    }

    private void MoverDerecha() {
        cursor.setX(points[1].x);
        cursor.setY(points[1].y);
    }

    private void DetectarCasillas(Button bI,Button bD){
        Point Izq;
        Point Der;
        Izq=Izquierda(cX,cY);
        Der=Derecha(cX,cY);
        int pixel=bmp.getPixel(Izq.x,Izq.y);
        bI.setBackgroundColor(pixel);
        pixel=bmp.getPixel(Der.x,Der.y);
        bD.setBackgroundColor(pixel);
        points[0]=Izq;
        points[1]=Der;
    }


    private Point Derecha(int cX,int cY) {
        int fX;
        int fY=cY;
        int h = bmp.getWidth()/2;
        if (cY < h) {
            fX = (int) ((cX - h) * Math.cos(Math.toRadians(angulo * ndado)) - (cY - h) * Math.sin(Math.toRadians(angulo * ndado)));
            if (cX < h)
                fY = (int) (((cX - h) * (-Math.sin(Math.toRadians(angulo * ndado)))) + (cY - h) * Math.cos(Math.toRadians(angulo * ndado)));
            else
                fY = (int) ((cX - h) * Math.sin(Math.toRadians(angulo * ndado)) + (cY - h) * Math.cos(Math.toRadians(angulo * ndado)));
        }
        else{
            fX = (int) ((cX-h)*Math.cos(Math.toRadians(angulo*ndado))+(cY-h)*Math.sin(Math.toRadians(angulo*ndado)));
            if (cX < h)
                fY = (int) ((cX - h) * Math.sin(Math.toRadians(angulo * ndado)) + (cY - h) * Math.cos(Math.toRadians(angulo * ndado)));
            else
                fY = (int) (((cX - h) * (-Math.sin(Math.toRadians(angulo * ndado)))) + (cY - h) * Math.cos(Math.toRadians(angulo * ndado)));
        }
        fX += h;
        fY += h;
        return new Point(fX,fY);
    }

    private Point Izquierda(int cX, int cY) {
        int fX;
        int fY;
        int h = bmp.getWidth() / 2;
        if (cY < h) {
            fX = (int) ((cX - h) * Math.cos(Math.toRadians(angulo * ndado)) + (cY - h) * Math.sin(Math.toRadians(angulo * ndado)));
            if (cX < h)
                fY = (int) ((cX - h) * Math.sin(Math.toRadians(angulo * ndado)) + (cY - h) * Math.cos(Math.toRadians(angulo * ndado)));
            else
                fY = (int) (((cX - h) * (-Math.sin(Math.toRadians(angulo * ndado)))) + (cY - h) * Math.cos(Math.toRadians(angulo * ndado)));
        } else {
            fX = (int) ((cX - h) * Math.cos(Math.toRadians(angulo * ndado)) - (cY - h) * Math.sin(Math.toRadians(angulo * ndado)));
            if (cX < h)
                fY = (int) (((cX - h) * (-Math.sin(Math.toRadians(angulo * ndado)))) + (cY - h) * Math.cos(Math.toRadians(angulo * ndado)));
            else
                fY = (int) ((cX - h) * Math.sin(Math.toRadians(angulo * ndado)) + (cY - h) * Math.cos(Math.toRadians(angulo * ndado)));
        }
        fX += h;
        fY += h;
        return new Point(fX, fY);
    }
}
