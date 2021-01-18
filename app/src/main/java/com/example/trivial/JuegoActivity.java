package com.example.trivial;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.RotateDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.BreakIterator;

public class JuegoActivity extends AppCompatActivity {

    TextView ex, ey;
    ImageView cursor, tablero;
    Bitmap bitmap, bmpm;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        tablero = findViewById(R.id.tablero);
        bitmap = ((BitmapDrawable)tablero.getDrawable()).getBitmap();
        bmpm = bitmap.copy(Bitmap.Config.ARGB_8888,true);
        cursor = findViewById(R.id.ficha);
        Button b = findViewById(R.id.button);
        ex = findViewById(R.id.editTextNumber);
        ey = findViewById(R.id.editTextNumber2);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CambioPosicion();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            int w = tablero.getWidth();
            int h = tablero.getHeight();
            bmpm.setWidth(w);
            bmpm.setHeight(h);
        }

    }

    private void CambioPosicion() {
        String X = ex.getText().toString();
        int Xi = Integer.parseInt(X);
        String Y = ey.getText().toString();
        int Yi = Integer.parseInt(Y);
        cursor.setX(Xi);
        cursor.setY(Yi);
        int pixel = bmpm.getPixel((int) cursor.getX(), (int) cursor.getY());
        TextView textView = findViewById(R.id.textView);
        textView.setText(String.format("#%02X%02X%02X", Color.red(pixel), Color.green(pixel),Color.blue(pixel)));
    }


}