package com.example.trivial;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class JuegoActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        ImageView tablero = findViewById(R.id.tablero);
        BitmapDrawable drawable = (BitmapDrawable) tablero.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        int pixel=bitmap.getPixel(862,45);
        TextView textView = findViewById(R.id.textView);
        textView.setText(String.format("#%02X%02X%02X", Color.red(pixel), Color.green(pixel),Color.blue(pixel)));
    }
}