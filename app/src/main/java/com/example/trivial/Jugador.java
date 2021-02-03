package com.example.trivial;

import java.io.Serializable;

public class Jugador implements Serializable {
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
        this.Nombre = nombre;
        this.casillaX = casillaX;
        this.casillaY = casillaY;
        this.Quesito_amarillo = quesito_amarillo;
        this.Quesito_rosa = quesito_rosa;
        this.Quesito_azul = quesito_azul;
        this.Quesito_morado = quesito_morado;
        this.Quesito_naranja = quesito_naranja;
        this.Quesito_verde = quesito_verde;
        this.turno = turno;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getCasillaX() {
        return casillaX;
    }

    public void setCasillaX(int casillaX) {
        this.casillaX = casillaX;
    }

    public int getCasillaY() {
        return casillaY;
    }

    public void setCasillaY(int casillaY) {
        this.casillaY = casillaY;
    }

    public boolean isQuesito_amarillo() {
        return Quesito_amarillo;
    }

    public void setQuesito_amarillo(boolean quesito_amarillo) {
        Quesito_amarillo = quesito_amarillo;
    }

    public boolean isQuesito_rosa() {
        return Quesito_rosa;
    }

    public void setQuesito_rosa(boolean quesito_rosa) {
        Quesito_rosa = quesito_rosa;
    }

    public boolean isQuesito_azul() {
        return Quesito_azul;
    }

    public void setQuesito_azul(boolean quesito_azul) {
        Quesito_azul = quesito_azul;
    }

    public boolean isQuesito_morado() {
        return Quesito_morado;
    }

    public void setQuesito_morado(boolean quesito_morado) {
        Quesito_morado = quesito_morado;
    }

    public boolean isQuesito_naranja() {
        return Quesito_naranja;
    }

    public void setQuesito_naranja(boolean quesito_naranja) {
        Quesito_naranja = quesito_naranja;
    }

    public boolean isQuesito_verde() {
        return Quesito_verde;
    }

    public void setQuesito_verde(boolean quesito_verde) {
        Quesito_verde = quesito_verde;
    }

    public boolean isTurno() {
        return turno;
    }

    public void setTurno(boolean turno) {
        this.turno = turno;
    }
}
