package com.example.covidscape;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

//User profile fragment
public class profileFrag extends Fragment {

    private Button logoutBtn, updateBtn;
    private ImageButton arrowBack;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        logoutBtn = (Button) v.findViewById(R.id.logoutButton);
        arrowBack = (ImageButton) v.findViewById(R.id.arrowBackHomepage);
        updateBtn = (Button) v.findViewById(R.id.editProfileBtn);

        //button sound effect for fragment
        final MediaPlayer mediaplayer = MediaPlayer.create(getActivity(),R.raw.pop);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start(); //button sound effect
                Intent intent =  new Intent(getActivity(),UpdateActivity.class); //direct to UpdateActivity
                startActivity(intent);
            }
        });

        //perform logout and direct to login screen
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaplayer.start(); //button sound effect
                Intent intent = new Intent(getActivity(), login.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Please Perform Login/Sign Up", Toast.LENGTH_LONG).show();
            }
        });

        //get user data from firebase
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance("https://covidscape-login-logout-sop-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users"); //retrieve user data from "Users" in firebase
        userID = user.getUid();

        //declare textView to find id
        final TextView userUsernameTextView = (TextView) v.findViewById(R.id.username);
        final EditText firstNameTextView = (EditText) v.findViewById(R.id.firstName);
        final EditText lastNameTextView = (EditText) v.findViewById(R.id.lastName);
        final EditText emailTextView = (EditText) v.findViewById(R.id.email);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user userProfile = snapshot.getValue(user.class);

                if(userProfile != null){
                    String username = userProfile.username;
                    String firstName = userProfile.firstName;
                    String lastName = userProfile.lastName;
                    String email = userProfile.email;

                    //set user info
                    userUsernameTextView.setText(username);
                    firstNameTextView.setText(firstName);
                    lastNameTextView.setText(lastName);
                    emailTextView.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(),"Something went wrong",Toast.LENGTH_LONG).show();

            }
        });

        //back button to Main activity
        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaplayer.start(); // button sound effect
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

}