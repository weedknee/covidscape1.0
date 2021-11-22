package com.example.covidscape;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

//Sign up Activity
public class Signup extends AppCompatActivity implements View.OnClickListener {

    LottieAnimationView writing;
    private EditText username, email, password, firstName, lastName;
    private Button registerUserBtn;
    private ImageButton back;
    private FirebaseAuth mAuth;
    private RadioGroup radGAvatar;
    private MediaPlayer mediaPlayer;
    int avatarChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        //animation
        writing = findViewById(R.id.writting);
        writing.animate();

        username = findViewById(R.id.accUsername);
        email = findViewById(R.id.accEmail);
        password = findViewById(R.id.accPassword);
        firstName = findViewById(R.id.accFirstName);
        lastName = findViewById(R.id.accLastName);

        mediaPlayer = MediaPlayer.create(this, R.raw.pop);

        //back button to login activity
        back = findViewById(R.id.signupBackBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                startActivity(new Intent(Signup.this,login.class));
            }
        });

        mAuth = FirebaseAuth.getInstance();

        registerUserBtn = findViewById(R.id.Done);
        registerUserBtn.setOnClickListener(this);

        radGAvatar=findViewById(R.id.radGroupAvatar);
    }

    //switch case to perform sign up
    @Override
    public void onClick(View v) {
        final MediaPlayer mediaplayerBtn = MediaPlayer.create(this, R.raw.pop);
        switch (v.getId()) {
            case R.id.Done:
                mediaplayerBtn.start(); //button sound effect
                registerUser();
                startActivity(new Intent(Signup.this,login.class));
                break;
        }
    }

    // get text from user
    private void registerUser() {
        String getUsername = username.getText().toString().trim();
        String getPassword = password.getText().toString().trim();
        String getEmail = email.getText().toString().trim();
        String getfirstName = firstName.getText().toString().trim();
        String getLastName = lastName.getText().toString().trim();
        avatarChecked=radGAvatar.getCheckedRadioButtonId();
        String avatar;
        if (avatarChecked== R.id.radioGirl){
            avatar="girl";
        } else if ( avatarChecked == R.id.radioBoy){
            avatar="boy";
        } else{
            avatar="boy";
        }

        mAuth.createUserWithEmailAndPassword(getEmail, getPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            user User = new user(getUsername,getEmail,getfirstName,getLastName,0, avatar); //store into java user class
                            FirebaseDatabase.getInstance("https://covidscape-login-logout-sop-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users") //store in "Users" firebase database
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


