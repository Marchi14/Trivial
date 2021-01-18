package com.example.trivial;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class JuegoActivity extends AppCompatActivity {

    Bitmap bmp;
    ImageView tablero, cursor;


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        tablero = findViewById(R.id.tablero);
        cursor = findViewById(R.id.cursor);
        Button b = findViewById(R.id.button);
        bmp = ((BitmapDrawable)tablero.getDrawable()).getBitmap();
        bmp=bmp.copy(Bitmap.Config.ARGB_8888,true);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoverCursorIzquierda();
            }
        });
    }

    private void MoverCursorIzquierda() {
    }

    private void GetQuestions() {
        int pixel=bmp.getPixel((int) (cursor.getX() + (cursor.getWidth()/2)),(int) cursor.getY() + (cursor.getHeight()/2));
    }
}