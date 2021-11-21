package com.example.covidscape;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class forgotPassword extends AppCompatActivity {

    private EditText emailEditTxt;
    private Button resetBtn;
    private ImageButton backBtn;
    final MediaPlayer mediaplayer = MediaPlayer.create(this,R.raw.pop);

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        emailEditTxt = findViewById(R.id.forgotPassEmail);
        resetBtn = findViewById(R.id.resetPassBtn);

        auth = FirebaseAuth.getInstance();


        backBtn = findViewById(R.id.forgotPassBackBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start();
                startActivity(new Intent(forgotPassword.this,login.class));
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start();
                resetPassword();
                startActivity(new Intent(forgotPassword.this,login.class));
            }
        });
    }

    private void resetPassword() {
        String eml = emailEditTxt.getText().toString().trim();

        if(eml.isEmpty()){
            emailEditTxt.setError("Email is required!");
            emailEditTxt.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(eml).matches()) {
            emailEditTxt.setError("Please enter valid Email!");
            emailEditTxt.requestFocus();
            return;
        }
            auth.sendPasswordResetEmail(eml).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(forgotPassword.this,"Check your email to reset Password!", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(forgotPassword.this,"Try again! Invalid Email Entered!", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }

