package com.example.covidscape;

import static com.example.covidscape.loadingQuiz.questionBank;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView question;
    Button[] options= new Button[4];

    List<QuizHandler> allQuestions;
    QuizHandler quizHandler;
    int index=0, currentScore=0, questionsAttempted=1;
    final int  TOTAL_QUESTIONS=5;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_start);

        question= findViewById(R.id.txtQuestion);
        options[0]= findViewById(R.id.btnOption1);
        options[1]= findViewById(R.id.btnOption2);
        options[2]= findViewById(R.id.btnOption3);
        options[3]= findViewById(R.id.btnOption4);

        progressBar= findViewById(R.id.progressBar2);
        allQuestions=questionBank;
        Collections.shuffle(allQuestions);
        quizHandler=allQuestions.get(index);
        nextQuestion();

        options[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(options[0]);
                if(questionsAttempted==TOTAL_QUESTIONS){
                    finishQuiz();
                }
                else{nextQuestion();}
            }
        });

        options[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(options[1]);
                if(questionsAttempted==TOTAL_QUESTIONS){
                    finishQuiz();
                }
                else{nextQuestion();}
            }
        });

        options[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(options[2]);
                if(questionsAttempted==TOTAL_QUESTIONS){
                    finishQuiz();
                }
                else{nextQuestion();}
            }
        });

        options[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(options[3]);
                if(questionsAttempted==TOTAL_QUESTIONS){
                    finishQuiz();
                }
                else{nextQuestion();}
            }
        });

    }

    private void nextQuestion() {

        if(options[0].isEnabled()) {
            question.setText(allQuestions.get(index).getQuestion());
            options[0].setText(allQuestions.get(index).getOp1());
            options[1].setText(allQuestions.get(index).getOp2());
            options[2].setText(allQuestions.get(index).getOp3());
            options[3].setText(allQuestions.get(index).getOp4());
        }

    }

    private void checkAnswer(Button b){
        questionsAttempted++;
        for (int i=0; i<options.length; i++){
            options[i].setClickable(false);
            if(options[i]!=b){
                options[i].setBackgroundColor(getResources().getColor(R.color.gray_disabled));
            }
        }
        String correctAnswer=allQuestions.get(index).getAnswer();
        if(b.getText().toString().trim().toLowerCase().equalsIgnoreCase(correctAnswer.trim())){
            b.setBackgroundColor(getResources().getColor(R.color.green_correct));
            currentScore++;
        }
        else{b.setBackgroundColor(getResources().getColor(R.color.red_wrong));}
        System.out.println(correctAnswer);

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

    private void finishQuiz(){
        Intent intent = new Intent(QuizActivity.this, ScoreActivity.class);
        intent.putExtra("score", currentScore);
        startActivity(intent);
    }
}
