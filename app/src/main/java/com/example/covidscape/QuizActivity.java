package com.example.covidscape;

import static com.example.covidscape.LoadQuizActivity.questionBank;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

//Quiz game based on SOP guides
public class QuizActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView question;
    Button[] options= new Button[4];

    List<QuizHandler> allQuestions;
    QuizHandler quizHandler;
    int index=0, currentScore=0, questionsAttempted=1;
    final int  TOTAL_QUESTIONS=5;
    private MediaPlayer mediaPlayer, bgmPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_start);

        question= findViewById(R.id.txtQuestion);
        options[0]= findViewById(R.id.btnOption1);
        options[1]= findViewById(R.id.btnOption2);
        options[2]= findViewById(R.id.btnOption3);
        options[3]= findViewById(R.id.btnOption4);

        //background music & sound effect
        mediaPlayer = MediaPlayer.create(this,R.raw.pop);
        bgmPlayer = MediaPlayer.create(this,R.raw.nekoatsumebgm);
        bgmPlayer.setVolume(20,20);
        bgmPlayer.setLooping(true);
        bgmPlayer.start();

        progressBar= findViewById(R.id.progressBar2);
        //store questions from questionBank in LoadQuizActivity
        allQuestions=questionBank;
        //shuffle allQuestions to randomize the question list
        Collections.shuffle(allQuestions);
        //get first question
        quizHandler=allQuestions.get(index);
        nextQuestion();

        options[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start(); //button sound effect
                checkAnswer(options[0]);
                //check if questions attempted reached the limit
                if(questionsAttempted>TOTAL_QUESTIONS){
                    finishQuiz();
                }
                else{nextQuestion();}
            }
        });

        options[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start(); //button sound effect
                checkAnswer(options[1]);
                if(questionsAttempted>TOTAL_QUESTIONS){
                    finishQuiz();
                }
                else{nextQuestion();}
            }
        });

        options[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start(); //button sound effect
                checkAnswer(options[2]);
                if(questionsAttempted>TOTAL_QUESTIONS){
                    finishQuiz();
                }
                else{nextQuestion();}
            }
        });

        options[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start(); //button sound effect
                checkAnswer(options[3]);
                if(questionsAttempted>TOTAL_QUESTIONS){
                    finishQuiz();
                }
                else{nextQuestion();}
            }
        });

    }

    private void nextQuestion() {
        //changes text to next question
        if(options[0].isEnabled()) {
            question.setText(allQuestions.get(index).getQuestion());
            options[0].setText(allQuestions.get(index).getOp1());
            options[1].setText(allQuestions.get(index).getOp2());
            options[2].setText(allQuestions.get(index).getOp3());
            options[3].setText(allQuestions.get(index).getOp4());
        }

    }

    private void checkAnswer(Button b){
        //increment questions attempted
        questionsAttempted++;
        //users only have 1 attempt to answer
        //disable answer option buttons
        for (int i=0; i<options.length; i++){
            options[i].setClickable(false);
            if(options[i]!=b){
                //change colors of buttons that weren't selected to gray
                options[i].setBackgroundColor(getResources().getColor(R.color.gray_disabled));
            }
        }
        //get answer from question list
        String correctAnswer=allQuestions.get(index).getAnswer();
        //if answer is correct, button changes to green and score increments
        if(b.getText().toString().trim().toLowerCase().equalsIgnoreCase(correctAnswer.trim())){
            b.setBackgroundColor(getResources().getColor(R.color.green_correct));
            currentScore++;
        }
        //else, wrong button selected changes to red
        else{b.setBackgroundColor(getResources().getColor(R.color.red_wrong));}
        System.out.println(correctAnswer);

        //as the color changes too fast, we delay 200 milisecond so the user can
        // see the color feedback before it changes back to default yellow
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i=0; i<options.length; i++){
                    options[i].setBackgroundColor(getResources().getColor(R.color.yellow));
                    options[i].setClickable(true);
                }
                progressBar.setProgress(questionsAttempted);
            }
        },200);
        if(index<TOTAL_QUESTIONS-1){
            index++;
        }
    }

    //function called to start score activity when
    //quiz if completed
    private void finishQuiz(){
        Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
        intent.putExtra("score", currentScore);
        startActivity(intent);
    }

    //bgm play
    @Override
    public void onResume() {
        super.onResume();
        bgmPlayer.start();
    }

    //bgm pause
    @Override
    protected void onPause() {
        super.onPause();
        bgmPlayer.stop();
    }

    //bgm end
    @Override
    public void onDestroy() {
        super.onDestroy();
        bgmPlayer.stop();
        bgmPlayer.release();

    }
}
