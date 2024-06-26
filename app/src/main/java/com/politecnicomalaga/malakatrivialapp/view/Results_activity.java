package com.politecnicomalaga.malakatrivialapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.politecnicomalaga.malakatrivialapp.R;
import com.politecnicomalaga.malakatrivialapp.control.GameViewModel;

public class Results_activity extends AppCompatActivity {
    private GameViewModel gameViewModel;
    private TextView tvResults, tvMessage;
    private Button btnMenu;

    //Metodo onCreate para realizar la inicialización básica de la actividad.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_results);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        gameViewModel = GameViewModel.getInstance();

        tvResults = (TextView) findViewById(R.id.tvResults);
        tvMessage = (TextView) findViewById(R.id.tvMessage);
        btnMenu = (Button) findViewById(R.id.btnMenu);

        //Muestra los puntos en el viewModel.
        int score = gameViewModel.getScore();
        tvResults.setText(String.valueOf(score));

        //Muestra un mensaje según los puntos obtenidos
        if(score >= 3000){
            tvMessage.setText("¡Excelente!");
        }else if(score >= 2000){
            tvMessage.setText("Bien");
        }else if(score >= 1000){
            tvMessage.setText("Regular");
        }else if(score < 1000){
            tvMessage.setText("Tienes que mejorar");
        }

        //Boton para volver al menú principal.
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Results_activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
