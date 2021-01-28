package com.example.trivial;

public enum Colores {
    Naranja("#F17B35", "Ocio y Deporte"),
    qNaranja("#E4732F", "Ocio y Deporte"),
    Verde("#4DAB53", "Ciencias y naturaleza"),
    qVerde("#4D9E53", "Ciencias y naturaleza"),
    Rosa("#FD85B3", "Entretenimiento"),
    qRosa("#F385B3", "Entretenimiento"),
    Amarillo("#EFDB3A", "Historia"),
    qAmarillo("#E5D32B", "Historia"),
    Morado("#9A47E0","Arte y Literatura"),
    qMorado("#8E40D1", "Arte y Literatura"),
    Azul("#5FBFCD", "Geografía"),
    qAzul("#5FADCD", "Geografía"),
    Blanco("#EBCFC3", "Volver a Tirar");


    private final String color;
    private final String tipo;

    Colores(String color, String tipo) {
        this.color = color;
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public String getTipo() {
        return tipo;
    }
}
