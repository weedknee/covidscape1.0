package com.example.covidscape;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class sopOutdoor extends AppCompatActivity {
    ImageButton back2Homepage;
    private MediaPlayer mediaPlayerBgm;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sop_outdoor);

        //button sound effect for activity
        final MediaPlayer mediaplayer = MediaPlayer.create(this,R.raw.pop);

        mediaPlayerBgm = MediaPlayer.create(sopOutdoor.this,R.raw.overcooked2bgm);
        mediaPlayerBgm.setLooping(true);
        mediaPlayerBgm.start();

        back2Homepage = findViewById(R.id.xBtnNH);
        back2Homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change SOPChoiceActivity to Homepage java class
                mediaplayer.start();
                startActivity(new Intent(sopOutdoor.this, MainActivity.class));
            }
        });
    }


}
