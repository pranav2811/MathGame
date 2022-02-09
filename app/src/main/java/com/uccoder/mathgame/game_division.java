package com.uccoder.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;


public class game_division extends AppCompatActivity {
    TextView score;
    TextView life;
    TextView time;

    TextView question;
    EditText answer;

    Button ok;
    Button next;
    Random random = new Random();
    int number1;
    int number2;

    int userAnswer;
    int realAnswer;

    int userScore = 0;
    int userLife = 3;

    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_division);


        score = findViewById(R.id.textViewScoreDiv);
        life = findViewById(R.id.textViewLifeDiv);
        time = findViewById(R.id.textViewTimeDiv);
        question = findViewById(R.id.textViewQuestionDiv);
        answer = findViewById(R.id.editTextAnswerDiv);
        ok = findViewById(R.id.buttonOkDiv);
        next = findViewById(R.id.buttonNextDiv);



    }
}