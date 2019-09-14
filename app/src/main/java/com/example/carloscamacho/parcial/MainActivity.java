package com.example.carloscamacho.parcial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private EditText jsonText;
    private Button startButton;

    private String json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonText = findViewById(R.id.json_text);
        startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(v -> {
            json = jsonText.getText().toString();
            Gson gson = new Gson();
            Log.i("CARLOS", json);
            if(json == null || json.length() == 0){
                Toast.makeText(this, "No hay nada", Toast.LENGTH_SHORT).show();
                return;
            }
            try {
                Quiz quiz = gson.fromJson(json, Quiz.class);
        }
            catch (Exception e){
                Log.i("CARLOS", e.getMessage());
            Toast.makeText(this, "Json mas estructurado", Toast.LENGTH_SHORT).show();
            return;
        }

            Intent intent = new Intent(this, PreguntaActivity.class);
            intent.putExtra("json", json);

            startActivity(intent);
            Log.i("TALLER", json);
        });

    }
}
