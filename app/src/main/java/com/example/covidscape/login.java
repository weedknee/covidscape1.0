package com.example.covidscape;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;


public class login extends AppCompatActivity {

    private TextView etEmail, etPw, forgotPass;
    Button loginBtn, signupBtn;
    FirebaseAuth mAuth;
    Boolean loginButtonClicked = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etEmail = findViewById(R.id.forgotPassEmail);
        etPw = findViewById(R.id.password);

        loginBtn = findViewById(R.id.loginButton);
        signupBtn = findViewById(R.id.signupButton);

        forgotPass = findViewById(R.id.forgotPassword);

        mAuth = FirebaseAuth.getInstance();

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,Signup.class));
            }
        });

        loginBtn.setOnClickListener(view -> {
            loginButtonClicked = true;
            loginOrSignUp();
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,forgotPassword.class));
            }
        });
    }




    private void loginOrSignUp() {
        String loginEmail = etEmail.getText().toString();
        String loginPass = etPw.getText().toString();

        if(TextUtils.isEmpty(loginEmail)){
            etEmail.setError("Email cannot be empty");
            etEmail.requestFocus();
        }
        else if(TextUtils.isEmpty(loginPass)){
            etPw.setError("Password cannot be empty");
            etPw.requestFocus();
        }
        else if(loginButtonClicked == true){
            mAuth.signInWithEmailAndPassword(loginEmail, loginPass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(login.this, "Login successfully", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //chg SOPChoiceActivity to homepage's java file
                                    startActivity(new Intent(login.this, MainActivity.class));
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(login.this, "Login failed. [ " + task.getException().getMessage() + " ]", Toast.LENGTH_SHORT).show();
                                }


                        }
                    });
        }


//    private void createUser() {
//        String signupEmail = etEmail.getText().toString();
//        String signupPass = etPw.getText().toString();
//
//        if(TextUtils.isEmpty(signupEmail)){
//            etEmail.setError("Email cannot be empty");
//            etEmail.requestFocus();
//        }
//        else if(TextUtils.isEmpty(signupPass)){
//            etPw.setError("Password cannot be empty");
//            etPw.requestFocus();
//        }
//        else{
//            mAuth.signInWithEmailAndPassword(signupEmail, signupPass)
//                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                // Sign in success, update UI with the signed-in user's information
//                                Toast.makeText(MainActivity.this, "Sign Up successfully", Toast.LENGTH_SHORT).show();
//                                FirebaseUser user = mAuth.getCurrentUser();
//
//                                startActivity(new Intent(MainActivity.this, SOPChoiceActivity.class));
//                            }
//                            else {
//                                // If sign in fails, display a message to the user.
//                                Toast.makeText(MainActivity.this, "Sign Up failed. [ " + task.getException().getMessage() + " ]", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//        }
//







//        //login
//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        //signup
//        signupBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {

//
//            }
//        });

    }
}
