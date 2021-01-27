package com.example.trivial;

public class Jugador {
    String Nombre;
    int casillaX;
    int casillaY;
    boolean Quesito_amarillo, Quesito_rosa, Quesito_azul,Quesito_morado,Quesito_naranja,Quesito_verde, turno;

    public Jugador(String Nombre){
        this.Nombre=Nombre;
        this.Quesito_amarillo=false;
        this.Quesito_rosa=false;
        this.Quesito_azul=false;
        this.Quesito_morado=false;
        this.Quesito_naranja=false;
        this.Quesito_verde=false;
        this.turno=false;
    }

    public Jugador(String nombre, int casillaX, int casillaY, boolean quesito_amarillo, boolean quesito_rosa, boolean quesito_azul, boolean quesito_morado, boolean quesito_naranja, boolean quesito_verde, boolean turno) {
        Nombre = nombre;
        this.casillaX = casillaX;
        this.casillaY = casillaY;
        Quesito_amarillo = quesito_amarillo;
        Quesito_rosa = quesito_rosa;
        Quesito_azul = quesito_azul;
        Quesito_morado = quesito_morado;
        Quesito_naranja = quesito_naranja;
        Quesito_verde = quesito_verde;
        this.turno = turno;
    }
}
