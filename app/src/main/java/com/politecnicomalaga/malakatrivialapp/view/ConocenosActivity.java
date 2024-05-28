package com.politecnicomalaga.trivialapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.politecnicomalaga.trivialapp.R;
import com.politecnicomalaga.trivialapp.control.GameRVAdapter;

import java.util.ArrayList;
import java.util.List;

public class ConocenosActivity extends AppCompatActivity {

    private List<String[]> nosotros;
    private Button btnVolverMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conocenos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Array tipo String que guarda el nombre y la descripción de cado uno de nosotros 
        String[] pablo = {"PABLO DEL PINO PAULANO","Desde Jaén tenemos a Pablo, un desarrollador que se encuentra en pleno aprendizaje."};
        String[] mara = {"MARA GAMBERO","Recién salida de la cantera malagueña tenemos a una auténtica lider la cual puede con todo."};
        String[] alvaro = {"ÁLVARO MILLÁN","Chaval muy joven que siempre está trabajando y avanzando en su desarrollo profesional."};
        String[] daniel = {"DANIEL SÁNCHEZ","Aquí nos encontramos con la veterania del grupo (que no viejo), aporta una experiencia muy valiosa."};
        String[] sergio = {"SERGIO SORIANO",""};

        //Creo el ArrayList
        nosotros = new ArrayList<>();
        //Agrego nuestra información al ArrayList
        nosotros.add(pablo);
        nosotros.add(mara);
        nosotros.add(alvaro);
        nosotros.add(daniel);
        nosotros.add(sergio);

        RecyclerView mRecyclerView = findViewById(R.id.rvNosotros);
        // Creamos un adapter con un enlace a la activity y a los datos a usar
        GameRVAdapter mAdapter = new GameRVAdapter(this, nosotros);
        // Conectamos el adapter y el RV
        mRecyclerView.setAdapter(mAdapter);
        // Asignamos al RV un tipo de layout manager por defecto: típicamente el LinearLayoutManager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        //Instancio un botón que nos permitirá volver al menú de inicio
        btnVolverMenu = (Button) findViewById(R.id.btnVolverMenu);
        btnVolverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConocenosActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
