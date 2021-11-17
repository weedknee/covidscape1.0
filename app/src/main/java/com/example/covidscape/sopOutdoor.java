package com.example.covidscape;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class sopOutdoor extends AppCompatActivity {
    ImageButton back2Homepage;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sop_outdoor);

        back2Homepage = findViewById(R.id.xBtnNH);

        back2Homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //change SOPChoiceActivity to Homepage java class
                startActivity(new Intent(sopOutdoor.this, MainActivity.class));
            }
        });
    }


}
