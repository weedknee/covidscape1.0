package com.example.covidscape;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class sopIndoor extends AppCompatActivity {
    ImageButton back2Homepage;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sop_indoor);

        //button sound effect for activity
        final MediaPlayer mediaplayer = MediaPlayer.create(this,R.raw.pop);

        back2Homepage = findViewById(R.id.xBtnH);

        back2Homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaplayer.start();
                startActivity(new Intent(sopIndoor.this, MainActivity.class));
            }
        });
    }

}
