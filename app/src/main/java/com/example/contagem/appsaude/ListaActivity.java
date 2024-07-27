package com.example.contagem.appsaude;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ListaActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
    }

    void goAgua(View view){
        intent = new Intent(this, AguaActivity.class);
        startActivity(intent);
    }

    void goExercicios(View view){
        intent = new Intent(this, ExercicioActivity.class);
        startActivity(intent);
    }
    void goIMC(View view){
        intent = new Intent(this, IMCActivity.class);
        startActivity(intent);

    }
}
