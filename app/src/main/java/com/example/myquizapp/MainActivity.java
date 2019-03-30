package com.example.myquizapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
private TextView scoreTextview, timeTextview,questionTextview;
private Button button1,button2,button3,button4;
private TextView resultTextview;
private Button playButton;
private LinearLayout linearLayout;
private GridLayout gridLayout;
private int numberofquestion;
private int correctqustion;
private int locationofCorrectAnswer;

ArrayList<Integer> answers=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initviews();
    }
    private void initviews(){
        scoreTextview = findViewById(R.id.score_text_view);
        timeTextview= findViewById(R.id.time_text_view);
        questionTextview=findViewById(R.id.question_text_view);
        button1=findViewById(R.id.button_1);
        button2= findViewById(R.id.button_2);
        button3= findViewById(R.id.button_3);
        button4= findViewById(R.id.button_4);
        resultTextview=findViewById(R.id.answer_text_view);
        playButton= findViewById(R.id.play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starQuiz();
            }
        });

        linearLayout=findViewById(R.id.linear_layout);
        gridLayout=findViewById(R.id.grid_layout);
    }
    private void starQuiz(){
     timeTextview.setText("30s");
     correctqustion=0;
     numberofquestion=0;
     gridLayout.setVisibility(View.VISIBLE);
     linearLayout.setVisibility(View.VISIBLE);
     scoreTextview.setVisibility(View.VISIBLE);
     scoreTextview.setText("0/0");

     new CountDownTimer(30100,1000){
         @Override
         public void onTick(long millisUntilFinished) {
             timeTextview.setText(String.valueOf(millisUntilFinished/1000)+"s");
         }

         @Override
         public void onFinish() {
             timeTextview.setText("0s");
             gridLayout.setVisibility(View.INVISIBLE);
             linearLayout.setVisibility(View.INVISIBLE);
             playButton.setVisibility(View.VISIBLE);
             playButton.setText("Play again");
             showscore();
         }
     }.start();
     createquestion();
    }
    private void createquestion(){
        answers.clear();
        Random random = new Random();

        int a=random.nextInt(21);
        int b=random.nextInt(21);

        locationofCorrectAnswer=random.nextInt(4);
        for(int i=0;i<4;i++){
            if(i==locationofCorrectAnswer){
                answers.add(a+b);
            }
            else{
                int wronganswer=random.nextInt(41);
                while (wronganswer==a+b){
                    wronganswer=random.nextInt(41);
                }
                answers.add(wronganswer);
            }
        }
        String question=String.valueOf(a)+" + "+String.valueOf(b);
        questionTextview.setText(question);

        button1.setText(String.valueOf(answers.get(0)));
        button2.setText(String.valueOf(answers.get(1)));
        button3.setText(String.valueOf(answers.get(2)));
        button4.setText(String.valueOf(answers.get(3)));
    }
    public void submit(View view){
        if(view.getTag().toString().equals(String.valueOf(locationofCorrectAnswer))){
            correctqustion++;
            resultTextview.setText("Correct Answer");
        }
        else {

            resultTextview.setText("Wrong answer");}
        numberofquestion++;
            scoreTextview.setText(String.valueOf(correctqustion)+"/"+String.valueOf(numberofquestion));
            createquestion();

    }
    private void showscore(){
        resultTextview.setText(String.valueOf(correctqustion)+"/"+String.valueOf(numberofquestion));

    }
}
