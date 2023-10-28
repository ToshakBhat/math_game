package com.example.math_game;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;
import java.util.Random;

public class GamePage extends AppCompatActivity {
    TextView question;
    TextView score;
    TextView life;
    TextView time;
    EditText answer;
    TextView head;
    Button ok;
    Button next;
    Random random = new Random();//Random object
    int num1;
    int num2;
    int userAnswer;
    int realAnswer;
    int userScore = 0;
    int userLife = 3;

    CountDownTimer timer;
    private static final long START_TIMER_IN_MILIS = 10000; //Final values must be written in capital letters
    Boolean timer_running;
    long time_left_in_milis = START_TIMER_IN_MILIS;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        value = intent.getStringExtra("val");



        score = findViewById(R.id.score);
        life = findViewById(R.id.textViewLife);
        time = findViewById(R.id.textViewTime);
        question = findViewById(R.id.textViewQuestion);
        answer = findViewById(R.id.editTextText);
        ok = findViewById(R.id.button2);
        next = findViewById(R.id.button3);

        head = findViewById(R.id.head);//Heading code
        head.setText(value);

        score.setText("0");
        life.setText(""+userLife);

        gameContinue(); //To set the question

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAnswer = Integer.valueOf(answer.getText().toString());
                pauseTimer();
                if (userAnswer == realAnswer){
                    userScore += 10;
                    question.setText("Wohoo!! Correct Answer");
                    score.setText(""+userScore);
                    answer.setVisibility(View.INVISIBLE);
                    ok.setVisibility(View.INVISIBLE);
                    answer.setText("");
                    resetTimer();
                }else{
                    //String val = "3";
                    //userLife = Integer.parseInt(val);
                    userLife -= 1;
                    question.setText("Oops!! Wrong Answer");
                    life.setText(""+userLife);
                    answer.setVisibility(View.INVISIBLE);
                    ok.setVisibility(View.INVISIBLE);
                    answer.setText("");
                    resetTimer();

                }

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setVisibility(View.VISIBLE);
                ok.setVisibility(View.VISIBLE);
                answer.setText("");
                resetTimer();

                if (userLife <= 0){
                    Toast.makeText(getApplicationContext(),"Game Over",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(GamePage.this,Result.class);
                    intent.putExtra("score",userScore);
                    startActivity(intent);
                    finish(); //This stops the previous activity
                }else{
                    gameContinue();
                }
            }
        });
    }

    public void gameContinue(){
        if (value.equals("Addition")) {
            num1 = random.nextInt(100);
            num2 = random.nextInt(100);

            realAnswer = num1 + num2;
            question.setText(num1 + " + " + num2);
            startTimer(); //Now start the clock
        }
        else if(value.equals("Subtraction")){
            num1 = random.nextInt(100);
            num2 = random.nextInt(100);

            realAnswer = num1 - num2;
            question.setText(num1 + " - " + num2);
            startTimer(); //Now start the clock
        }
        else if(value.equals("Multiplication")){
            num1 = random.nextInt(13);
            num2 = random.nextInt(13);

            realAnswer = num1 * num2;
            question.setText(num1 + " * " + num2);
            startTimer(); //Now start the clock
        }
        else if(value.equals("Division")){
            num1 = random.nextInt(13);
            num2 = random.nextInt(13);

            realAnswer = num1 % num2;
            question.setText(num1 + " / " + num2);
            startTimer(); //Now start the clock
        }
    }

    public void startTimer(){
        timer = new CountDownTimer(time_left_in_milis,1000) { //work for 60 seconds
            @Override
            public void onTick(long l) { //What you want to do when every one second finish
                time_left_in_milis = l;
                updateText();
            }

            @Override
            public void onFinish() { //What you want to do when time finishes.
                timer_running = false;
                pauseTimer();
                resetTimer();
                updateText();
                userLife -= 1;
                question.setText("Sorry , Time is up !");
                life.setText(""+userLife);
                answer.setVisibility(View.INVISIBLE);
                ok.setVisibility(View.INVISIBLE);
                answer.setText("");
            }
        }.start();
        timer_running = true;
    }
    public void updateText(){
        int second = (int) (time_left_in_milis/1000)%60;
        String time_left = String.format(Locale.getDefault(),"%02d",second);
        time.setText(time_left);
    }
    public void pauseTimer(){
        timer.cancel();
        timer_running = false;
    }
    public void resetTimer(){
        time_left_in_milis = START_TIMER_IN_MILIS;
        updateText();


    }


}
