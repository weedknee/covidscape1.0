package com.example.covidscape;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//Main page fragment
public class HomeFragment extends Fragment {

    private ImageButton indoor, outdoor,quiz,covidNews;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private MediaPlayer mediaplayer;

    @Nullable
    @Override
    //set view to home fragment
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container,false);

        indoor = (ImageButton) v.findViewById(R.id.indoor);
        outdoor = (ImageButton) v.findViewById(R.id.outdoor);
        quiz = (ImageButton) v.findViewById(R.id.quiz);
        covidNews = (ImageButton) v.findViewById(R.id.covidNewsBtn);

        mediaplayer = MediaPlayer.create(getActivity(),R.raw.pop);

        indoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start(); // button sound effect
                Intent intent = new Intent(getActivity(), SopIndoorActivity.class);
                startActivity(intent);
                }
        });

        outdoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start(); // button sound effect
                Intent intent = new Intent(getActivity(), SopOutdoorActivity.class);
                startActivity(intent);
            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start(); // button sound effect
                Intent intent = new Intent(getActivity(), QuizHomeActivity.class);
                startActivity(intent);
            }
        });

        covidNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start(); // button sound effect
                Intent intent = new Intent(getActivity(), CovidNewsActivity.class);
                startActivity(intent);
            }
        });

        //get user info from firebase database to display username in home screen
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance("https://covidscape-login-logout-sop-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users"); //retrieve data from "Users" in database
        userID = user.getUid();

        final TextView greetingUserTextView = (TextView) v.findViewById(R.id.homeName);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class); // get username from database to display in home screen
                if (userProfile != null) {
                    String firstName = userProfile.firstName;
                    greetingUserTextView.setText("Hello, " + firstName + "!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

}