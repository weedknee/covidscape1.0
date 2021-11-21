//package com.example.covidscape;
//
//import android.content.Intent;
//import android.media.MediaPlayer;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.material.textfield.TextInputEditText;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.firestore.auth.User;
//
//public class updateProfile extends AppCompatActivity {
//
//    private FirebaseAuth mAuth;
//    private TextInputEditText usernameText, firstNameText, lastNameText, emailText;
//    private Button updateBtn, cancelUpdateBtn;
//    private DatabaseReference reference;
//    String uName,fName, lName, Email, avatar;
//    int userXP;
//    private FirebaseUser user;
//    private String userID;
//    private user userClass;
//
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.update_profile);
//
//
//        mAuth = FirebaseAuth.getInstance();
//        updateBtn = findViewById(R.id.updateProfileBtn);
//        usernameText = findViewById(R.id.editUsername);
//        firstNameText = findViewById(R.id.editFirstName);
//        lastNameText = findViewById(R.id.editLastName);
//        emailText   = findViewById(R.id.editEmail);
//        cancelUpdateBtn = findViewById(R.id.cancelUpdateProfileBtn);
//
//        reference = FirebaseDatabase.getInstance("https://covidscape-login-logout-sop-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users");
//        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
//
//        reference.child(userID).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                DataSnapshot dataSnapshot = task.getResult();
//                uName = String.valueOf(dataSnapshot.child("username").getValue());
//                fName = String.valueOf(dataSnapshot.child("firstName").getValue());
//                lName = String.valueOf(dataSnapshot.child("lastName").getValue());
//                Email = String.valueOf(dataSnapshot.child("email").getValue());
//
//                usernameText.setText(uName);
//                firstNameText.setText(fName);
//                lastNameText.setText(lName);
//                emailText.setText(Email);
//            }
//        });
//
//        cancelUpdateBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(new Intent(updateProfile.this,profileFrag.class));
//            }
//        });
//
//      updateBtn.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View v) {
//              fName = firstNameText.getText().toString();
//               lName = lastNameText.getText().toString();
//               Email = emailText.getText().toString();
//
//               userClass = new user(uName,fName,lName,Email,userXP,avatar);
//               reference.child(userID).setValue(userClass);
//               Toast.makeText(updateProfile.this,"Update Success!",Toast.LENGTH_LONG).show();
//              startActivity(new Intent(updateProfile.this,profileFrag.class));
//          }
//      });
//    }
//
//}
