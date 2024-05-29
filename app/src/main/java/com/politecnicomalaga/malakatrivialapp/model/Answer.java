package com.politecnicomalaga.malakatrivialapp.model;

public class Answer {
    //Definimos los dos atributos que debe de tener una respuesta en sí
    private String respuesta;
    private boolean correcta;

    public Answer(String respuesta, boolean correcta) {
        this.respuesta = respuesta;
        this.correcta = correcta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }
    
}
