package com.politecnicomalaga.malakatrivialapp.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.politecnicomalaga.malakatrivialapp.R;
import com.politecnicomalaga.malakatrivialapp.control.GameViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static String player;
    private Button btnStart, btnNosotros;
    private EditText etPlayerName;
    private Activity thisAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        thisAct = this;

        //Instaciación de los botones y editText para introducir el nombre del jugador
        btnStart = (Button) findViewById(R.id.btn_start);
        btnNosotros = (Button) findViewById(R.id.btnConocenos);
        etPlayerName = (EditText) findViewById(R.id.etPlayerName);


        //Botón que comienza el juego
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Comprueba el nombre del usuario
                player = etPlayerName.getText().toString();
                //Si el nombre está en null, muestra un mensaje de error
                if (player == null || player.trim().isEmpty()) {
                    Toast mensajeError = Toast.makeText(thisAct, "Debe introducir un nombre", Toast.LENGTH_LONG);
                    mensajeError.show();
                }else{
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    startActivity(intent);
                }
            }
        });

        //Botón que nos lleva al apartado de conócenos
        btnNosotros.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, ConocenosActivity.class);
                startActivity(intent);
            }
        });

    }

    public static String getPlayer(){
        return player;
    }
}
