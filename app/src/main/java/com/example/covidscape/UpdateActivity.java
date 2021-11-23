package com.example.covidscape;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

//update activity to update user profile
public class UpdateActivity extends AppCompatActivity {
    EditText usernameET, firstNameET, lastNameET, emailET;
    Button updateBtn, cancelBtn;
    DatabaseReference dbRef;
    FirebaseUser user;
    private String userID;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_profile);

        mediaPlayer = MediaPlayer.create(this,R.raw.pop);
        usernameET=findViewById(R.id.editUsername);
        firstNameET=findViewById(R.id.editFirstName);
        lastNameET=findViewById(R.id.editLastName);
        emailET=findViewById(R.id.editEmail);
        updateBtn=findViewById(R.id.updateProfileBtn);
        cancelBtn=findViewById(R.id.cancelUpdateProfileBtn);

        //get database reference to Users
        dbRef= FirebaseDatabase.getInstance("https://covidscape-login-logout-sop-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID=user.getUid();

        dbRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //read user data from database and store in userProfile (user Class)
                User userProfile = snapshot.getValue(User.class);
                if(userProfile != null){
                   usernameET.setText(userProfile.getUsername());
                   firstNameET.setText(userProfile.getFirstName());
                   lastNameET.setText(userProfile.getLastName());
                   emailET.setText(userProfile.getEmail());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateActivity.this, "Error retrieving user data.", Toast.LENGTH_SHORT).show();
            }
        });

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                String newUsername, newFirstName, newLastName, newEmail;
                //store values from EditText fields into the following String
                newUsername = usernameET.getText().toString();
                newFirstName = firstNameET.getText().toString();
                newLastName=lastNameET.getText().toString();
                newEmail=emailET.getText().toString();

                if (user != null) {
                    //store String into a Hashmap with the respective database path
                    Map<String, Object> profileUpdate = new HashMap<>();
                    profileUpdate.put(userID + "/username", newUsername);
                    profileUpdate.put(userID + "/firstName", newFirstName);
                    profileUpdate.put(userID + "/lastName", newLastName);
                    profileUpdate.put(userID + "/email", newEmail);
                    //update values in the database
                    dbRef.updateChildren(profileUpdate);
                    //inform the user of the action performed
                    Toast.makeText(UpdateActivity.this, " Profile has been updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateActivity.this,MainActivity.class));
                }
            }
        });

        //cancel button to go back to Main activity
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                startActivity(new Intent(UpdateActivity.this,MainActivity.class));
            }
        });

    }

}
