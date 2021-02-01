package com.example.trivial;

public enum Colores {
    Naranja("#F17B35", "Ocio y Deporte", false),
    qNaranja("#E4732F", "Ocio y Deporte", true),
    Verde("#4DAB53", "Ciencias y naturaleza", false),
    qVerde("#4D9E53", "Ciencias y naturaleza", true),
    Rosa("#FD85B3", "Entretenimiento",false),
    qRosa("#F385B3", "Entretenimiento", true),
    Amarillo("#EFDB3A", "Historia", false),
    qAmarillo("#E5D32B", "Historia", true),
    Morado("#9A47E0","Arte y Literatura",false),
    qMorado("#8E40D1", "Arte y Literatura",true),
    Azul("#5FBFCD", "Geografía",false),
    qAzul("#5FADCD", "Geografía",true),
    Blanco("#EBCFC3", "Volver a Tirar",false);


    private final String color;
    private final String tipo;
    private final boolean quesito;

    Colores(String color, String tipo, boolean quesito) {
        this.color = color;
        this.tipo = tipo;
        this.quesito = quesito;
    }

    public String getColor() {
        return color;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isQuesito() {
        return quesito;
    }
}
