package com.example.carloscamacho.parcial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PreguntaActivity extends Activity {

    private String json;
    private Quiz quiz;
    private Button[] answers;
    private TextView questionText;
    private int current;
    private int maxQuestion;
    private int score;
    private TextView scoreText;
    private LinearLayout list;
    private TextView numberQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta);

        json = getIntent().getStringExtra("json");
        quiz = new Gson().fromJson(json, Quiz.class);

        questionText = findViewById(R.id.question_text);
        scoreText = findViewById(R.id.score);
        list = findViewById(R.id.question_layout);
        numberQuestions = findViewById(R.id.number_question);

        current = 0;
        score = 0;
        maxQuestion = quiz.results.length;
        setQuestion(0);



        //questionText.setText(quiz.response_code + " FUNCO");
        questionText.setText(quiz.results[0].question);

    }

    private void incorrect() {
        Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
    }

    private void correct() {
        Toast.makeText(this, "Correct", Toast.LENGTH_SHORT ).show();
        updateScore();
    }

    private void updateScore() {
        score += 10;
        scoreText.setText(score + "");
    }

    public void setQuestion(int index) {
        if(index == quiz.results.length){
            Intent intent = new Intent(this, Result.class);
            intent.putExtra("score", score);
            intent.putExtra("size", quiz.results.length);
            startActivity(intent);
            finish();
            return;
        }
        numberQuestions.setText((index + 1) + "");
        createButton(quiz.results[index].incorrect_answers.length + 1);
        ArrayList<String> q = new ArrayList<>(4);
        q.add(quiz.results[index].correct_answer);
        for(String s: quiz.results[index].incorrect_answers)
            q.add(s);

        Collections.shuffle(q);

        for (int i = 0; i < q.size(); i++)
            answers[i].setText(q.get(i));
        questionText.setText(quiz.results[index].question);
    }

    private void createButton(int n) {
        list.removeAllViews();
        answers = new Button[n];
        for(int i = 0; i < n; i++){
            answers[i] = new Button(this);
            answers[i].setOnClickListener(v ->{
                    if(((Button) v).getText().equals(quiz.results[current].correct_answer))
                        correct();
                    else
                        incorrect();
                    setQuestion(++current);
                });
            list.addView(answers[i]);
        }
    }
}
