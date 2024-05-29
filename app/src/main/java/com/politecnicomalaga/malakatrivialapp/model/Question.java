package com.politecnicomalaga.malakatrivialapp.model;

import java.util.ArrayList;
import java.util.List;

public class Question {
    //Definimos los atributos asociados a lo que es la pregunta en sí, la imagen que se va a colocar
    //y una estructura de almacenamiento(en este caso en forma de lista) para la serie de respuestas
    private String pregunta;
    private String urlImage;
    private List<Answer> listaRespuestas;

    public Question(String pregunta, String[] resp_preg, String url_img) {
        this.pregunta = pregunta;
        this.urlImage = url_img;
        listaRespuestas = new ArrayList<>();
        //Se realiza un bucle para iterar desde la primera hasta la última respuesta de las preguntas
        for(int i=0; i< (resp_preg.length-1); i++){
            //Se compara cada respuesta con la última del array
            if(resp_preg[resp_preg.length-1].equals(resp_preg[i])){
                //Si son iguales se añade un objeto Respuesta marcándola
                //como correcta (true) y se agrega a la lista de las respuestas.
                listaRespuestas.add(new Answer(resp_preg[i], true));
            }else{
                //Si no son iguales se añade un objeto Respuesta marcándola
                //como incorrecta (false) y se agrega a la lista de las respuestas.
                listaRespuestas.add(new Answer(resp_preg[i], false));
            }
        }
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public List<Answer> getListaRespuestas() {
        return listaRespuestas;
    }

    public void setListaRespuestas(List<Answer> listaRespuestas) {
        this.listaRespuestas = listaRespuestas;
    }

    public String getCorrectAnswerString(){
        //Recorremos cada una de las respuestas para saber cuál de ellas es correcta y devolverla,
        //en caso contrario no devolver nada(null)
        for(Answer a:listaRespuestas){
            if(a.isCorrecta()) return a.getRespuesta();
        }
        return null;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}
