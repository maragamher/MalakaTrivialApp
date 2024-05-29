package com.politecnicomalaga.malakatrivialapp.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.politecnicomalaga.malakatrivialapp.R;
import com.politecnicomalaga.malakatrivialapp.control.GameViewModel;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    private TextView tvPregunta, tvTiempo, tvMessage;
    private Button btnResp1, btnResp2, btnResp3, btnResp4, btnSigPreg;
    private WebView wvImage;
    private GameViewModel gameViewModel;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Inicializamos todos los botones
        tvPregunta = (TextView) findViewById(R.id.tvPregunta);
        tvTiempo = (TextView) findViewById(R.id.tvTiempo);
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        btnResp1 = (Button) findViewById(R.id.btnResp1);
        btnResp2 = (Button) findViewById(R.id.btnResp2);
        btnResp3 = (Button) findViewById(R.id.btnResp3);
        btnResp4 = (Button) findViewById(R.id.btnResp4);
        btnSigPreg = (Button) findViewById(R.id.btnNextPreg);
        wvImage = (WebView) findViewById(R.id.ivImage);

        tvMessage.setText(tvMessage.getText() + MainActivity.getPlayer());

        //Obtenemos el valor String de las preguntas y las respuestas del archivo strings.xml
        String[] preguntas = getResources().getStringArray(R.array.lista_preguntas);
        String[] resp_preg_0 = getResources().getStringArray(R.array.respuestas_pregunta0);
        String[] resp_preg_1 = getResources().getStringArray(R.array.respuestas_pregunta1);
        String[] resp_preg_2 = getResources().getStringArray(R.array.respuestas_pregunta2);
        String[] resp_preg_3 = getResources().getStringArray(R.array.respuestas_pregunta3);
        String[] url_imagenes = getResources().getStringArray(R.array.url_imagenes);
        //Los arrays de respuestas los metemos en una ArrayList para pasarselo al GameViewModel
        List<String[]> respuestas = new ArrayList<>();
        respuestas.add(resp_preg_0);
        respuestas.add(resp_preg_1);
        respuestas.add(resp_preg_2);
        respuestas.add(resp_preg_3);

        //Hemos apostado por hacer GameViewModel singleton
        //Para que sea más cómodo, guardamos la instancia del GameViewModel en una variable
        gameViewModel = GameViewModel.getInstance();
        //Reiniciamos por si venimos de una partida anterior no haya datos guardados
        gameViewModel.reset();
        //Le pasamos las preguntas y respuestas
        gameViewModel.setData(preguntas, respuestas, url_imagenes);

        //Cargamos los datos en la pantalla
        loadData();

        //Seteamos los listener de todos los botones

        btnResp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswer(btnResp1);
            }
        });

        btnResp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswer(btnResp2);
            }
        });

        btnResp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswer(btnResp3);
            }
        });

        btnResp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitAnswer(btnResp4);
            }
        });

        btnSigPreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Esto solo se ejecutará después de que la pregunta haya sido respondida
                if(!gameViewModel.isQuestionAnswered()) return;

                //Tenemos que comprobar que sigan habiendo preguntas
                if (!gameViewModel.isGameOver()) {
                    //Volvemos a cargar los nuevos datos por pantalla
                    loadData();
                    //Como pasamos a la siguiente pregunta, seteamos el booleano a false
                    gameViewModel.setQuestionAnswered(false);
                    //Cambiamos el color de los botones
                    resetButtonColor();
                } else {
                    //Si el juego ha terminado, es decir no hay más preguntas, cambiamos a la pantalla de resultados
                    Intent intent = new Intent(GameActivity.this, Results_activity.class);
                    startActivity(intent);
                }

            }

        });

    }
    private void submitAnswer(Button btn){
        //Comprobamos que no se haya contestado antes, porque una pregunta no se puede contestar dos veces
        if(gameViewModel.isQuestionAnswered()) return;

        //Recoge cual ha sido la respuesta seleccionada
        String respSelect = (String) btn.getText();
        //Guardamos cuando ha tardado en responder la pregunta y asi dar los puntos en base al tiempo
        gameViewModel.setTimer(Integer.valueOf((String) tvTiempo.getText()));
        if (gameViewModel.submitAnswer(respSelect)) {
            btn.setBackgroundColor(Color.GREEN);
        } else {
            btn.setBackgroundColor(Color.RED);
        }
        //Paramos el temporizador
        timer.cancel();
    }
    private void loadData(){
        gameViewModel.getCurrentQuestion().observe(this, Question -> {

            if(Question != null){
                tvPregunta.setText(Question.getPregunta());
                btnResp1.setText(Question.getListaRespuestas().get(0).getRespuesta());
                btnResp2.setText(Question.getListaRespuestas().get(1).getRespuesta());
                btnResp3.setText(Question.getListaRespuestas().get(2).getRespuesta());
                btnResp4.setText(Question.getListaRespuestas().get(3).getRespuesta());
                String html = "<html><body><img src=\"" + Question.getUrlImage() + "\" width=\"75%\" height=\"100%\"\"/></body></html>";
                wvImage.loadData(html, "text/html", null);
            }

        });

        //Comienza el tiempo atrás de nuevo
        startTime();
    }

    private void resetButtonColor(){
        btnResp1.setBackgroundColor(Color.rgb(103, 58, 183));
        btnResp2.setBackgroundColor(Color.rgb(103, 58, 183));
        btnResp3.setBackgroundColor(Color.rgb(103, 58, 183));
        btnResp4.setBackgroundColor(Color.rgb(103, 58, 183));
    }

    private void startTime(){
        int tiempoTotalMilisegundos = 15 * 1000;
        timer = new CountDownTimer(tiempoTotalMilisegundos, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int tiempoSegundos = (int) (millisUntilFinished/1000);
                tvTiempo.setText(String.valueOf(tiempoSegundos));
            }

            @Override
            public void onFinish() {
                //Cuando se termine el tiempo ponemos como si la pregunta hubiera sido contestada,
                //aunque realmente no la contesten, así no se podrá contestar fuera de tiempo
                gameViewModel.setQuestionAnswered(true);
            }
        };

        timer.start();

    }
}
