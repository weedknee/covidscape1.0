package com.example.covidscape;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;

//Quiz Instruction pop up window activity
public class InstructionActivity extends AppCompatActivity {

    //pop up window for instruction in Quiz activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instruction);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //set size of pop up window
        getWindow().setLayout((int)(width*.9), (int)(height*.4));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        getWindow().setAttributes(params);

    }
}
