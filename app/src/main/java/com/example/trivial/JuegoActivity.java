package com.example.trivial;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;

public class JuegoActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView tablero = findViewById(R.id.tablero);
        BitmapDrawable drawable = (BitmapDrawable) tablero.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        bitmap.getColor();
    }
}