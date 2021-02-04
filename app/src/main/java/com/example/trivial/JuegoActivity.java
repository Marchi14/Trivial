package com.example.trivial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class JuegoActivity extends AppCompatActivity implements View.OnTouchListener {

    static final int ASK_QUESTION_REQUEST = 100;
    Jugador jugador1,jugador2;
    Bitmap bmp;
    ImageView tablero, cursor, dado;
    TextView nJugador1, nJugador2;
    Button bI, bD;
    double cX, cY;
    String tema;
    Point[] points = new Point[2];
    double angulo = Math.toRadians((double)360 / 48 + 0.2d);
    int ndado = 1;
    boolean tirada=true,quesito=false;

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        tablero = findViewById(R.id.tablero);
        cursor = findViewById(R.id.cursor);
        bI = findViewById(R.id.bIzquierda);
        bD = findViewById(R.id.bDerecha);
        bmp = ((BitmapDrawable) tablero.getDrawable()).getBitmap();
        bmp = bmp.copy(Bitmap.Config.ARGB_8888, true);
        dado = findViewById(R.id.dado);
        dado.setOnTouchListener(this);

        //CONSEGUIR LOS NOMBRES DE LOS JUGADORES
        nJugador1=findViewById(R.id.Nombre_Jugador);
        nJugador2=findViewById(R.id.n_Jugador2);
        Bundle b=getIntent().getExtras();
        String n1=(String)b.get("player1");
        String n2=(String)b.get("player2");
        nJugador1.setText(n1);
        nJugador2.setText(n2);

        jugador1 = new Jugador(n1);
        jugador1.setTurno(true);
        jugador2 = new Jugador(n2);

        bI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoverIzquierda();
                if (bI.getText() != "Volver a Tirar")
                    IniciarPregunta();
                tirada=true;
            }
        });

        bD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoverDerecha();
                if (bD.getText() != "Volver a Tirar")
                    IniciarPregunta();
                tirada=true;
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == JuegoActivity.RESULT_OK) {
            boolean acertado = data.getBooleanExtra("acierto",false);
            if(jugador1.isTurno() && !acertado){
                jugador1.setTurno(false);
                jugador2.setTurno(true);
            }
            else if(jugador2.isTurno() && !acertado){
                jugador1.setTurno(true);
                jugador2.setTurno(false);
            }
            Quesito(acertado);
        }
    }

    private void IniciarPregunta(){
        boolean f=false;
        if ((jugador1.FinalRound() && jugador1.isTurno()) || (jugador2.FinalRound() && jugador2.isTurno()))
            f = true;
        bI.setEnabled(false);
        bD.setEnabled(false);
        Intent intent = new Intent(JuegoActivity.this,PreguntaActivity.class);
        intent.putExtra("Tema",GetTypeofPregunta(bmp.getPixel((int) (cursor.getX()+cursor.getWidth()/2), (int) (cursor.getY()+cursor.getHeight()/2))));
        intent.putExtra("queso",quesito);
        intent.putExtra("jugador 1",jugador1);
        intent.putExtra("jugador 2",jugador2);
        intent.putExtra("final",f);
        startActivityForResult(intent, ASK_QUESTION_REQUEST);
    }

    private void MoverIzquierda() {
        cursor.setX(points[0].x);
        cursor.setY(points[0].y);
    }

    private void MoverDerecha() {
        cursor.setX(points[1].x);
        cursor.setY(points[1].y);
    }

    private void DetectarCasillas(Button bI, Button bD) {
        Point Izq;
        Point Der;
        cX = cursor.getX();
        cY = cursor.getY();
        Izq = Izquierda(cX, cY);
        Der = Derecha(cX, cY);

        int pixel = bmp.getPixel(Izq.x+cursor.getWidth()/2, Izq.y+cursor.getHeight()/2);
        tema = GetTypeofPregunta(pixel);
        if (tema != null)
            bI.setText(tema);
        bI.setBackgroundColor(pixel);
        pixel = bmp.getPixel(Der.x+cursor.getWidth()/2, Der.y+cursor.getHeight()/2);
        tema = GetTypeofPregunta(pixel);
        if (tema != null)
            bD.setText(tema);
        bD.setBackgroundColor(pixel);
        points[0] = Izq;
        points[1] = Der;
    }


    private Point Derecha(double cX, double cY) {
        double fX;
        double fY;
        double h = 540;
        double ang = angulo * ndado;
        double hX = cX - h;
        double hY = cY - h;
        fX = (hX * Math.cos(ang)) + (hY * Math.sin(-ang));
        fY = (hX * Math.sin(ang)) + (hY * Math.cos(ang));
        fX += h;
        fY += h;
        int fXi = (int) fX;
        int fYi = (int) fY;
        return new Point(fXi, fYi);
    }

    private Point Izquierda(double cX, double cY) {
        double fX;
        double fY;
        double h = 540;
        double ang = angulo * ndado;
        double hX = cX - h;
        double hY = cY - h;
        fX = (hX * Math.cos(ang)) + (hY * Math.sin(ang));
        fY = (hX * Math.sin(-ang)) + (hY * Math.cos(ang));
        fX += h;
        fY += h;
        int fXi = (int) fX;
        int fYi = (int) fY;
        return new Point(fXi, fYi);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (tirada)
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                Random rng = new Random();
                ndado = rng.nextInt(6)+1;
                switch (ndado) {
                    case 1:
                        dado.setImageResource(R.drawable.dado1);
                        break;
                    case 2:
                        dado.setImageResource(R.drawable.dado2);
                        break;
                    case 3:
                        dado.setImageResource(R.drawable.dado3);
                        break;
                    case 4:
                        dado.setImageResource(R.drawable.dado4);
                        break;
                    case 5:
                        dado.setImageResource(R.drawable.dado5);
                        break;
                    case 6:
                        dado.setImageResource(R.drawable.dado6);
                        break;
                }
                DetectarCasillas(bI, bD);
                tirada=false;
                bI.setEnabled(true);
                bD.setEnabled(true);
            }
        return false;
    }

    private String GetTypeofPregunta(int pixel) {
        int r = Color.red(pixel);
        int b = Color.blue(pixel);
        int g = Color.green(pixel);
        String color = String. format("#%02X%02X%02X", r, g, b);
        for (Colores colorE : Colores.values())
            if (color.equals(colorE.getColor())){
                quesito = colorE.isQuesito();
                return colorE.getTipo();
            }
        return null;
    }

    private void Quesito(boolean acertado){
        if (quesito && acertado){
            ImageView queso;
            if(jugador1.isTurno()){
                switch(tema){
                    case "Ocio y Deporte":
                        queso = findViewById(R.id.Deportes1);
                        queso.setImageAlpha(127);
                        jugador1.setQuesito_naranja(true);
                        break;
                    case "Historia":
                        queso = findViewById(R.id.Historia1);
                        queso.setImageAlpha(127);
                        jugador1.setQuesito_amarillo(true);
                        break;
                    case "Ciencias y naturaleza":
                        queso = findViewById(R.id.Naturaleza1);
                        queso.setImageAlpha(127);
                        jugador1.setQuesito_verde(true);
                        break;
                    case "Arte y Literatura":
                        queso = findViewById(R.id.Arte1);
                        queso.setImageAlpha(127);
                        jugador1.setQuesito_morado(true);
                        break;
                    case "Geografía":
                        queso = findViewById(R.id.Geografia1);
                        queso.setImageAlpha(127);
                        jugador1.setQuesito_azul(true);
                        break;
                    case "Entretenimiento":
                        queso = findViewById(R.id.Entretenimiento1);
                        queso.setImageAlpha(127);
                        jugador1.setQuesito_rosa(true);
                        break;
                }
            }
            else if(jugador2.isTurno()){
                switch(tema){
                    case "Ocio y Deporte":
                        queso = findViewById(R.id.Deportes2);
                        queso.setImageAlpha(127);
                        jugador2.setQuesito_naranja(true);
                        break;
                    case "Historia":
                        queso = findViewById(R.id.Historia2);
                        queso.setImageAlpha(127);
                        jugador2.setQuesito_amarillo(true);
                        break;
                    case "Ciencias y naturaleza":
                        queso = findViewById(R.id.Naturaleza2);
                        queso.setImageAlpha(127);
                        jugador2.setQuesito_verde(true);
                        break;
                    case "Arte y Literatura":
                        queso = findViewById(R.id.Arte2);
                        queso.setImageAlpha(127);
                        jugador2.setQuesito_morado(true);
                        break;
                    case "Geografía":
                        queso = findViewById(R.id.Geografia2);
                        queso.setImageAlpha(127);
                        jugador2.setQuesito_azul(true);
                        break;
                    case "Entretenimiento":
                        queso = findViewById(R.id.Entretenimiento2);
                        queso.setImageAlpha(127);
                        jugador2.setQuesito_rosa(true);
                        break;
                }
            }
        }
    }
}