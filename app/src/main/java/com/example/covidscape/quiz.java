package com.example.covidscape;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class quiz extends AppCompatActivity {

    Button start, leaderboard, instruction, back ;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        start = findViewById(R.id.startBtn);
        back = findViewById(R.id.backBtn);
        instruction = findViewById(R.id.intsructionBtn);

        //button sound effect for activity
        final MediaPlayer mediaplayer = MediaPlayer.create(this,R.raw.pop);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start();
                startActivity(new Intent(quiz.this,loadingQuiz.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start();
                startActivity(new Intent(quiz.this,MainActivity.class));
            }
        });

        instruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start();
                openPopUpWindow();
            }
        });


    }

    private void openPopUpWindow() {
        Intent popupWindow = new Intent(quiz.this,pop.class);
        startActivity(popupWindow);
    }
}
