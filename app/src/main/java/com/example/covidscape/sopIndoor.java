package com.example.covidscape;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class sopIndoor extends AppCompatActivity {
    ImageButton back2Homepage;
    private MediaPlayer mediaPlayer, bgmPlayer;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sop_indoor);

        //button sound effect for activity
        mediaPlayer = MediaPlayer.create(this,R.raw.pop);
        bgmPlayer =MediaPlayer.create(this,R.raw.home);

        back2Homepage = findViewById(R.id.xBtnH);

        back2Homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                startActivity(new Intent(sopIndoor.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        bgmPlayer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bgmPlayer.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bgmPlayer.stop();
        bgmPlayer.release();

    }

}
