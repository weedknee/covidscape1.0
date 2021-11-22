package com.example.covidscape;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

//Quiz menu screen
public class quiz extends AppCompatActivity {

    Button start, instruction, back;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        start = findViewById(R.id.startBtn);
        back = findViewById(R.id.backBtn);
        instruction = findViewById(R.id.intsructionBtn);

        //button sound effect for activity
        final MediaPlayer mediaplayer = MediaPlayer.create(this,R.raw.pop);

        //start Quiz activity
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start(); //button sound effect
                startActivity(new Intent(quiz.this,loadingQuiz.class));
            }
        });

        //back to home screen
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start(); //button sound effect
                startActivity(new Intent(quiz.this,MainActivity.class));
            }
        });

        //instruction button to pop up window
        instruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start(); //button sound effect
                openPopUpWindow();
            }
        });


    }

    private void openPopUpWindow() {
        Intent popupWindow = new Intent(quiz.this,pop.class);
        startActivity(popupWindow);
    }
}
