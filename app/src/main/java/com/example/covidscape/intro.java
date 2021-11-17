package com.example.covidscape;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class intro extends AppCompatActivity {

    ImageView intro, logo;
    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        logo = findViewById(R.id.logo);
        intro = findViewById(R.id.intro);
        lottie = findViewById(R.id.lottie);
        //animation play
        intro.animate().translationY(-2000).setDuration(800).setStartDelay(4000);
        logo.animate().translationY(1400).setDuration(800).setStartDelay(4000);
        lottie.animate().translationY(1700).setDuration(800).setStartDelay(4000);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        },3000);


    }
}