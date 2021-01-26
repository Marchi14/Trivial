package com.example.trivial;

public enum Colores {
    Naranja("F17B35"),
    qNaranja("E4732F"),
    Verde("4DAB53"),
    qVerde("4D9E53"),
    Rosa("FD85B3"),
    qRosa("F385B3"),
    Amarillo("EFDB3A"),
    qAmarillo("E5D32B"),
    Morado("9A47E0"),
    qMorado("8E40D1"),
    Azul("5FBFCD"),
    qAzul("5FADCD"),
    Blanco("EBCFC3");


    private final String color;

    Colores(String color) {
        this.color = color;
    }

    public String getHEXColor()
    {
        return this.color;
    }
}
