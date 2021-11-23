package com.example.covidscape;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

// Main Menu of 3 fragments: Avatar, Home , User Profile
public class MainActivity extends AppCompatActivity {

    private MediaPlayer bgmPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //background music
        bgmPlayer = MediaPlayer.create(this,R.raw.home);
        bgmPlayer.setVolume(20,20);
        bgmPlayer.setLooping(true);
        bgmPlayer.start();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
    }

    //bottom navigation with 3 fragments
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.firstFragment:
                            selectedFragment = new AvatarFragment(); //avatar fragment
                            break;
                        case R.id.secondFragment:
                            selectedFragment = new HomeFragment(); //home fragment
                            break;
                        case R.id.thirdFragment:
                            selectedFragment = new ProfileFragment(); //profile fragment
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }

            };

    //background music play
    @Override
    public void onResume() {
        super.onResume();
        bgmPlayer.start();
    }
    //background music pause
    @Override
    protected void onPause() {
        super.onPause();
        bgmPlayer.stop();
    }

    //background music end
    @Override
    public void onDestroy() {
        super.onDestroy();
        bgmPlayer.stop();
        bgmPlayer.release();

    }

}