package com.uccoder.mathgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class GameMultiplication extends AppCompatActivity {
    TextView score;
    TextView life;
    TextView time;

    TextView question;
    EditText answer;

    Button ok;
    Button next;
    Button back;

    Random random = new Random();
    int number1;
    int number2;

    int userAnswer;
    int realAnswer;

    int userScore = 0;
    int userLife = 3;

    CountDownTimer timer;

    private static final long START_TIMER_IN_MILIS =60000;
    Boolean timer_running;
    long time_left_in_milis = START_TIMER_IN_MILIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_multiplication);

        score = findViewById(R.id.textViewScoreMulti);
        life = findViewById(R.id.textViewLifeMulti);
        time = findViewById(R.id.textViewTimeMulti);
        question = findViewById(R.id.textViewQuestionMulti);
        answer = findViewById(R.id.editTextAnswerMulti);

        ok = findViewById(R.id.buttonOkMulti);
        next = findViewById(R.id.buttonNextMulti);
        back = findViewById(R.id.buttonMultiBack);

        gameContinue();


        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userAnswer == realAnswer){
                    userScore = userScore + 1;
                    score.setText(""+userScore);
                    question.setText("Congratulations Your Answer is Correct");
                }
                else{
                    userLife = userLife-1;
                    life.setText(""+userLife);
                    question.setText("Sorry Your Answer is Wrong");
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userLife <=0){
                    Toast.makeText(getApplicationContext(), "Sorry Game Over!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),Result.class);
                    intent.putExtra("score",userScore);
                    startActivity(intent);
                    finish();

                }
                else{
                    gameContinue();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    public void gameContinue(){
        number1 = random.nextInt(100);
        number2 = random.nextInt(100);
        question.setText(number1 + "x" + number2);
        realAnswer = number1*number2;
        startTimer();
    }

    public void startTimer() {
        timer = new CountDownTimer(time_left_in_milis,1000) {
            @Override
            public void onTick(long l) {
                time_left_in_milis = l;
                updateText();

            }

            @Override
            public void onFinish() {
                timer_running = false;
                pauseTimer();
                resetTimer();
                updateText();
                userLife = userLife -1;
                life.setText(""+userLife);
                question.setText("Sorry Time is up!");

            }
        }.start();
        timer_running = true;
    }
    public void pauseTimer(){
        timer.cancel();
        timer_running = false;


    }
    public void resetTimer(){
        time_left_in_milis = START_TIMER_IN_MILIS;
        updateText();

    }
    public void updateText(){
        int second = (int)(time_left_in_milis/1000)%60;
        String time_left = String.format(Locale.getDefault(),"%02d",second);
        time.setText(time_left);

    }
}