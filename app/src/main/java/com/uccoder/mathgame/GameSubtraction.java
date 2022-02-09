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

public class GameSubtraction extends AppCompatActivity {
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


    private static final long START_TIMER_IN_MILIS =60000;
    Boolean timer_running;
    long time_left_in_milis = START_TIMER_IN_MILIS;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_subtraction);

        score = findViewById(R.id.textViewScoreDiv);
        life = findViewById(R.id.textViewLifeDiv);
        time = findViewById(R.id.textViewTimeDiv);
        question = findViewById(R.id.textViewQuestionDiv);
        answer = findViewById(R.id.editTextAnswerDiv);
        ok = findViewById(R.id.buttonOkDiv);
        next = findViewById(R.id.buttonNextDiv);

        gameContinue();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setText("");
                gameContinue();
                resetTimer();

                if(userLife<=0){
                    Toast.makeText(getApplicationContext(), "Sorry Game Over!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(GameSubtraction.this,Result.class);
                    intent.putExtra("score",userScore);
                    startActivity(intent);
                    finish();

                }
                else
                {
                gameContinue();
                }
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(userAnswer == realAnswer){
                    userScore = userScore+10;
                    score.setText(""+userScore);
                    question.setText("Congratulations Your answer is true!");
                }
                else{
                    userLife = userLife-1;
                    life.setText(""+userLife);
                    question.setText("Sorry Your Answer is Wrong!");
                }
            }
        });




    }
    public void gameContinue(){
        number1 = random.nextInt(1000);
        number2 = random.nextInt(1000);
        question.setText(number1 + "-" + number2);
        realAnswer =  number1-number2;
        startTimer();
    }
    public void startTimer()
    {
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
                userLife = userLife-1;
                life.setText(""+userLife);
                question.setText("Sorry Time is Up!");

            }
        }.start();
        timer_running = true;

    }
    public void updateText()
    {
        int second = (int)(time_left_in_milis/1000)%60;
        String time_left = String.format(Locale.getDefault(),"%02d",second);
        time.setText(time_left);

    }
    public void pauseTimer()
    {
        timer.cancel();
        timer_running = false;


    }
    public void resetTimer()
    {
        time_left_in_milis = START_TIMER_IN_MILIS;
        updateText();

    }
}