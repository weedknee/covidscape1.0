package com.example.covidscape;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

//Loading screen before Quiz starts
public class loadingQuiz extends AppCompatActivity {
    public static ArrayList<QuizHandler> questionBank;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_quiz);

        questionBank=new ArrayList<>(); //store QuestionBank into ArrayList
        databaseReference= FirebaseDatabase.getInstance("https://covidscape-login-logout-sop-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("QuestionBank"); //retrieve data from "QuestionBank" in database

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    QuizHandler quizHandler = dataSnapshot.getValue(QuizHandler.class);
                    questionBank.add(quizHandler);
                }
                Intent intent = new Intent(loadingQuiz.this, QuizActivity.class);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}