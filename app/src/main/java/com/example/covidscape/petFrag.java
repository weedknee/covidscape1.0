package com.example.covidscape;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

//Avatar fragment
public class petFrag extends Fragment {
    View view;
    final int MAX_XP = 100;
    int userXP, userLevel;
    TextView levelTV, usernameTV;
    ProgressBar levelBar;
    ImageView avatarImg;


    DatabaseReference dbRef;
    private String userID;
    FirebaseUser user;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_pet, container, false);
        levelTV = view.findViewById(R.id.txtLevel);
        levelBar = view.findViewById(R.id.barLevel);
        usernameTV = view.findViewById(R.id.txtUsername);
        avatarImg = view.findViewById(R.id.imgAvatar);

        //scores update firebase
        user = FirebaseAuth.getInstance().getCurrentUser();
        dbRef = FirebaseDatabase.getInstance("https://covidscape-login-logout-sop-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users"); // retrieve data from "Users" in firebase
        userID = user.getUid();

        dbRef.child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user userProfile = snapshot.getValue(user.class);
                if (userProfile != null) {
                    //calculate xp and level
                    userXP = userProfile.userXP;
                    userLevel = (int) (userXP / MAX_XP);
                    //set text and progress
                    levelTV.setText("Level " + userLevel);
                    levelBar.setProgress(userProfile.userXP % MAX_XP);
                    usernameTV.setText(userProfile.username);

                    String avatar = userProfile.getAvatar();
                    System.out.println(avatar);

                    switch (userLevel) {
                        case 0:
                            if (avatar.equals("boy")) {
                                avatarImg.setImageResource(R.drawable.boy_lvl0);
                            } else {
                                avatarImg.setImageResource(R.drawable.girl_lvl0);
                            }
                            break;
                        case 1:
                            if (avatar.equals("boy")) {
                                avatarImg.setImageResource(R.drawable.boy_lvl1);
                            } else {
                                avatarImg.setImageResource(R.drawable.girl_lvl1);
                            }
                            break;
                        case 2:
                            if (avatar.equals("boy")) {
                                avatarImg.setImageResource(R.drawable.boy_lvl2);
                            } else {
                                avatarImg.setImageResource(R.drawable.girl_lvl2);
                            }
                            break;
                        default:
                            if (avatar.equals("boy")) {
                                avatarImg.setImageResource(R.drawable.boy_lvl3);
                            } else {
                                avatarImg.setImageResource(R.drawable.girl_lvl3);
                            }
                            break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_LONG).show();

            }
        });
        //set avatar according to levels


        return view;
    }

}
