package com.example.covidscape;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
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

//forgot password to reset password via email activity
public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText emailEditTxt;
    private Button resetBtn;
    private ImageButton backBtn;
    private MediaPlayer mediaplayer;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        emailEditTxt = findViewById(R.id.forgotPassEmail);
        resetBtn = findViewById(R.id.resetPassBtn);
        backBtn = findViewById(R.id.forgotPassBackBtn);

        auth = FirebaseAuth.getInstance();

        mediaplayer = MediaPlayer.create(this,R.raw.pop);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start(); //button sound effect
                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class)); //back to login activity
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start(); //button sound effect
                resetPassword(); //function to reset password
                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class)); //back to login activity
            }
        });
    }

    private void resetPassword() {
        String eml = emailEditTxt.getText().toString().trim(); //get email from user

        if(eml.isEmpty()){ //if input is empty
            emailEditTxt.setError("Email is required!"); //error message
            emailEditTxt.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(eml).matches()) { //if email doesn't match
            emailEditTxt.setError("Please enter valid Email!"); //error message
            emailEditTxt.requestFocus();
            return;
        }
            //send request to reset password to user's email
            auth.sendPasswordResetEmail(eml).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(ForgotPasswordActivity.this,"Check your email to reset Password!", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(ForgotPasswordActivity.this,"Try again! Invalid Email Entered!", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }
    }

