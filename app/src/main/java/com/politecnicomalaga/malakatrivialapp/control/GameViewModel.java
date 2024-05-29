package com.politecnicomalaga.malakatrivialapp.control;

/*
 * GameViewModel
 * Clase intermediaría entre el modelo y la vista.
 * Hace de control entre la clase GameActivity y la clase Game.
 * Sigue el patrón de diseño singleton, para que solo tengamos una
 * instancia de esta clase en el proyecto completo.
 * */

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.politecnicomalaga.malakatrivialapp.model.Game;
import com.politecnicomalaga.malakatrivialapp.model.Question;

import java.util.List;

public class GameViewModel extends ViewModel {
    private int time;
    private Game game;
    private static GameViewModel thisGVM = null;
    private boolean questionAnswered;

    //Constructor Diseño: singleton
    private GameViewModel(){
        questionAnswered = false;
    }

    public static GameViewModel getInstance(){
        if(thisGVM == null)
            thisGVM = new GameViewModel();
        return thisGVM;
    }

    //Creamos la instancia del objeto de la clase Game
    public void setData(String[] preguntas, List<String[]> respuestas){
        this.game = new Game(preguntas, respuestas);
    }

    //Returna la pregunta actual
    public MutableLiveData<Question> getCurrentQuestion(){
        MutableLiveData<Question> currentQuestion = new MutableLiveData<>();
        //Obtiene la pregunta del objeto game
        //Setea el valor al objeto MutableLiveData
        currentQuestion.setValue(game.getCurrentQuestion());
        return currentQuestion;
    }

    public boolean isGameOver(){
        return game.isGameOver();
    }

    public boolean submitAnswer(String answer){
        if(!questionAnswered){
            questionAnswered = true;
            return game.submitAnswer(answer);
        }
        return false;
    }

    //Getters y setters

    public int getScore(){
        return game.getPuntosTotales();
    }

    public boolean isQuestionAnswered() {
        return questionAnswered;
    }

    public void setQuestionAnswered(boolean questionAnswered) {
        this.questionAnswered = questionAnswered;
    }
    public int getTime(){
        return time;
    }
    public void setTimer(int time){
        this.time = time;
    }

    public void reset(){
        this.questionAnswered = false;
    }
}
