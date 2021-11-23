package com.example.covidscape;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

//intro Splash Screen
public class IntroActivity extends AppCompatActivity {

    ImageView intro, logo;
    LottieAnimationView lottie;
    MediaPlayer introSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        introSound = MediaPlayer.create(this,R.raw.chime);
        introSound.start(); //splash screen sound effect
        logo = findViewById(R.id.logo);
        intro = findViewById(R.id.intro);
        lottie = findViewById(R.id.lottie);

        //animation play
        intro.animate();
        logo.animate();
        lottie.animate();

        new Handler().postDelayed(new Runnable(){ //display login class after intro splash screen
            @Override
            public void run(){
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        },3200);

    }

    //intro sound effect end
    @Override
    protected void onPause() {
        super.onPause();
        introSound.release();
        finish();
    }
}