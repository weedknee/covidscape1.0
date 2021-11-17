package com.example.covidscape;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Signup extends AppCompatActivity implements View.OnClickListener {

    LottieAnimationView writing;

    private EditText username, email, password, firstName, lastName;
    private Button registerUserBtn;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        writing = findViewById(R.id.writting);
        writing.animate();

        username = findViewById(R.id.accUsername);
        email = findViewById(R.id.accEmail);
        password = findViewById(R.id.accPassword);
        firstName = findViewById(R.id.accFirstName);
        lastName = findViewById(R.id.accLastName);

        mAuth = FirebaseAuth.getInstance();


        registerUserBtn = findViewById(R.id.Done);
        registerUserBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Done:
                registerUser();
                startActivity(new Intent(Signup.this,login.class));
                break;
        }
    }

    private void registerUser() {
        String getUsername = username.getText().toString().trim();
        String getPassword = password.getText().toString().trim();
        String getEmail = email.getText().toString().trim();
        String getfirstName = firstName.getText().toString().trim();
        String getLastName = lastName.getText().toString().trim();


        mAuth.createUserWithEmailAndPassword(getEmail, getPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            user User = new user(getUsername,getEmail,getfirstName,getLastName);
                            FirebaseDatabase.getInstance("https://covidscape-login-logout-sop-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(User).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Signup.this,"success",Toast.LENGTH_LONG).show();

                                    }else {
                                        Toast.makeText(Signup.this,"failed",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }
                });
    }


}


