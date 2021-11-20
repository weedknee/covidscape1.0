package com.example.covidscape;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class ScoreActivity extends AppCompatActivity {
    CircularProgressBar circularProgressBar;
    TextView txtScore;
    private Button back;
    int score;
    final int TOTAL_QUESTIONS=5;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);

        //button sound effect for activity
        final MediaPlayer mediaplayer = MediaPlayer.create(this,R.raw.pop);


        score=getIntent().getIntExtra("score",0);

        circularProgressBar= (CircularProgressBar) findViewById(R.id.circularProgressBar);
        txtScore= (TextView) findViewById(R.id.txtScore);
        back = findViewById(R.id.backBtnQuiz);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start();
                startActivity(new Intent(ScoreActivity.this,quiz.class));
            }
        });

        circularProgressBar.setProgress(score);
        txtScore.setText(score+"/"+TOTAL_QUESTIONS);

    }
}
