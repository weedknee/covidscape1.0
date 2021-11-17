package com.example.covidscape;

import android.content.Intent;
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

        back2Homepage = findViewById(R.id.xBtnH);

        back2Homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(sopIndoor.this, MainActivity.class));
            }
        });
    }

}
