package com.example.covidscape;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.HashMap;
import java.util.Map;

//Display Score from Quiz activity
public class ScoreActivity extends AppCompatActivity {
    CircularProgressBar circularProgressBar;
    TextView txtScore;
    private Button back;
    int score, newXP, userXP, xpEarned;
    final int TOTAL_QUESTIONS=5;

    DatabaseReference dbRef;
    private String userID;
    FirebaseUser user;

    private MediaPlayer mediaplayer, victoryPlayer;
    LottieAnimationView victoryConfetti;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score);

        score=getIntent().getIntExtra("score",0);

        //animation
        victoryConfetti = findViewById(R.id.confetti);
        victoryConfetti.animate();

        // background music and sound effect
        mediaplayer = MediaPlayer.create(this,R.raw.pop);
        victoryPlayer =MediaPlayer.create(this,R.raw.victory);

        circularProgressBar= (CircularProgressBar) findViewById(R.id.circularProgressBar);
        txtScore= (TextView) findViewById(R.id.txtScore);

        //back button to Quiz activity
        back = findViewById(R.id.backBtnQuiz);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start();
                startActivity(new Intent(ScoreActivity.this, QuizHomeActivity.class));
            }
        });

        victoryPlayer.start(); // sound effect

        circularProgressBar.setProgress(score);
        txtScore.setText(score+"/"+TOTAL_QUESTIONS);

        dbRef=FirebaseDatabase.getInstance("https://covidscape-login-logout-sop-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID=user.getUid();

        //get current XP points
        dbRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null){
                    userXP=userProfile.userXP;
                    System.out.println("userXP: "+userXP);

                    updateXP(score,userXP, dbRef, userProfile);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //System.out.println("Can't get current XP"); //for debugging
            }
        });

    }
    //update new XP points
    private void updateXP(int score, int userXP, DatabaseReference dbRef, User user){
        xpEarned = score * 20; //each correct answer is worth 20xp
        //System.out.println("xpEarned: " + xpEarned); //for debugging
        newXP = xpEarned + userXP;//add xp earned from quiz and user's old xp together
        //System.out.println("newXP: " + newXP);//for debugging
        if (user != null) {
            //stores new xp into hashmap with the database path
            Map<String, Object> pointsUpdate = new HashMap<>();
            pointsUpdate.put(userID + "/userXP", newXP);
            //update new userXP points into database
            dbRef.updateChildren(pointsUpdate);

            Toast.makeText(this, xpEarned + " XP has been added!", Toast.LENGTH_SHORT).show();
        }
    }

}
