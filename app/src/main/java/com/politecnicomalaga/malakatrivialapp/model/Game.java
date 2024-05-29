package com.politecnicomalaga.malakatrivialapp.model;

import java.util.ArrayList;
import java.util.List;

/*Esta clase es el JSON en sí*/
/*
 * Esta clase representa el modelo del juego para la aplicación Malaka Trivial.
 * Maneja la lógica del juego, incluyendo el seguimiento de puntos, la gestión de preguntas y el procesamiento de respuestas.
 */

public class Game {
    private int puntosTotales;
    private int temporizador;
    private List<Question> listaPreguntas;
    private int currentQuestion;
    private GameViewModel gameViewModel;

    public Game(String[] preguntas, List<String[]> respuestas) {
        currentQuestion = 0;
        listaPreguntas = new ArrayList<>();
        for(int i = 0; i < preguntas.length;i++){
            listaPreguntas.add(new Question(preguntas[i], respuestas.get(i)));
        }
        gameViewModel = GameViewModel.getInstance();
    }

    public int getPuntosTotales() {
        return puntosTotales;
    }

    public  void setPuntosTotales(int puntosTotales) {
        puntosTotales = puntosTotales;
    }

    public int getTemporizador() {
        return temporizador;
    }

    public void setTemporizador(int temporizador) {
        this.temporizador = temporizador;
    }

    public List<Question> getListaPreguntas() {
        return listaPreguntas;
    }
    
// Método para obtener la pregunta actual
    public Question getCurrentQuestion() {
        if (currentQuestion < listaPreguntas.size()) {
            return listaPreguntas.get(currentQuestion);
        } else {
            return null;
        }
    }

// Método para enviar una respuesta y comprobar si es correcta.
    public boolean submitAnswer(String answer) {
        String correctAnswer = listaPreguntas.get(currentQuestion).getCorrectAnswerString();
        currentQuestion++;
        if (correctAnswer.equals(answer)) {
            int time = gameViewModel.getTime();
            if(time >= 10){
                time = 10; // Limitar el tiempo a un máximo de 10
            }
            puntosTotales+= time*100;
            return true;
        }
        return false;
    }

// Método para comprobar si el juego ha terminado
    public boolean isGameOver() {
        return currentQuestion >= listaPreguntas.size();
    }

}

