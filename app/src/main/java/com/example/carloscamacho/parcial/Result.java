package com.example.carloscamacho.parcial;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Result extends Activity {

    private TextView correct;
    private TextView incorrect;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        int score = getIntent().getIntExtra("score", 0);
        int n = getIntent().getIntExtra("size", 0);

        correct = findViewById(R.id.correct_score);
        incorrect = findViewById(R.id.incorrect_score);

        correct.setText((score / 10) + "");
        incorrect.setText((n - score / 10)+ "");

        button = findViewById(R.id.restart);

        button.setOnClickListener(v -> finish());
    }
}
